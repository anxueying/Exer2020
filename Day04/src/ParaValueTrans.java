class ParaValueTrans{
	public static void main(String[] args){
		int a=1,b=2;
		System.out.println("main前：a="+a+"，b="+b);
		add(a,b);
		System.out.println("main后：a="+a+"，b="+b);
	}
	
	public static void add(int i,int j){
		System.out.println("add前：i="+i+"，j="+j);
		i += 1;
		j += 2;
		System.out.println("add后：i="+i+"，j="+j);
	}
}