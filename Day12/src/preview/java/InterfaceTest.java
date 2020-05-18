package preview.java;

public class InterfaceTest {

    public static void main(String[] args) {
        //System.out.println(Flyer.NUM);

        Bird bird = new Bird();//本态
        bird.fly();
        bird.run();
        System.out.println(AA.NUM);
        System.out.println(BB.NUM);

        /*Flyer f = new Bird();//接口支持多态
        f.fly();//虚拟方法调用（动态绑定）

        Runner r = new Bird();//多态
        r.run();*/
    }

}

interface AA{

    public static final int NUM = 11;

    void show();
}

interface BB{

    int NUM = 22;

    void show();
}

interface Flyer extends AA, BB{

    //int NUM = 100;//public static final

    public void fly();//public abstract

    //void show();
}

interface Runner{

    public void run();
    public void eat();
}

class Animal1{
    public void eat(){ }
    public void run(){ System.out.println("鸟儿蹦蹦哒哒的跑");}
}

class Bird extends Animal1 implements Flyer, Runner{

    public void fly(){
        System.out.println("鸟儿煽动翅膀飞翔");
    }

 /*   public void run(){//此时不实现接口的类也可
        System.out.println("鸟儿蹦蹦哒哒的跑");
    }*/

    public void show(){

    }
}

abstract class Plane implements Flyer{
    //public abstract void fly();
}
