import java.util.Scanner;
class BreakContinueExer2{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);	
		label:{
			int positive=0 ,negative =0;
			for(;;){
				System.out.print("请输入整数：");	
				int i = scan.nextInt();
				if(i==0){
					break label;
				}else if(i>0){
					positive++;
				}else{
					negative++;
				}
                System.out.println("正数："+positive+"，负数："+negative);				
			}
		}
		
	}
}