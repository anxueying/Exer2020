package exer;

/*1、简述抽象类和接口的区别
*
*
* */

//3、编写单例设计模式
public class Preview {
}


//饿汉
class Singleton{
    //4. 不加static 无法直接调用get方法，而静态方法里不能有非静态成员，所以：
    //2. 类的内部创建对象(私有化）
    private static Singleton singleton = new Singleton(10);

    private int n;

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    //1. 构造器私有化
    private Singleton(int n) {
        this.n = n;
    }

    //3. 提供公共的get方法
    public  static Singleton getSingleton() {
        return singleton;
    }
}

//懒汉 存在多线程安全问题
class Singleton2{
    //2. 类的内部创建对象(私有化）
    private static Singleton2 singleton2 = null;

    private int n;

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    //1. 构造器私有化
    private Singleton2(int n) {
        this.n = n;
    }

    //3. 提供公共的get方法
    public  static Singleton2 getInstance() {
        if (singleton2==null){
            singleton2 = new Singleton2(10);
        }
        return singleton2;
    }
}
