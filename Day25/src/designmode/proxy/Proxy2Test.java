package designmode.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Mrs.An Xueying
 * 2020/5/28 15:07
 * 动态代理：可以替不同的主题接口进行代理工作，只要他们的代理工作内容相同。
 *
 * 步骤：
 * （1）主题接口
 * （2）被代理类
 * （3）动态代理的代理工作处理器
     * 要求必须实现：
     * java.lang.reflect.InvocationHandler接口，
     * 重写Object invoke(Object proxy, Method method,Object[] args)
 * （4）创建代理类对象
     * java.lang.reflect.Proxy类型的静态方法
     * newProxyInstance(ClassLoader loader, Class[] interfaces，InvocationHandler h )
 * （5）调用对用的方法
 */
public class Proxy2Test {
    @Test
    public void test1(){
        //被代理对象
        YongHuDAO sd = new YongHuDAO();
        //被代理者的类加载器对象
        ClassLoader loader = sd.getClass().getClassLoader();
        //被代理者实现的接口们
        Class<?>[] interfaces = sd.getClass().getInterfaces();
        //代理工作处理器对象
        TimeInvocationHandler h = new TimeInvocationHandler(sd);

        //创建代理类及其对象
        //proxy是代理类的对象，代理类是编译器自动编译生成的一个类
        Object proxy = Proxy.newProxyInstance(loader, interfaces, h);

        //这里强转的目的是为了调用增、删、改、查的方法
        //为什么这里强转可以成功了，因为代理类与被代理类实现了相同的主题接口
        DBDAO d = (DBDAO) proxy;
        d.add();
        d.update();
        d.delete();
        d.select();

    }

}

/**
 * 动态代理：处理器
 */
class TimeInvocationHandler implements InvocationHandler{
    private Object target;

    public TimeInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();

        //被代理对象的xx方法被调用
        /*
         * 没有反射：  被代理对象.xx方法(args实参列表)
         * 有了反射：  方法对象.invoke(被代理对象，args实参列表)
         */
        Object returnValue = method.invoke(target, args);

        long end = System.currentTimeMillis();
        System.out.println(method.getName() + "方法运行时间：" + (end-start));

        return returnValue;
    }

}

/**
 * 主题接口
 */
interface DBDAO{
    /**
     * 添加
     */
    void add();

    /**
     * 修改
     */
    void update();

    /**
     * 删除
     */
    void delete();

    /**
     * 查询
     */
    void select();
}

//被代理类1
class ShangPinDAO implements DBDAO{
    @Override
    public void add(){
        System.out.println("添加商品");
    }

    @Override
    public void update() {
        System.out.println("修改商品");
    }

    @Override
    public void delete() {
        System.out.println("删除商品");
    }

    @Override
    public void select() {
        System.out.println("查询商品");
    }
}
//被代理类2
class YongHuDAO implements DBDAO{
    @Override
    public void add(){
        System.out.println("添加用户");
    }

    @Override
    public void update() {
        System.out.println("修改用户");
    }

    @Override
    public void delete() {
        System.out.println("删除用户");
    }

    @Override
    public void select() {
        System.out.println("查询用户");
    }
}