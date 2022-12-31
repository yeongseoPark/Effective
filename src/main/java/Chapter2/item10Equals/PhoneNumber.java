package Chapter2.item10Equals;

public final class PhoneNumber {
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


}
