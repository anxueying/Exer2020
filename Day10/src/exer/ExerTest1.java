package exer;

/**
 * 1. 继承的好处
 * ① 易于维护
 * ② 提高代码复用性
 * ③ 让类与类之间产生关系，创建更丰富的数据类型（多态） //没想到
 *
 * 2. 方法重写和方法重载的区别
 * ① 重写 override
 * 前提：子类继承父类后
 * 目的：对父类同名方法做扩展或重写
 *      ① 出现一样的方法签名时（修饰符、返回值、方法名必须完全相同），创建子类时，调用该方法，实际运行的是子类的方法，如同将父类中的方法覆盖了一样。
 *      ② 子类重写的访问控制修饰符，不能小于父类被重写方法的访问控制修饰符（编译报错），在这种情况下，访问控制修饰符可以不同
 *      ③ 子类方法的返回值类型必须是父类被重写方法的返回值类型的子类，在这种情况下，返回值类型可以不同
 *
 * ② 重载 overload
 * 前提：在同一类下，方法名相同，参数列表不同（参数个数、参数类型，与返回值类型无关）
 * 目的：可以用同一方法名实现不同的需求
 *
 *
 * 3. 写出this和super的用法。
 * ① 属性  this.属性   super.属性
 * ② 方法  this.方法  super.方法
 * ③ 构造器 this() 避免递归引用   super()  必须放在构造器可运行代码的首行
 *
 */
public class ExerTest1{
    public static void main(String[] args){
        new GrandSun();
        /*
        "我是Parent的无参构造"
        "我是sub的无参构造"
        "我是grandSun的无参构造"

        "我是Parent的无参构造"
        "我是sub的无参构造"
        "我是grandSun的有参构造"
         */
        new GrandSun("hello");
    }
}

class Parent{
    public Parent(String name){
        System.out.println("我是Parent的有参构造");
    }
    public Parent(){
        System.out.println("我是Parent的无参构造");
    }

}
class Sub extends Parent{

    public Sub(String name){
        System.out.println("我是sub的有参构造");
    }

    public Sub(){
        super();
        System.out.println("我是sub的无参构造");
    }
}
class GrandSun extends Sub{

    public GrandSun(){
        super();
        System.out.println("我是grandSun的无参构造");
    }

    public GrandSun(String name){
        super();
        System.out.println("我是grandSun的有参构造");
    }

}

