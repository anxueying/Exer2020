class HomeworkPExer26{
	public static void main(String[] args){
		for(int i=1;i<=7;i++){//行
			for(int j=1;j<=7;j++){
				if(j==i||j==8-i){
					System.out.print("O");
				}else{
					System.out.print("*");
				}	
			}    //列
		System.out.println();	
		}
	}
}