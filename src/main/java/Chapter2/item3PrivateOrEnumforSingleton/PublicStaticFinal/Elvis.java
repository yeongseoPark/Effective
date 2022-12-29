package Chapter2.item3PrivateOrEnumforSingleton.PublicStaticFinal;

public class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    private Elvis() {}

    public void leaveTheBuilding() {
        System.out.println("leave");
    }
}

class tmp {
    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
    }
}
