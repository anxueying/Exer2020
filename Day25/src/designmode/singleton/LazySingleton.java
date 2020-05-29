package designmode.singleton;

/**
 * @author Mrs.An Xueying
 * 2020/5/28 17:16
 * 优势：不用不创建，用时再创建
 * 劣势：需要考虑线程安全问题
 */
public class LazySingleton {
    private static LazySingleton instance;
    private LazySingleton(){

    }
    public static LazySingleton getInstance(){
        if(instance == null){
            synchronized (LazySingleton.class) {
                if(instance == null){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
