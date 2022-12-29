package Chapter2.item4Private;

public class UtilityClass {
    /* private 생성자 : 클래스 밖 접근 불가, 내부에서 실수로 호출시 AssertionError 발생 */
    private UtilityClass() {
        throw new AssertionError();
    }
}
