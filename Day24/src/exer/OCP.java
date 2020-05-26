package exer;

/**
 * @author Mrs.An Xueying
 * 2020/5/26 19:15
 * JavaBean : Java欠缺属性、事件、多重继承功能, Java Bean正是编写这套胶水代码的惯用模式或约定
     * 1、所有属性为private
     * 2、提供默认构造方法--public默认构造器（例如无参构造器）
     * 3、提供getter和setter
     * 4、实现serializable接口
 * 开闭原则 ： 一个软件的实体。如类，模块和函数应该 对扩展开放，对修改关闭。
 */

import java.net.InetAddress;

/**
 * JavaBean
 */
class Medicine{
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Medicine(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

/**
 * 病人接口
 */
interface IPatient{
    /**
     * 获取病人名字
     * @return string 病人名字
     */
    String getName();

    /**
     * 买药
     * @param medicine  药品实例
     * @return 花费
     */
    double pay(Medicine medicine);
}

/**
 * 医保1类病人
 */
class L1Patient implements IPatient{

    private String name;
    private final static int level = 1;

    @Override
    public String getName() {
        return this.name;
    }

    public L1Patient(String name) {
        this.name = name;
    }

    @Override
    public double pay(Medicine medicine) {
        return medicine.getPrice()*0.7;
    }
}

/**
 * 医保2类病人
 */
class L2Patient implements IPatient{

    private String name;
    private final static int level = 2;

    public L2Patient(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double pay(Medicine medicine) {
        return medicine.getPrice()*0.8;
    }
}

/**
 * 医保3类病人
 */
class L3Patient implements IPatient{

    private String name;
    private final static int level = 3;

    public L3Patient(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double pay(Medicine medicine) {
        return medicine.getPrice()*0.9;
    }
}

/**
 * 医院
 */
class Hospital{
    /**
     * 医院库存药品
     */
    private Medicine medicine = new Medicine("阿司匹林", 180);

    /**
     * 医院卖药
     * @param patient 买药的病人
     */
    public void saleMedicine(IPatient patient){
        double money = patient.pay(medicine);
        System.out.println(patient.getName()+"买了"+medicine.getName()+"花费"+money+"元");
    }
}

public class OCP {
    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        L1Patient xiaoMing = new L1Patient("小明");
        L2Patient xiaoFang = new L2Patient("小芳");
        L3Patient xiaoLi = new L3Patient("小李");
        hospital.saleMedicine(xiaoMing);
        hospital.saleMedicine(xiaoFang);
        hospital.saleMedicine(xiaoLi);

    }
}


