package preview.java;

/*
编写测试类创建对象与 Equipment 关联。
调用 equals() 与 toString() 方法
 */
public class ProgrammerTest {
    public static void main(String[] args) {
        Programmer p1 = new Programmer("张三",19,new Equipment("联想","白色"));
        Programmer p2 = new Programmer("张三",30,new Equipment("DELL","黑色"));
        System.out.println("p1.equals(p2) = " + p1.equals(p2));
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
    }
}
