package homework.java;

public class Test15 {
    public static void main(String[] args) {
        Woman girl1 = new Woman();
        Man boy1 = new Man();
        Man boy2 = new Man();
        meeting(boy1,girl1,boy2);
    }

    public static void meeting(Person... ps){
        for (Person p : ps) {
            p.eat();
            p.toilet();
            if (p instanceof Man){
                Man man = (Man)p;
                man.smoke();
            }
            if (p instanceof Woman){
                Woman woman = (Woman)p;
                woman.makeup();
            }

            System.out.println("————————");
        }
    }
}

class Person{
    public void eat(){
        System.out.println("吃饭");
    }

    public void toilet(){
        System.out.println("上洗手间");
    }
}

class Man extends Person{
    @Override
    public void eat() {
        System.out.println("吃大碗宽面");;
    }

    @Override
    public void toilet() {
        System.out.println("站着上厕所");
    }

    public void smoke(){
        System.out.println("打印抽烟");
    }
}

class Woman extends Person{
    @Override
    public void eat() {
        System.out.println("吃蔬菜沙拉");;
    }

    @Override
    public void toilet() {
        System.out.println("优雅的去洗手间");
    }

    public void makeup(){
        System.out.println("化妆");
    }
}
