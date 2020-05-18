import java.util.Scanner;
class SwitchTest1{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入年：");
		int year = scan.nextInt();
		
		System.out.println("请输入月：");
		int month = scan.nextInt();
		
		System.out.println("请输入日：");
		int day = scan.nextInt();
		
		int february,result=0;
		if (((year%4 == 0) && (year%100!=0))||(year%400 == 0)){
			february = 29;
		}else{
			february = 28;
		}
	
		
		switch(month){
			case 12:
				result += 30 ;
			case 11:
				result += 31 ;
			case 10:
				result += 30 ;
			case 9:
				result += 31 ;
			case 8:
				result += 31 ;
			case 7:
				result += 30;
			case 6:	
				result += 31;
			case 5:
				result += 30;
			case 4:
				result += 31;
			case 3:
				result += february;
			case 2:
				result += 31;
			case 1:
				result += day;		
		}
		
		System.out.println(year + "年" + month + "月" + day + "日是当年的第" + result + "天" );
	}
}