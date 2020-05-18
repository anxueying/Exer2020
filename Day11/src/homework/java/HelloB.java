package homework.java;

class HelloA1{
	public HelloA1(){
		System.out.println("HelloA");//4
	}
	{
		System.out.println("I'm A Class");//3
	}
	static{
		System.out.println("static A");//1
	}
}

class HelloB1 extends HelloA1{
	public HelloB1(){
		System.out.println("HelloB");//6
	}
	{
		System.out.println("I'm B Class");//5
	}
	static{
		System.out.println("static B");//随着类的加载而加载2
	}

	public static void main(String[] args) {
		new HelloB1();
	}

}