package Chapter2.item9trywithResource;

import java.io.*;

public class Redundant {

    /* 자원이 둘 이상이면 try-finally 방식은 지저분하다! */
    static void copy(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[100];
                int n;
                while ((n = in.read(buf)) >= 0) {
                    out.write(buf, 0 , n);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    static void tryWithResourceCopy(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[100];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0 , n);
            }
        }
    }
}
