package exer.java;

/*
一、面向对象的特性之二：继承
注意：
1. 不能为了简化代码，获取某功能而继承。两个类之间要有一定的所属关系：is a

Q:工作中，两个完全是功能性的类，语义无法分辨，如何确定所属关系?
A:继承试一下，当A父B子，此时B继承了A中所有的属性和方法，这时看这些属性、方法是否完全符合B。

2. 只支持单继承，不支持多继承（一个子类只能有一个父类）

3. 支持多层继承（Person--Employee--Manager）

好处：
1. 提高代码的复用性
2. 易于维护
3. 创建更丰富的数据类型（多态）

当子类继承父类后，类中各成员的特点
1. 属性
① 出现同名属性，优先调用子类的。若需调用父类的同名属性，需要用关键字super
② 子类继承父类中“所有”属性，包括私有。因为private修饰符的作用，子类不能直接访问，需通过公共的get/set方法来访问。

//super的使用方法与this一样
    - super.属性
    - super.方法
    - super  构造器

2. 方法
重写（覆盖，override）
① 出现一样的方法签名时（修饰符、返回值、方法名必须完全相同），创建子类时，调用该方法，实际运行的是子类的方法，如同将父类中的方法覆盖了一样。
② 子类重写的访问控制修饰符，不能小于父类被重写方法的访问控制修饰符（编译报错），在这种情况下，访问控制修饰符可以不同
③ 子类方法的返回值类型必须是父类被重写方法的返回值类型的子类，在这种情况下，返回值类型可以不同
（通常应用时，使方法签名一样，因为目的是“覆盖”）

【面试题】 overload 和override 的区别？
overload：方法的重载。 作用: 同一方法名下，可以有不同的功能
前提：使用在同一类中
1. 方法名相同
2. 参数列表不同（参数个数、参数类型）
注意：与返回值类型无关

override：方法的重写。 作用：覆盖父类
前提：子类继承父类后
① 出现一样的方法签名时（修饰符、返回值、方法名必须完全相同），创建子类时，调用该方法，实际运行的是子类的方法，如同将父类中的方法覆盖了一样。
② 子类重写的访问控制修饰符，不能小于父类被重写方法的访问控制修饰符（编译报错），在这种情况下，访问控制修饰符可以不同
③ 子类方法的返回值类型必须是父类被重写方法的返回值类型的子类，在这种情况下，返回值类型可以不同
（通常应用时，使方法签名一样，因为目的是“覆盖”）

3. 构造器
特点：
① 子类中所有的构造器的默认第一行第一句有一个隐式的无参的构造器：super()
        作用：调用父类无参构造器
        目的：因为子类继承了父类所有的属性、方法，所以要知道父类是如何为对象进行初始化的
② 父类中没有提供无参构造器，那么子类所有构造器中必须“显示”的调用父类中的有参构造器
③  this() 只能写在当前构造器中可执行代码的首行
   super() 只能写在当前构造器中可执行代码的首行
   二者不能同时出现
 */
public class ExtendsTest1 {
    public static void main(String[] args) {
        Student stu = new Student();
        stu.name = "张三";
        stu.age = 10;

        stu.eat();
        stu.sleep();

        System.out.println("stu.name = " + stu.name);
        System.out.println("stu.age = " + stu.age);

        Manager mg = new Manager();
        System.out.println("mg.age = " + mg.age);
        mg.getSuperAge();

    }
}

/**
 * 父类、超类、基类、SuperClass
 */
class Person{
    String name;
    int age=0;

    public void eat(){
        System.out.println("eat");
    }

    public void sleep(){
        System.out.println("sleep");
    }
}

/**
 * class A extends B
 * 子类可以继承父类中所有的属性和方法（除构造器）
 * extend 扩展：子类是父类的扩展；父有子有，父没有子可有；
 */
class Student extends Person{
    public void eat(){
        super.eat();//继承父类，可以不继承
        System.out.println("吃食堂");//子类重写或拓展
    }
}

class Employee extends Person{

}

/**
 * 子类的子类；多层继承
 */
class Manager extends Employee{
    int age = 18;

    public void getSuperAge(){
        System.out.println("mg.age:" + super.age);
    }
}