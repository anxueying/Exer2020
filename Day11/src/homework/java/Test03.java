package homework.java;

public class Test03 {
	public static void main(String[] args) {
		Base1 b1 = new Base1();
		Base1 b2 = new Sub1();//多态
	}
}

class Base1 {
	Base1() {
		method(100);
	}

	public void method(int i) {
		System.out.println("base : " + i);
	}
}

class Sub1 extends Base1 {
	Sub1() {
		super.method(70);
	}

	public void method(int j) {
		System.out.println("sub : " + j);
	}
}