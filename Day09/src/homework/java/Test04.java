package homework.java;

public class Test04 extends Father{
	private String name = "test";
	
	public static void main(String[] args) {
		Test04 test = new Test04();
		System.out.println(test.getName());//子类没有get，所以是father
	}
}
class Father {
	private String name = "father";

	public String getName() {
		return name;
	}
}