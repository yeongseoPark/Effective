package Chapter2.Item6avoidUnnecessaryObject;

import java.util.regex.Pattern;

public class RomanNumerals {
//    static boolean isRomanNumeral(String s) {
//        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})" + "(X[CL]|L>X{0,3})(I[XV]|V?I{0,3})$");
//    }

    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})" + "(X[CL]|L>X{0,3})(I[XV]|V?I{0,3})$");
    // 캐싱

    static boolean isRomanNumeral(String s) {
        return ROMAN.matcher(s).matches();
    }

    private static long sum() {
//        Long sum = 0L; -> long 인 i가 sum에 더해질때마다 Long인스턴스(i를 Long으로) 생성(2^31개)
        long sum = 0L;
        for (long i =0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }

        return sum;
    }

    public static void main(String[] args) {
         long sum = sum();
    }
}
