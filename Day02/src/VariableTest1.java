class VariableTest1{
	public static void main(String[] args){
		double var1 = 2;
		double var2 = 1.9;
		//因为后面var2的值要变了，所以在此处声明str
		String str = var1 + "/" + var2 + "=";
		//按题目要求赋值
		var2 = var1/var2;
		//输出结果
		System.out.println(str+var2); 
	}
}