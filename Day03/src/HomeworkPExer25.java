class HomeworkPExer25{
	public static void main(String[] args){
		for(int i=1;i<=5;i++){//行
			for(int j=0;j<=i-1;j++){
				System.out.print(" ");
			}    //列
			for(int k=1;k<=6-i;k++){
					System.out.print("* ");
				}
		System.out.println();	
		}
	}
}