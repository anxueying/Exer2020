import java.util.Scanner;
class SwitchTest2{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入成绩：");
		int score = scan.nextInt();
		switch(score/10){
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				System.out.print("等级：D");
				break;
			case 6:
				System.out.print("等级：C");
				break;
			case 7:
			case 8:
				System.out.print("等级：B");
				break;		
			case 9:
				System.out.print("等级：A");
				break;		
			default:
				System.out.print("等级：A");
				break;
		}
	}
}