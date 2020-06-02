package exer;

public class GeometricObjectTest {
    public static void main(String[] args) {
        Circle c1 = new Circle("blue",10,10);
        Circle c2 = new Circle("red",11,10);

        System.out.println("c1半径为：" + c1);
        System.out.println("c2半径为： " + c2);

        //System.out.println("c1与c2半径是否相等：" + c1.equals(c2));
        System.out.println("c1与c2颜色是否相等：" + c1.equals(c2));

    }
}
