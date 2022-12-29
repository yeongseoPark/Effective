package Chapter2.item9trywithResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class trywithresource {

    /* try-finally로 자원의 닫힘을 보장 - readLine과 close 메서드 모두 실패하면 readLine 의 예외는 close()의 예외에 삼켜져 스택 내역에 남지 않게 됨 */
    static String firstLineOfFileTryFinally(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    /* readLine 과 close(BufferedReader가 AutoCloseable을 구현) 호출 양쪽에서 예외가 발생하면, readLine에서 발생한 예외가 기록되고 close 에서 발생한 예외는
       스택 추적 내역에 suppressed 꼬리표를 달고 출력됨 (getSuprresed메서드로 가져올 수도 있다) */
    static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new FileReader(path))) { /* try 블록이 끝나면 BufferedReader는 (예외가 던져지더라도) 자동적으로 닫힌다 */
            return br.readLine();
        }
    }

    static String firstLineOfFileWithCatch(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new FileReader(path))) {
            return br.readLine();
        } catch (IOException ie) { // 파일을 열거나 데이터를 읽는데 실패시 예외를 던지는 대신 기본값을 반환
            return "기본값";
        }
    }
}
