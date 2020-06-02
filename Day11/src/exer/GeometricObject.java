package exer;

public class GeometricObject {
    protected String color;
    protected double weight;

    protected GeometricObject(){

    }
    protected  GeometricObject(String color,double weight){
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

class Circle extends GeometricObject{
    private double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(String color, double weight, double radius) {
        super(color, weight);
        this.radius = radius;//protect 子类可直接继承使用
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double findArea(){
        return Math.PI*Math.sqrt(radius);
    }

    public boolean equals(Circle c){//存在安全隐患
        //return this.findArea() == c.findArea();
        return this.getColor() == c.getColor();
    }

    public String toString(){
        return ""+this.radius;
    }
}
