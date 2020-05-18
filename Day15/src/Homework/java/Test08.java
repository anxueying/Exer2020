package Homework.java;

public class Test08 {
    public static void main(String[] args) {
        try {
            Person p1 = new Person("张三",-10);
        }catch (NoLifeValueException noLife){
            noLife.getMessage();
        }finally {
            System.out.println("p1运行完毕");
        }

        Person p2 = new Person();
        p2.setLifeValue(10);
        System.out.println(p2);
    }
}
