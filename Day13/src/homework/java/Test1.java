package homework.java;

public class Test1 {
    String str = new String("good");
    char[] ch = { 't', 'e', 's', 't' };
    public void change(String str, char ch[]) {
        str = "test ok";
        ch[0] = 'g';
    }

    public static void main(String[] args) {
        Test1 ex = new Test1();
        ex.change(ex.str, ex.ch);// (good,test)-->(good,gest)
        System.out.print(ex.str + " and ");//good and gest
        System.out.println(ex.ch);
    }
}