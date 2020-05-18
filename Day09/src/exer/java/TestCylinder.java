package exer.java;

import sun.security.util.Length;

public class TestCylinder {
    public static void main(String[] args) {
        Cylinder cl = new Cylinder(10,3);
        System.out.println("cl.findArea() = " + cl.findArea());
        System.out.println("圆柱的体积为： " + cl.findVolume());
    }
}

class Circle{
    private double radius;

    public Circle(){
        this.radius =1;
    }

    public Circle(double radius){
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double findArea(){
        return Math.PI*radius*radius;
    }
}

class Cylinder extends Circle{
    private double length;

    public Cylinder() {
        this.length = 1;
    }

    public Cylinder(double length, double radius){
        super(radius);
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double findVolume(){
        return findArea()*this.length;
    }
}
