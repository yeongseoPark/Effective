package Chapter2.Item7objectReferenceRelease;

import java.lang.ref.WeakReference;

public class stock {
    String name;

    public stock(String name) {
        this.name = name;
    }
}

class gc {
    public static void main(String[] args) {
        stock phone = new stock("phone");

        WeakReference<stock> weak  = new WeakReference<>(phone);

        System.out.println(weak.get().name);

        // 할당 해지
        phone = null;

        System.gc();

        System.out.println(weak); // WeakReference 자체를 할당해제하는 것은 아니고, 안에 담긴 reference를 gc가 해제.
        System.out.println(weak.get().name); // 따라서 .get() 한 결과물이 null이 되어 npe 발생
    }
}