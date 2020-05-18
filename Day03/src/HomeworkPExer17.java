import java.util.Scanner;
class HomeworkPExer17{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入年份：");
		int year = scan.nextInt();
			
		String constel = "";
		switch(year%12){
			case 1:
				constel = "鸡";
				break;
			case 2:
				constel = "狗";
				break;
			case 3:
				constel = "猪";
				break;
			case 4:
				constel = "鼠";
				break;
			case 5:
				constel = "牛";
				break;
			case 6:
				constel = "虎";
				break;
			case 7:
				constel = "兔";
				break;
			case 8:
				constel = "龙";
				break;
			case 9:
				constel = "蛇";
				break;
			case 10:
				constel = "马";
				break;
			case 11:
				constel = "羊";
				break;
			case 12:
				constel = "猴";
				break;
			default:
				constel = "猴";
				break;
		}
		
		
		System.out.println(year+"年是"+constel+"年");

	}
}