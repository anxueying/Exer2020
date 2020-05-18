import java.util.Scanner;
class HomeworkPExer4{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入星期数：");
		int week = scan.nextInt();
		switch(week){
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
				System.out.println("星期"+week);
				break;
			case 7:
				System.out.println("星期日");
				break;
			default:
				System.out.println("非法参数");
				break;				
		}
		
	}
}