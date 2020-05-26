package exer;

import java.util.Random;

/**
 * @author Mrs.An Xueying
 * 2020/5/23 16:22
 */
public class ProductTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        new Thread(new Runnable(){

            @Override
            public void run() {
                while (true){
                    //调用生产方法
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clerk.addProduct();
                }
            }
        },"生产者").start();

        new Thread(new Runnable(){

            @Override
            public void run() {
                while (true){
                    //调用消费方法
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clerk.getProduct();
                }
            }
        },"消费者").start();
    }
}

class Clerk{
    /**
     * 馒头数量
     */

    private int number = 0;

    /**
     * 生产
     */

    public synchronized void addProduct(){
        //馒头数量达到20就不生产了
        if (number>=20){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            //继续生产馒头
            number++;
            System.out.println(Thread.currentThread().getName()+"生产了"+number+"个馒头");
            //通知消费者吃
            notifyAll();
        }
    }

    /**
     * 消费
     */
    public synchronized void getProduct(){
        //馒头数量达到0就不消费了
        if (number<=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            //继续消费馒头
            number--;
            System.out.println(Thread.currentThread().getName()+"消费了"+number+"个馒头");
            //通知生产者生产
            notifyAll();
        }
    }
}
