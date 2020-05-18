package exer.java;

public class Test {
     public Test() {
          Inner s1 = new Inner();
          s1.a = 10;
          Inner s2 = new Inner();
          s2.a = 20;
          Test.Inner s3 = new Test.Inner();
          System.out.println(s3.a);//5
     }

     class Inner {
          public int a = 5;
     }

     public static void main(String[] args) {
          Test t = new Test();
          Inner r = t.new Inner();
          System.out.println(r.a);//5
     }
}
