package exer;

import java.util.ArrayList;

/**
 * @author Mrs.An Xueying
 * 2020/5/25 8:54
 * 创建两个线程，一个线程随机创建0~100的整数并存放到集合中，等集合中存放所有数据另一个线程输出该集合中的内容。
 */
public class PrintNum {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Object obj = new Object();
        //将随机整数存放到集合中
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 100; i++) {
                    list.add((int) (Math.random() * 100));
                }

            }
        }, "生成随机数");
        t.start();


        //打印集合中的数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //当前线程阻塞，等待t执行完毕当前线程再执行
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //用睡眠和锁配合，睡多了就浪费，睡少了可能没生成完
                System.out.println(list);
                System.out.println(list.size());

            }
        }, "输出集合内容").start();

    }
}
