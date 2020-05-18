package homework.java;

import java.util.Objects;

public class Exercise9 {
    public static void main(String[] args) {
        Triangle t1 = new Triangle(2,3,6);
        Triangle t2 = new Triangle(4,3,5);

        System.out.println(t1);
        System.out.println("t1.equals(t2) = " + t1.equals(t2));

        System.out.println("t2.hashCode() = " + t2.hashCode());
        System.out.println("t2.getArea() = " + t2.getArea());
        System.out.println("t1.getPiremeter() = " + t1.getPiremeter());

    }
}

class Triangle{
    private double a;
    private double b;
    private double c;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triangle() {
    }

    @Override
    public String toString(){
        return a+" - "+b+" - "+c;
    }

    @Override
    public boolean equals(Object object){
        if (object == this){
            return true;
        }

        if (object instanceof Triangle){
            Triangle triangle = (Triangle)object;
            if (triangle.getA() == this.getA() &&triangle.getB() == this.getB()&&triangle.getC()==this.getC() ){
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    public double getPiremeter(){
        return a+b+c;
    }

    public double getArea(){
        double p = getPiremeter();
        return Math.pow(p*(p-a)*(p-b)*(p-c),1/2);
    }
}
