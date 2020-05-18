package homework.java;

public class Test06 {
	public static void main(String[] args) {
		Base1 b = new Sub1();//多态
		System.out.println(b.x);//b.x =1
	}
}
class Base1{
	int x = 1;
}
class Sub1 extends Base1{
	int x = 2;
}