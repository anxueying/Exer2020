package homework.java;

public class Exercise1 {
    public static void main(String[] args) {
        B b = new B();
        b.showA();
        b.showB();
    }
}

interface A{
    abstract void showA();
    default void showB(){
        System.out.println("BBBB");
    };
}

class B implements A{
    public void showA(){
        System.out.println("AAAA");
    }
}
