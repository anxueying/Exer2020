package homework.java;

public class FinalTest {
    public static void main(String[] args) {

    }

    public static int add(final Number1 NUM){
        return NUM.n;
    }
}

class Number1{
    final int n ;

    public Number1(int n) {
        this.n = n;
    }

    public void getDetail(){
        System.out.println(n);
    }
}

class Number2 extends Number1{

    Number2() {
        super(10);
    }

    public void getDetail(){
        System.out.println(n+1);
    }
}