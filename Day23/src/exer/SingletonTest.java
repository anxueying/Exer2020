package exer;

import jdk.nashorn.internal.ir.IfNode;

/**
 * @author Mrs.An Xueying
 * 2020/5/25 8:42
 */
public class SingletonTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(SingletonModel.getInstance());
                }
            }).start();
        }
    }
}



class SingletonModel {
    /**
     * 单例设计模式（懒汉式）  改善为线程安全
     * 1. 私有化构造器
     * 2. 私有化创建静态实例，并赋值为null
     * （不用静态调不了方法）
     * 3. 创建公共静态方法，判断，为null则创建实例并返回；否则结束方法
     * 目的：延迟加载  flink、spark
     * 好处：节省内存
     */
    private SingletonModel(){};
    private static SingletonModel s = null;

    public static SingletonModel getInstance(){
        if (s==null){
            synchronized (SingletonModel.class){
                if(s==null){
                    s = new SingletonModel();
                }
            }
        }
        return s;
    }

}