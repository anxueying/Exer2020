package exer;

/**
 * @author Mrs.An Xueying
 * 2020/5/22 16:31
 */
public class SellTicket {
    public static void main(String[] args) {
        Wicket wicket = new Wicket();

        Thread t1 = new Thread(wicket);
        Thread t2 = new Thread(wicket);
        Thread t3 = new Thread(wicket);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}

class Wicket implements Runnable{
    private int tickNum = 10;
    @Override
    public void run() {
        //让三个线程不停卖票直到卖完
        while (tickNum>0){
            System.out.println(Thread.currentThread().getName()+"：" +tickNum--);
        }
    }
}
