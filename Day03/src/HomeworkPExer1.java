import java.util.Scanner;
class HomeworkPExer1{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("请输出一个小写英文字母：");
		char alphabet = scan.next().charAt(0);
		switch(alphabet){
			case 'a':
				alphabet = 'A';
				System.out.println(alphabet);
				break;
			case 'b':
				alphabet = 'B';
				System.out.println(alphabet);
				break;
			case 'c':
				alphabet = 'C';
				System.out.println(alphabet);
				break;
			case 'd':
				alphabet = 'D';
				System.out.println(alphabet);
				break;
			case 'e':
				alphabet = 'E';
				System.out.println(alphabet);
				break;
			default:
				System.out.println("other");
				break;
		}
	}
}