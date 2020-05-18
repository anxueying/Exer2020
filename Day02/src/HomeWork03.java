//1)对下列代码，若有输出，指出输出结果。
class HomeWork03{
	public static void main(String[] args){
		int x = 4;
		int y = 1;
		if(x>2){
			if(y>2)
				System.out.println(x + y); //只有一行的话可以不用｛｝
				System.out.println("atguigu"); //相当于没有缩进，输出此行
		}else
			System.out.println("x is " + x); //只有一行的话可以不用｛｝
		
		
	    boolean b = true;
        if(b == false)  //如果写成if(b=false)能编译通过吗？如果能，结果是？
	    System.out.println("a");//不输出
        else if(b)
	    System.out.println("b");//输出b
        else if(!b)
	    System.out.println("c");//不输出
        else
	    System.out.println("d");//不输出
	
	    if(b = false)  //如果写成if(b=false)能编译通过吗？如果能，结果是？
	    System.out.println("a");//不输出
        else if(b)
	    System.out.println("b");//不输出
        else if(!b)
	    System.out.println("c");//输出c
        else
	    System.out.println("d");//不输出
	}
	
}
