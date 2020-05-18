package homework.java;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {

    Account acc = new Account(1122,20000,0.045);
    CheckAccount checkacc = new CheckAccount(1122,20000,0.045,5000);
    @Before
    public void before(){
        System.out.println("测试方法执行前……");
    }

    @Test
    public void AccountTest(){
        acc.withdraw(30000);
        System.out.println("您的账户余额为："+acc.getBalance());
        acc.withdraw(2500);
        acc.deposit(3000);
        System.out.println("您的账户余额为："+acc.getBalance());
        System.out.println("月利率为："+acc.getMonthlyInterest());
    }

    @Test
    public void CheckAccountTest(){
        checkacc.withdraw(5000);
        System.out.println("您的账户余额为："+checkacc.getBalance());
        System.out.println("您的可透支额为："+checkacc.getOverdraft());

        checkacc.withdraw(18000);
        System.out.println("您的账户余额为："+checkacc.getBalance());
        System.out.println("您的可透支额为："+checkacc.getOverdraft());

        checkacc.withdraw(3000);
        System.out.println("您的账户余额为："+checkacc.getBalance());
        System.out.println("您的可透支额为："+checkacc.getOverdraft());
    }

    @After
    public void after(){
        System.out.println("测试方法执行后……");
    }
}
