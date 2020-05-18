import java.util.Scanner;
class HomeworkPExer5{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入学生成绩：");
		int score = scan.nextInt();
		switch(score/10){
			case 10:
			case 9:
				System.out.println("等级：A");
				break;
			case 8:
			case 7:
				System.out.println("等级：B");
				break;
			case 6:
				System.out.println("等级：C");
				break;
			default:
				System.out.println("等级：D");
				break;				
		}
		
	}
}