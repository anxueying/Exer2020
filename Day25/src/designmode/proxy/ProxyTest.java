package designmode.proxy;

import org.junit.Test;

import java.net.DatagramPacket;

/**
 * @author Mrs.An Xueying
 * 2020/5/28 14:52
 * 静态代理模式：只能替一个主题接口进行代理工作。
 * 如主题接口不同，代理工作相同，也需要编写两个代理类。
 */
public class ProxyTest {
    @Test
    public void test1(){
        TimeProxy tp = new TimeProxy(new GoodsDAO());
        tp.add();
        tp.select();
    }

    @Test
    public void test2(){
        TimeProxy tp = new TimeProxy(new UserDAO());
        tp.add();
        tp.select();
    }

}

/**
 *主题接口
 */
interface DAO{
    /**
     * 添加
     */
    void add();

    /**
     * 查询
     */
    void select();
}

/**
 * 被代理类
 */
class GoodsDAO implements DAO{
    @Override
    public void add() {
        System.out.println("添加商品");
    }

    @Override
    public void select() {
        System.out.println("查询商品");
    }
}

class UserDAO implements DAO{
    @Override
    public void add() {
        System.out.println("添加用户");
    }

    @Override
    public void select() {
        System.out.println("查询用户");
    }
}

/**
 * 代理类
 */
class TimeProxy implements DAO{
    /**
     * 被代理者：target
     */
    private DAO target;

    public TimeProxy(DAO target) {
        super();
        this.target = target;
    }

    @Override
    public void add() {
        long start = System.currentTimeMillis();
        target.add();
        long end = System.currentTimeMillis();
        System.out.println("时间差："+(end-start));
    }

    @Override
    public void select() {
        long start = System.currentTimeMillis();
        target.select();
        long end = System.currentTimeMillis();
        System.out.println("时间差："+(end-start));
    }
}