import java.util.Scanner;//导包
class IfMarried{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);//创建Scanner的对象
		
		System.out.print("身高：（cm）");
		int height = scan.nextInt();
		
        System.out.print("财富：（千万）");
		double money = scan.nextDouble();
		
        System.out.print("帅否：（true/false）");
		boolean handsome = scan.nextBoolean();	
		
		if((handsome == true) &&(height>180)&&(money>1)){
			System.out.print("我一定要嫁给他！！！");
	    }else if((handsome == true) ||(height>180) || (money>1)){
			System.out.print("嫁吧，比上不足，比下有余。");
		}else if ((handsome == false) &&(height<=180)&&(money<=1)){
			System.out.print("不嫁！");
		}else{
			System.out.print("嫁吧，比上不足，比下有余。");
		}
	}
}