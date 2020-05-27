package homework;

/**
 * @author Mrs.An Xueying
 * 2020/5/28 2:20
 */
public class Juicer {
    public void run(Fruit f){
        f.squeeze();
    }
}

class Apple implements Fruit{
    @Override
    public void squeeze() {
        System.out.println("苹果汁");
    }
}

class Banana implements Fruit{
    @Override
    public void squeeze() {
        System.out.println("香蕉汁");
    }
}

class Orange implements Fruit{
    @Override
    public void squeeze() {
        System.out.println("橙汁");
    }
}
