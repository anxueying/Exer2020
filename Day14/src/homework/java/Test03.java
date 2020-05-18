package homework.java;

public class Test03 {
	{
		System.out.println("a");
	}

	static {
		System.out.println("b");
	}

	Test03(){
		System.out.println("c");
	}

	public static String getOut(){
		try{
			return "1";
		}catch(Exception e){
			return "2";
		}finally{
			return "3";
		}
	}
	public static void main(String[] args) {
		new Test03();
		System.out.println(getOut());
	}
}