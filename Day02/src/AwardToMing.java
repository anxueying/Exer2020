import java.util.Scanner;//导包
class AwardToMing{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);//创建Scanner的对象
		System.out.print("请输入小明的成绩：");
		Double score = scan.nextDouble();//获取成绩
		
		if(score == 100){
			System.out.print("奖励一辆BMW");
		}else if((score >= 80) && (score < 100)){
			System.out.print("奖励一个台iphone15s");
		}else if ((score >= 60) && (score < 80)){
			System.out.print("奖励一本参考书");
		}else{
			System.out.print("什么奖励也没有");
		}
	}
}