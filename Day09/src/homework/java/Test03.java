package homework.java;

public class Test03 {
	public static void main(String[] args) {
		Father1 f = new Father1();
		Child1 c = new Child1();
	}
}
class Father1 {
	public Father1(){
		System.out.println("father create");
	}
}
class Child1 extends Father1{
	public Child1(){
		System.out.println("child create");
	}
}