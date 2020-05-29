package designmode.singleton;

/**
 * @author Mrs.An Xueying
 * 2020/5/28 17:13
 * 饿汉式单例设计模式:
 * 类初始化时，直接创建对象。
 *
 * 优势：因为Java的类加载和初始化的机制可以保证线程安全，所以这类形式的单例设计模式不存在线程安全问题。
 *
 * 劣势：不管你暂时是否需要该实例对象，都会创建，使得类初始化时间加长。
 */
public class HungrySingleton {
    private HungrySingleton(){}
    private static HungrySingleton INSTANCE = new HungrySingleton();
    public static HungrySingleton getInstance(){
        return INSTANCE;
    }
}