package preview.java;

public abstract class Frock {

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

    public Frock(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Frock() {
        this.serialNumber = getNextNum();
    }

    public abstract double calcArea();
}

class Shirt extends Frock implements Clothing{
    private double area;
    private String color;

    public String getColor(){
        return color;
    }
    public String getDetails(){
        return "面积";
    }


    public double calcArea(){
        return area*1.3;
    }
}

interface Clothing{
    double area = 10;
    String color = "白色";

    double calcArea();
    String getColor();
    String getDetails();

}