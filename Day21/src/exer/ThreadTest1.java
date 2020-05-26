package exer;

/**
 * @author Mrs.An Xueying
 * 2020/5/22 10:55
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        /**
         * 编程规范 尽量不要显示创建线程，要使用线程池
         */
        new Thread(){
            @Override
            public void run(){
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName()+"+++"+i);
                }
            }
        }.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"---"+i);
        }


    }
}


class MyThread extends Thread{
    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"==="+i);
        }
    }
}
