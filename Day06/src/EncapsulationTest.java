//封装：该隐藏隐藏，该暴露暴露
/**
访问控制修饰符
public: 公共的，可用于修饰属性、方法、类。在任何地方都可以访问
private：私有的，可用于修饰属性、方法。只能在本类中访问
 * @author Lenovo
 */
public class EncapsulationTest {
    public static void main(String[] args) {
        Animal ani = new Animal();
        ani.name = "大象";
        //ani.legs = -7;不符合常识
        //用方法赋值
        ani.setLegs(-4);

        System.out.println("名称："+ani.name+"，腿的个数："+ani.getLegs());
    }
}

/*
* 封装的步骤
* 1. 属性私有化
* 2. 提供公共的get/set方法
* */

class Animal{
    //隐藏属性，暴露方法
    String name;//未隐藏
    private int legs; //隐藏

    //功能语句-方法 设置属性值
    public void setLegs(int L){  //暴露
        if (L>=0&&L%2==0){
            legs = L;
        } //否则为0
    }

    //功能语句-方法 获取属性值
    public int getLegs(){  //暴露
        return legs;
    }
}