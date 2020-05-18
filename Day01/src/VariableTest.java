/**

1. 练习变量作用域
2. 声明变量
3. 变量赋值
4. 声明变量并赋值
5. 练习位运算符

*/
class VariableTest{
	public static void main(String[] args){
		int i1 = 1;
		int i2,i3;
		i2 = 12;
		i3 = i2<<2;
		System.out.println(i3);
		System.out.println(-12>>2);
		System.out.println(~12);
		System.out.println(12|2);
		System.out.println(12&2);
		System.out.println(12^2);
		System.out.println(12>>>2);
	}
}