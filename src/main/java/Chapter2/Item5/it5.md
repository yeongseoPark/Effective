클래스가 내부적으로 하나 이상의 자원에 의존하고, 그 자원이 클래스 동작에 영향을 준다면 
- 싱글턴과 정적 유틸리티 클래스 대신 
- 필요한 자원(또는 그 자원을 만들어주는 팩터리를) 생성자에 넘겨주자. 
- 이것이 의존 객체 주입