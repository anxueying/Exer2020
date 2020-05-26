package exer;

/**
 * @author Mrs.An Xueying
 * 2020/5/23 15:48
 */
public class AlterPrint {
    public static void main(String[] args) {
        PrintNum printNum = new PrintNum();

        new Thread(printNum, "线程1").start();
        new Thread(printNum, "线程2").start();
    }
}


class PrintNum implements Runnable {
    private int count = 0;

    @Override
    public void run() {
        //在这里用循环
        while (true){
            //锁
            synchronized(this){
                //把代码块中正在睡觉的线程唤醒
                notify();
                if (count<=100){
                    System.out.println(Thread.currentThread().getName()+"="+count++);
                    try {
                        //线程输出完毕后就开始睡眠，同时要把锁释放掉
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    return;
                }
            }
        }
        //System.out.println(Thread.currentThread().getName() +"离开");
    }
}
