package homework.java;

public class Test02 {
	public static void main(String[] args) {
		Father f = new Son();//多态
		/*
		创建对象时的一系列动作：
		new Son();

			1. new Father()
			调用this.print() 相当于找对象，再调用方法，所以调用的是Son方法
			System.out.println("Son.x = " + x);
			此时 Son中的x为0
			Son.x = 0;
			//x=20;

			2  new Son()
			Son.x = 30;
			//x=40;
		 */
		System.out.println(f.x);//father类初始化完成后，x=20
	}
}
class Father{
	int x = 10;
	public Father(){
		this.print();
		x = 20;
	}
	public void print(){
		System.out.println("Father.x = " + x);
	}
}
class Son extends Father{
	int x = 30;
	public Son(){
		this.print();
		x = 40;
	}
	public void print(){
		System.out.println("Son.x = " + x);
	}
}