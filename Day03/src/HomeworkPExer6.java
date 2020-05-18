import java.util.Scanner;
class HomeworkPExer6{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("请依次输入年月日：");
		int year = scan.nextInt();
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
				if(((year%4 == 0) && (year % 100 != 0)) || (year%400 ==0)){
					sum += 29;
				}else{
					sum += 28;
				}  	
			case 2:
				sum += 31;
			case 1:
				sum += day;		
		}
		System.out.println("这一天是当年的第"+sum+"天");
	}
}