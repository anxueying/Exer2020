public class Test05 {
    public static void main(String[] args) {
        Triangle tria = new Triangle();
        tria.a = 3;
        tria.b = 4;
        tria.c = 5;
        System.out.println(tria.isRightTriangle(3,4,5));
        System.out.println(tria.isIsoscelesTriangle(3,3,5));
        System.out.println(tria.isEquilateralTriangle(3,3,3));
        System.out.println(tria.getArea(3,4,5));
        System.out.println(tria.getLength(3,4,5));
    }
}

class Triangle{
    double a;
    double b;
    double c;

    public boolean isRightTriangle(double a,double b,double c){
        //判断是否是一个直角三角形
        if((a*a+b*b==c*c)||(a*a+c*c==b*b)||(c*c+b*b==a*a)){
            return true;
        }else {
            return false;
        }
    }

    public boolean isIsoscelesTriangle(double a,double b,double c){
        //判断是否是一个等腰三角形
        if((a==b)||(a==c)||(b==c)){
            return true;
        }else {
            return false;
        }
    }

    public boolean isEquilateralTriangle(double a,double b,double c){
        //判断是否是一个等边三角形
        if((a==b)&&(a==c)){
            return true;
        }else {
            return false;
        }
    }

    public double getArea(double a,double b,double c){
        //海伦公式求面积
        double p = getLength(a,b,c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    public double getLength(double a,double b,double c){
        //求周长
        return a+b+c;
    }


}