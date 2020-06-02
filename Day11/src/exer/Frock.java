package exer;

public class Frock {

    private static int currentNum=100000;
    private int serialNumber;

//    static {
//        currentNum=150000;
//    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public static int getNextNum(){
        currentNum+=100;
        return currentNum;
    }

    public Frock() {
        this.serialNumber = getNextNum();
    }
}
