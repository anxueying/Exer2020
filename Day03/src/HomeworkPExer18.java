import java.util.Scanner;
class HomeworkPExer18{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("请依次输入月日：");
		int month = scan.nextInt();
		int day = scan.nextInt();
		int sum = 0;
		switch(month){
			case 12:
				sum += 30;
			case 11:
				sum += 31;
			case 10:
				sum += 30;
			case 9:
				sum += 31;
			case 8:
				sum += 31;
			case 7:
				sum += 30;
			case 6:
				sum += 31;
			case 5:
				sum += 30;
			case 4:
				sum += 31;
			case 3:
				sum += 28;
			case 2:
				sum += 31;
			case 1:
				sum += day;		
		}
		
		switch(sum%7){
			case 6:
				System.out.println("星期天");
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 0:
				System.out.println("星期"+(sum%7+1));
		}
		
	}
}