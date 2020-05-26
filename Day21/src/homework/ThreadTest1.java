package homework;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Mrs.An Xueying
 * 2020/5/22 21:51
 * 编写程序，在main方法中创建一个线程。线程每隔一定时间（200ms以内的随机时间）
 * 产生一个0-100之间的随机整数，打印后将该整数放到集合中；
 * 共产生100个整数，全部产生后，睡眠30秒，然后将集合内容打印输出；
 * 在main线程中，唤醒上述睡眠的线程，使其尽快打印集合内容。
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        RandomNumSleep randomNumSleep = new RandomNumSleep();
        Thread t = new Thread(randomNumSleep);
        t.start();

        while (true){
            t.interrupt();
            if (!t.isAlive()){
                return;
            }
        }


    }
}


class RandomNumSleep implements Runnable{
    private ArrayList<Integer> list = new ArrayList<>();
    private int total = new Random().nextInt(200);
    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            //随机睡眠
            try {
                Thread.sleep(total);
            } catch (InterruptedException e) {
               // e.printStackTrace();
            }
            Integer num = new Random().nextInt(100);
            System.out.println(num);
            list.add(num);
        }

        //数组睡眠
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }

        System.out.println(list);
    }
}