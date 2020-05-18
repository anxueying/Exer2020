package preview.java;

public class TestFrock {
    public static void main(String[] args) {
        Frock f1 = new Shirt();
        Shirt shirt = new Shirt();
        Clothing clothing = new Shirt();
        System.out.println("Frock" + f1.getSerialNumber()+"\t"+f1.calcArea());
        System.out.println("shirt" + shirt.getSerialNumber()+"\t"+shirt.calcArea());
        System.out.println("clothing.getDetails() = " + clothing.getDetails());

    }
}
