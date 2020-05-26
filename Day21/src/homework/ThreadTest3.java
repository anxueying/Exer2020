package homework;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Mrs.An Xueying
 * 2020/5/22 15:15
 *
 *
 * 在main方法中创建并启动两个线程。第一个线程循环随机打印100以内的整数，直到第二个线程从键盘读取了“Q”命令。
 *
 * 知识点 ：
 * interrupt ： 当调用t线程的interrupt方法时，如果该线程正在执行(wait(),sleep(),join())而导致的阻塞状态时。
 *  那么该方法会抛出InterruptedException。同时会清除杀死该线程的标记。
 *
 */
public class ThreadTest3 {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "：" + new Random().nextInt(100) + ";");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        });
        t.start();

        new Thread(new Runnable(){

            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                System.out.print("请输入：");
                String str = scanner.next();
                if (str.equalsIgnoreCase("Q")) {
                    t.interrupt();
                }
            }
        }).start();
    }
}