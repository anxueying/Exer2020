package homework.java;

public class Test11 {
	static int x, y, z;

	static {
		int x = 5;//局部变量
		x--;//局部变量-1，就近原则
	}

	static {
		x--;//类变量-1
	}

	public static void main(String[] args) {
		System.out.println("x=" + x); //-1
		z--;//-1
		System.out.println("z = " + z);
		method();
		System.out.println("result:" + (z + y + ++z));//1+0+2
		System.out.println("y = " + y);
	}

	public static void method() {
		y = z++ + ++z;
		//y = z++;  y=-1 ,z=0
		//y += ++z;  y = -1 +1 =0 , z=1
	}
}