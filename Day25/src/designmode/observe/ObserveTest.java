package designmode.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mrs.An Xueying
 * 2020/5/27 16:26
 * 观察者模式：定义了对象之间一对多的关系。
 * 即，让多个观察者的对象同时监听被观察者的某一个动作。
 *
 * 概念：
 * 观察者
 * 被观察者
 */
public class ObserveTest {
    public static void main(String[] args) {
        Girl girl = new Girl("迪丽热巴");

        Boy boy1 = new Boy("张艺兴","宝贝我爱你");
        Boy boy2 = new Boy("鹿晗","我好后悔");
        Boy boy3 = new Boy("杨紫","快点雇水军黑她");

        //订阅(在被观察者中注册观察者）
        girl.register(boy1);
        girl.register(boy2);
        girl.register(boy3);

        //发送事件
        girl.event();

    }
}

class Girl{
    private String name;

    public Girl(String name) {
        this.name = name;
    }

    private List<Boy> boys = new ArrayList<Boy>();

    public void register(Boy boy){
        boys.add(boy);
    }

    public void remove(Boy boy){
        boys.remove(boy);
    }

    public void event(){
        for (Boy boy : boys) {
            boy.reciever(name + "和张艺兴官宣了");
        }
    }
}

class Boy{
    public String name;
    public String info;

    public Boy(String name,String info) {
        this.name = name;
        this.info = info;
    }

    public void reciever(String event){
        System.out.println(event+"：  \t"+ name +"："+ info);
    }
}
