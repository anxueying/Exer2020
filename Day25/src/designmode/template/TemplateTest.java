package designmode.template;

/**
 * @author Mrs.An Xueying
 * 2020/5/27 15:28
 * 模板设计模式：
 * 将共同的内容抽取到父类，不同的内容由子类实现
 */
public class TemplateTest {
    public static void main(String[] args) {
        Computer computer = new SubComputer();
        //此时，调用的是父类的runTime方法，子类的runCode方法
        computer.runTime();
    }
}


abstract class Computer{
    /**
     * 计算代码运行时间
     */
    public void runTime(){
        long start = System.currentTimeMillis();
        //运行的code放到方法中
        runCode();
        long end = System.currentTimeMillis();
        System.out.println("time=="+(end-start));
    }

    /**
     * 模板设计模式：抽象方法由子类重写
     */
    public abstract void runCode();
}

class SubComputer extends Computer{
    @Override
    public void runCode() {
        for (int i = 0; i < 100; i++) {
            if (i%2==0){
                System.out.println(i);
            }
        }
    }
}