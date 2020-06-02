package exer;

public class Exam extends Db{
    public static void main(String[] args) {
        B b = new B();
        b.test();
    }
    public  void test(){
        System.out.print("A");
    }
}


class B extends Exam{
    public void test(){
        super.test();
        System.out.println("B");
    }
}

class  Db{
    Db(){
        int a = 1;
        System.out.println(a);
    }
    public void show(){
        System.out.println("父");
    }
}

class  Db1 extends Db{
    public void show(){
        System.out.println("zi");
    }
    public void method(){
        System.out.println("zi2");
    }
}
