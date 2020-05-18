import java.util.Scanner;
class HomeworkPExer2{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入学生成绩：");
		int score = scan.nextInt();
		switch(score/60){
			case 0:
				System.out.println("不合格");
				break;
			case 1:
				System.out.println("合格");
				break;
			default:
				System.out.println("不合格");
				break;				
		}
		
	}
}