package designmode.factory;

/**
 * @author Mrs.An Xueying
 * 2020/5/27 15:26
 * 工厂设计模式
 * 因为每一种车在创建对象的时候可能都需要不同的资源，所以不适合一个工厂生产多种车了
 * 那么我们可以针对每一种车型设计一个车的工厂。
 */
public class FactoryTest {
    public static void main(String[] args) {
        //先创建宝马车工厂
        BMWFactory bmwFactory = new BMWFactory();
        //宝马车的工厂造宝马对象
        Car2 car = bmwFactory.createCar();
        car.info();

        System.out.println("=================================");

        //创建BYD的工厂对象
        BYDFactory bydFactory = new BYDFactory();
        Car2 car1 = bydFactory.createCar();
        car1.info();

    }
}


interface Car2{
    void info();
}

class BMW2 implements Car2{
    @Override
    public void info() {
        System.out.println("宁可坐在BMW里哭，也不要坐在劳斯莱斯上面笑");
    }
}

class BYD2 implements Car2{
    @Override
    public void info() {
        System.out.println("BYD中国造，为BYD点赞");
    }
}

/**
 * 所有工厂的标准
 */
interface Facotry2{
    Car2 createCar();
}

class BYDFactory implements Facotry2{
    @Override
    public Car2 createCar() {
        //需要的是铁资源
        return new BYD2();
    }
}

class BMWFactory implements Facotry2{
    @Override
    public Car2 createCar() {
        //需要的是铝资源
        return new BMW2();
    }
}