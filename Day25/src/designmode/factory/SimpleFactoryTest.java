package designmode.factory;

/**
 * @author Mrs.An Xueying
 * 2020/5/27 15:17
 * 工厂设计模式：
     * 1. 简单工厂
     * 2. 工厂设计模式
     * 3. 抽象工厂
 *
 * 遇到的问题:
     * 1.创建对象的工作比较复杂
     * 2.如果多个地方都需要该对象，代码会冗余
     * （Spring框架-会管理所有类的对象）
     * （忽略）3.自己创建对象需要自己管理，如果有工厂创建，那么对象的生命周期由工厂进行管理
     * （忽略）4.如果该类的对象，在整个工程中只需要一个 或者 需要多个
 *
 * 解决的问题：
     * 把对象的创建者与对象的使用者分离，把对象的创建统一到一个地方（工厂）
 */

public class SimpleFactoryTest {
    public static void main(String[] args) {
        Car car = CarFactory.createCar("BYD");
        car.info();
        Car car2 = CarFactory.createCar("BMW");
        car2.info();

        System.out.println("================================================");

        Car car3 = CarFactory.createCar2("designmode.factory.BYD");
        car3.info();
    }
}
/**
 * 简单工厂
 */
interface Car{
    void info();
}

class BMW implements Car{
    @Override
    public void info() {
        System.out.println("宁可坐在BMW里哭，也不要坐在劳斯莱斯上面笑");
    }
}

class BYD implements Car{
    @Override
    public void info() {
        System.out.println("BYD中国造，为BYD点赞");
    }
}

class WLHG implements Car{
    @Override
    public void info() {
        System.out.println("你看到了我的车尾吗？");
    }
}

class CarFactory{
    /**
     * 在这违反了开闭原则 - 修改了方法
     * @param name
     * @return
     */
    public static Car createCar(String name){
        if ("BMW".equals(name)){
            //创建对象需要很多资源
            return new BMW();
        }else if("BYD".equals(name)){
            //创建对象需要很多资源
            return new BYD();
        }else if("WLHG".equals(name)){
            return new WLHG();
        }else{
            return null;
        }
    }

    /**
     * 在这违反了开闭原则 - 修改了类
     */
    public static Car createBYD(){
        return new BYD();
    }
    public static Car createBMW(){
        return new BMW();
    }
    public static Car createWLHG(){
        return new WLHG();
    }

    /**
     * 解决方式二 ： 反射 - 通过反射获取对象
     */
    public static Car createCar2(String className){
        try {
            Class clazz = Class.forName(className);
            //创建对象 只能创建无参对象
            Object obj = clazz.newInstance();
            //可以判断该对象类型是否是Car---校验
            //返回车
            return (Car)obj;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}


