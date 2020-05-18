public class PassObject {
    public static void main(String[] args) {
        PassObject po = new PassObject();//这个没记熟
        Circle c1 = new Circle();
        po.printAreas(c1,5);
    }

    public void printAreas(Circle c,int times){
        String result = "Radius\tArea\n";
        for(int i=1;i<=times;i++){
            c.setRadius(i);
            result += (double)i + "\t\t" + c.findArea()+"\n";
        }

        System.out.println(result);
        
        c.setRadius(times+1);
        System.out.println("now radius is: " + c.getRadius());
    }
}


class Circle{
    private double radius; //半径

    public Circle(){

    }

    public Circle(double radius){
        this.radius = radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double findArea(){
        return Math.PI*this.radius*this.radius;
    }
}