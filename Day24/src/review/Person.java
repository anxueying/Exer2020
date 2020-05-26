package review;

/**
 * @author Mrs.An Xueying
 * 2020/5/26 9:02
 * 给Person类中的私有属性设置，并调用私有方法
 */
public class Person {
    public Person(){}

    private String name;

    private void show(String info){
        System.out.println(info);
    }

    /**
     * 验证是否赋值成功
     * @return
     */
    public String getName(){
        return name;
    }
}
