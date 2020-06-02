package exer;

public class Demo {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "xyz";
        show(s1,s2);
        System.out.println(s1+"---"+s2);
    }

    public static void show(String s1,String s2){
        //局部变量
        s1 = s2 + s1 +"Q";
        s2 = "W" + s1;
        System.out.println(s1);
        System.out.println(s2);
    }
}
