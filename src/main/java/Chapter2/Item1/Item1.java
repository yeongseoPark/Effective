package Chapter2.Item1;

import java.math.BigInteger;
import java.util.*;

public class Item1 {
    public static Boolean valueOf(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }

    public static void main(String[] args) {
        System.out.println(BigInteger.valueOf(3));
        Vector<Integer> vt = new Vector<>(Arrays.asList(1, 2, 3, 4, 5));
        Enumeration<Integer> e = vt.elements();
        System.out.println(Collections.list(e));
    }
}

