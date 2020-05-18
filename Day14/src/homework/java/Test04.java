package homework.java;

public class Test04 {
	static int i = 0;
	public static void main(String[] args) {
		System.out.println(test());
	}

	public static int test(){
		try{
			return ++i;//(1)先自增（2）把i的值放到操作数栈（3）走finally
		}finally{
			return ++i;//(4)再自增（5）又取i的值放到操作数栈（4）结束方法
		}
	}
}