package homework.java;

import java.awt.*;

public class PolymorphismTest4 {
    public static void main(String[] args) {
        Circle c = new Circle(3);
        MyRectangle r = new MyRectangle(3,4);
        Triangle t = new Triangle(3,4,5);

        Graphic[] graphics = new Graphic[3];
        graphics[0] = c;
        graphics[1] = r;
        graphics[2] = t;

        Graphic[] graphicsRank = areaRank(graphics);
        scanGraphic(graphicsRank);

        if(equalsArea(c,r)){
            System.out.println("面积相等");
        }else {
            System.out.println("面积不等");
        };

        System.out.println("面积更大的是： " + maxArea(r, t));
    }

    /**
     * 测试两个对象的面积是否相等
     * @param g1 动态绑定
     * @param g2 动态绑定
     * @return
     */
    public static boolean equalsArea(Graphic g1,Graphic g2){
        return g1.getArea()==g2.getArea();
    }

    /**
     * 找出面积大的
     * @param g1
     * @param g2
     * @return
     */
    public static double maxArea(Graphic g1,Graphic g2){
        return Math.max(g1.getArea(),g2.getArea());
    }

    public static void scanGraphic(Graphic[] graphics){
        for (Graphic graphic : graphics) {
            System.out.println(graphic.getInfo());
        }
    }

    public static Graphic[] areaRank(Graphic[] graphics){
        for (int i = 0; i < graphics.length-1; i++) {
            for (int j=0;j<graphics.length-1-i;j++){
                if (graphics[j].getArea()<graphics[j+1].getArea()){
                    Graphic tempG = graphics[j];
                    graphics[j] = graphics[j+1];
                    graphics[j+1] = tempG;
                }
            }
        }
        return graphics;
    }

}

/**
 * 几何形状
 */
class Graphic{
    private double area;
    private double perimeter;

    public Graphic() {
    }

    public Graphic(double area, double perimeter) {
        this.area = area;
        this.perimeter = perimeter;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public String getInfo(){
        return "面积："+getArea()+"，周长："+getPerimeter();
    }
}

/**
 * 圆形
 */
class Circle extends Graphic{
    private double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(double area, double perimeter, double radius) {
        super(area, perimeter);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI*Math.pow(radius,2);
    }

    @Override
    public double getPerimeter() {
        return 2*Math.PI*radius;
    }

    @Override
    public String getInfo() {
        return "圆形\t"+super.getInfo();
    }
}

/**
 * 矩形
 */
class MyRectangle extends Graphic{
    private double width;
    private double height;

    public MyRectangle() {
    }

    public MyRectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public MyRectangle(double area, double perimeter, double width, double height) {
        super(area, perimeter);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double getArea() {
        return width*height;
    }

    @Override
    public double getPerimeter() {
        return 2*(width+height);
    }

    @Override
    public String getInfo() {
        return "矩形\t"+super.getInfo();
    }
}

class Triangle extends Graphic{
    private double a;
    private double b;
    private double c;

    public Triangle() {
    }

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triangle(double area, double perimeter, double a, double b, double c) {
        super(area, perimeter);
        this.a = a;
        this.b = b;
        this.c = c;
    }

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

    @Override
    public double getArea() {
        double p = getPerimeter()/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    @Override
    public double getPerimeter() {
        return a+b+c;
    }

    @Override
    public String getInfo() {
        return "三角形：" + super.getInfo();
    }
}