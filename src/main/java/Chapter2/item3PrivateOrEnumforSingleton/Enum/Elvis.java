package Chapter2.item3PrivateOrEnumforSingleton.Enum;

public enum Elvis {
    INSTANCE;

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
