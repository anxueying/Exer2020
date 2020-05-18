class PptExer2{
	public static void main(String[] args){
		int squar = method();
		System.out.println("矩形面积："+squar);
	}

	public static int method(){
		for(int i=1;i<=10;i++){
			for(int j=1;j<=8;j++){
				System.out.print("*");
			}
			System.out.println();
		}
		return 8*10;
		
	}
}