package exam;

/**
 * @author Mrs.An Xueying
 * 2020/5/28 10:03
 * 3、编写一个懒汉式单例设计模式
 */
public class Test23 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
    }
}

class Singleton{
    private Singleton() { };
    private static Singleton instance;
    public static Singleton getInstance(){
        if (instance==null){
            synchronized (Singleton.class){
                if (instance==null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}