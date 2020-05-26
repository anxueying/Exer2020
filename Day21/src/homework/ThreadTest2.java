package homework;

/**
 * @author Mrs.An Xueying
 * 2020/5/22 11:17
 * 创建两个线程，
 * 一个线程打印100以内的偶数，
 * 一个线程打印100以内的奇数
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        new EvenNum().start();
        new UnevenNum().start();
    }
}

class EvenNum extends Thread{
    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            if (i%2==0){
                System.out.println(i);
            }
        }
    }
}

class UnevenNum extends Thread{
    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            if (i%2!=0){
                System.out.println(i);
            }
        }
    }
}