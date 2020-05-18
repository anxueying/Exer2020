import java.util.Scanner;//导包
class SortTest{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);//创建Scanner的对象
		System.out.print("请输入变量num1：");
		int num1 = scan.nextInt();
		System.out.print("请输入变量num2：");
		int num2 = scan.nextInt();
		System.out.print("请输入变量num3：");
		int num3 = scan.nextInt();
		
		if(num1 > num2){
			//num1 > num2
			if(num1 > num3){
				//num1 > num2 & num1 > num3
				if(num2>num3){
					System.out.print("从小到大依次为："+num3+"，"+num2+"，"+num1);
				}else{
					System.out.print("从小到大依次为："+num2+"，"+num3+"，"+num1);
				}
		    }
			else{
				//num1 > num2 & num1 <= num3				
				System.out.print("从小到大依次为："+num2+"，"+num1+"，"+num3);
			}	
		}else{
			//num1 <= num2
			if(num1 > num3){
				//num1 <= num2 & num1 > num3
				System.out.print("从小到大依次为："+num3+"，"+num1+"，"+num2);
			}else{
				//num1 <= num2 & num1 <= num3
				if(num2>num3){
					System.out.print("从小到大依次为："+num1+"，"+num3+"，"+num2);
				}else{
					System.out.print("从小到大依次为："+num1+"，"+num2+"，"+num3);
				}
			}
		}
	}
}