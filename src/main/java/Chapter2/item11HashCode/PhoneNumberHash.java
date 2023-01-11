package Chapter2.item11HashCode;

import java.util.HashMap;
import java.util.Map;

class PhoneNumber {
     private final short areaCode, prefix, lineNum;

     public PhoneNumber(short areaCode, short prefix, short lineNum) {
         this.areaCode = rangeCheck(areaCode, 999, "지역코드");
         this.prefix = rangeCheck(prefix, 999, "프리픽스");
         this.lineNum = rangeCheck(prefix, 9999, "가입자 번호");
     }

     private static short rangeCheck(int val, int max, String arg) {
         if (val < 0 || val > max) throw new IllegalArgumentException(arg + " : " + val);
         return (short) val;
     }

     @Override public boolean equals(Object o) {
         if (o == this)  // 1. 입력이 자기 자신의 참조인지 확인
             return true;
         if (!(o instanceof PhoneNumber)) // 2. instanceof 로 입력이 올바른 타입인지 확인
             return false;
         PhoneNumber pn = (PhoneNumber) o; // 3. 입력을 올바른 타입으로 형변환
         return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode; // 4. 핵심 필드들의 일치 하나씩 검사
     }

     /* 전형적인 hashCode */
//     @Override public int hashCode() {
//         int result = Short.hashCode(areaCode);
//         result = 31 * result + Short.hashCode(prefix);
//         result = 31 * result + Short.hashCode(lineNum);
//
//         return result;
//     }

     /* 한줄짜리 hashCode메서드 - 그닥 좋지 않은 성능
     @Override public int hashCode() {
        return Objects.hash(lineNum, prefix, areaCode);
     }
      */

    // 해시코드 지연 초기화하는 hashCode메서드 : 필요할때 계산해서 성능을 향상시킨다
    private int hashCode; // 선언시 기본값인 0으로 초기화, hashCode() 메서드가 불릴때까지 계산되지 않음

    @Override public int hashCode() {
        int result = hashCode;
        if (result == 0) { // 메서드가 호출된 적 없다면
            result = Short.hashCode(areaCode);
            result = 31 * result + Short.hashCode(prefix);
            result = 31 * result + Short.hashCode(lineNum);
        }
        return result;
    }

    public static void main(String[] args) {
        Map<PhoneNumber, String> m = new HashMap<>();
        m.put(new PhoneNumber((short) 707, (short) 867, (short) 5309), "제니");
        System.out.println(m.get(new PhoneNumber((short) 707, (short) 867, (short) 5309))); // 제니
        /* hashCode를 재정의하지 않으면 논리적 동치인 두 객체가 서로 다른 해시코드를 반환하기에 null이 반환된다 */
    }
}


