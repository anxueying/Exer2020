class HomeworkPExer8{
	public static void main(String[] args){
		int sum = 0,count = 0;
		for(int i=1;i<=100;i++){
			if(i%7 == 0){
				sum += i;
				count++;
			}
		}
		System.out.println("1~100之间是7的倍数的个数为："+count);
		System.out.println("1~100之间是7的倍数的整数的和为："+sum);
	}
}