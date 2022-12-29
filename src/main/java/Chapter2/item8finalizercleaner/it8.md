# finalizer와 cleaner 사용을 피하라 

- 자바의 두가지 객체 소멸자 
  - finalizer
  - cleaner

- 둘 다 **즉시 수행된다는 보장이 없다.**
  - 이를 얼마나 신속히 수행할지는 전적으로 GC알고리즘에 달렸기에, GC 구현마다 천차만별이다.
  - finalizer는 자신을 수행할 스레드를 제어할 수 없고, 다른 애플리케이션 스레드보다 우선순위가 낮다
  - cleaner는 자신을 수행할 스레드를 제어할 수는 있다.
  - 둘 모두 수행 시점뿐이 아니라 수행 여부조차 보장되지 않는다.
- 따라서 **상태를 영구적으로 수정하는 작업에서 절대 finalizer와 cleaner에 의존해서는 안 된**다.
- System.gc와 System.runFinalization 메서드 모두 finalizer와 cleaner 의 실행 가능성을 높여줄 수는 있으나, 보장해주지는 않는다.

- 또한, finalizer 동작 중 발생한 예외는 무시되고, 처리할 작업이 남아있더라도 그 순간 종료된다.
  - 스레드가 이처럼 훼손된 객체를 사용하려 한다면 어떻게 동작할지 예측할 수 없다.
  - 경고조차 출력하지 않고 잡지 못한 예외로 인해 객체가 훼손되며, 미래를 예측할 수 없다.
  - cleaner는 자신의 스레드를 통제하기 때문에 이런 문제는 발생하지 않는다.

- **심각한 성능 문제를 동반한다.**
  - finalizer가 가비지 컬렉터의 효율을 떨어트린다.
- **finalizer 공격에 노출되어 보안 문제를 일으킨다**
  - 생성자 또는 직렬화 과정에서 예외가 발생하면, 이 생성되다 만 객체에서 악의적인 **하위 클래스의 finalizer**가 수행될 수 있다.
  - finalizer는 static field에 자신의 참조를 할당하여, 가비지 컬렉터가 수집하지 못하게 막을 수 있다(low메모리의 필드가 참조중이기에 gc수거 X)
  - finalizer는 생성자에서 객체 생성을 막기 위해 던지는 예외를 무시
  - **final이 아닌 클래스를 finalizer 공격으로부터 방어하려면, 아무 일도 하지 않는 finalizer 메서드를 만들고 final로 선언**
    - final 클래스는 하위클래스를 만들 수 없기때문
  
## finalizer와 clenaer의 대안은?
- AutoCloseable(인터페이스)를 구현해주고, 클라이언트에서 인스턴스를 다 쓰고 나면 close 메서드를 호출.
    - 이때 각 인스턴스는 자신이 닫혔는지를 추적하는 것이 좋다.
    - close메서드에서 객체가 유효하지 않음을 필드에 기록하고, 다른 메서드는 이 필드를 검사해서 객체가 닫힌 다음 부른것이라면 IllegalStateException을 던짐

## finalizer와 cleaner는 대체 어디다 쓰는가?
1. 자원의 소유자가 close 메소드를 호출하지 않는 것에 대비한 안전망 역할
   - 클라이언트가 하지 않은 자원 회수를 늦게라도 해주는 것
2. native peer 와 연결된 객체에서 사용
   - native peer : 일반 자바 객체가 네이티브 메서드를 통해 기능을 위임한 네이티브 객체
   - 그니까, java와 결합한 자바가 아닌 객체 
     - Ex) 하드웨어를 제어하기 위한 자바객체
   - 이는 자바 객체는 아니니 가비지 컬렉터가 그 존재를 알 수 없다.
   - 피어가 되는 자바 객체를 회수할때 네이티브 객체가 회수되도록 cleaner나 finalizer가 나서서 처리해야 함.
   - 성능 저하를 감당할 수 있고, 네이티브 피어가 심각한 자원을 가지고 있지 않을때만 해당됨.

```java
public class Room implements AutoCloseable {
    /* 안전망 : GC가 Room을 회수할때까지 클라이언트가 close를 호출 않는다면 run메서도 호출 */
    private static final Cleaner cleaner = Cleaner.create(); 

    private static class State implements Runnable { // 정적이 아닌 중첩 클래스는 자동으로 바깥 객체의 참조를 가짐
        int numJunkPiles;

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        @Override // close메서드나 cleaner가 호출 cleanable에 의해 딱 한번만 호출됨
        public void run() {
            System.out.println("방 청소");
            numJunkPiles = 0;
        }
    }
    // 방의 상태를 cleanable과 공유
    private final State state; // Room인스턴스 참조시 순환참조로 인해 GC가 인스턴스를 회수하지 않음
    
    // cleanable 객체. 수거 대상이 되면 방을 청소
    private final Cleaner.Cleanable cleanable; 

    public Room(int numJunkPiles) {
        state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() {
        cleanable.clean(); // 보통은 얘가 run메서드 호출
    }
}

```