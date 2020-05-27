package designmode.decorator;

/**
 * @author Mrs.An Xueying
 * 2020/5/27 15:44
 * 装饰者设计模式：在已有的功能上增加额外的功能，并不影响原来的功能。
 * 即，用来扩展原来的功能。
 * 如：IO流  节点流--缓冲流--其他处理流 以节点流为基础进行装饰，层层包装
 *
 * 案例：手抓饼
 * 套餐1：饼
 * 套餐2：饼 + 鸡蛋
 * 套餐3：饼 + 鸡蛋 + 火腿
 * 套餐4：饼 + 鸡蛋 + 火腿 + 鸡蛋
 *
 *
 * 四要素：
 * 1. 抽象构件：接口，规定了构件等需要提供什么样的方法（饼）
 * 2. 具体构件：接口的实现类
 * 3. 抽象装饰：抽象类
 * 4. 具体装饰：抽象类的子类（比如鸡蛋，火腿）
 *
 */
public class DecoratorTest {
    public static void main(String[] args) {
        //创建饼的多态实例
        ICake icake = new Cake();
        //具体的装饰
        icake = new Egg(icake);
        //套餐2
        System.out.println(icake.name()+" : " + icake.price());

        //套餐4
        icake = new Egg(icake);
        icake = new Ham(icake);
        System.out.println(icake.name()+" : " + icake.price());

    }

}

/**
 * 抽象构件
 */
interface ICake{
    /**
     * 价格
     * @return
     */
    double price();

    /**
     * 内容
     * @return
     */
    String name();
}

/**
 * 具体构件
 */
class Cake implements ICake{
    @Override
    public double price() {
        return 5;
    }

    @Override
    public String name() {
        return "手抓饼";
    }
}

/**
 * 抽象装饰
 */
abstract class AbsDecorator implements ICake{
    private ICake iCake;

    public AbsDecorator(ICake iCake){
        this.iCake = iCake;
    }

    @Override
    public double price() {
        return iCake.price();
    }

    @Override
    public String name() {
        return iCake.name();
    }
}

/**
 * 鸡蛋
 */
class Egg extends AbsDecorator{

    public Egg(ICake iCake) {
        super(iCake);
    }

    @Override
    public double price() {
        return super.price()+2;
    }

    @Override
    public String name() {
        return super.name()+" + 鸡蛋";
    }
}

/**
 * 火腿
 */
class Ham extends AbsDecorator{

    public Ham(ICake iCake) {
        super(iCake);
    }

    @Override
    public double price() {
        return super.price()+3;
    }

    @Override
    public String name() {
        return super.name()+" + 火腿";
    }
}