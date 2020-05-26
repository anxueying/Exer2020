package exer;

import javax.swing.plaf.metal.MetalIconFactory;

/**
 * @author Mrs.An Xueying
 * 2020/5/23 14:35
 */
public class BankAccount1 {
    public static void main(String[] args) {
        Account a = new Account(0,"admin");
        Thread t1 = new Thread(new Runnable(){

            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    a.saveMoney(1000);
                }
            }
        },"客户1");
        Thread t2 = new Thread(new Runnable(){

            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    a.saveMoney(1000);
                }
            }
        },"客户2");
        t1.start();
        t2.start();

    }
}

class Account{
    private double debt;
    private String name;

    public synchronized void saveMoney(double money){
        debt+=money;
        System.out.println("余额为："+debt);
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public Account(double debt, String name) {
        this.debt = debt;
        this.name = name;
    }

    public Account() {
    }
}
