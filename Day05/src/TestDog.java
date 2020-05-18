/**
 * @author Lenovo
 */
public class TestDog {
    public static void main(String[] args) {
        Dog d1 = new Dog();
        d1.name = "京巴";
        System.out.println(d1.say());

        Dog d2 = new Dog();
        d2.name = "吉娃娃";
        d2.weight = 8;
        System.out.println(d2.say());
    }
}

class Dog{
    /**
     * 变量
     */

    String name;
    int age = 1;
    double weight =10;
    /**
     * 方法
     */

    public String say(){
        return name+"，"+age+"，"+weight;
    }
}