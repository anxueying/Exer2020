class HomeworkPExer27{
	public static void main(String[] args){
		for(int i=1;i<=5;i++){//行
			for(int j=1;j<=5-i;j++){
				System.out.print("\t");
			}    //列
			
			for(int k=1;k<=2*i-1;k++){
				System.out.print("*\t");
			}
		System.out.println();	
		}
		for(int i=6;i<=9;i++){//行
			for(int j=1;j<=i-5;j++){
				System.out.print("\t");
			}    //列
			
			for(int k=1;k<=2*(10-i)-1;k++){
				System.out.print("*\t");
			}
		System.out.println();	
		}
	}
}