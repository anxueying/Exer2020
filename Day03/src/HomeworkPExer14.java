import java.util.Scanner;
class HomeworkPExer14{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入一个字符：");
		char c1 = scan.next().charAt(0);;
		if(c1 >= 48 && c1 <= 57){
			System.out.println(c1 + "是数字.");
		}else if((c1 >= 97 && c1 <= 122)||(c1 >= 65 && c1 <= 90)){
			System.out.println(c1 + "是字母.");
		}else{
			System.out.println(c1 + "非数字非字母的其他字符.");
		}
		
	}
}