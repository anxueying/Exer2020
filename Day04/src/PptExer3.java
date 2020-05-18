class PptExer3{
	public static void main(String[] args){
		int squar = method(2,3);
		System.out.println("矩形面积："+squar);
	}

	public static int method(int m,int n){
		for(int i=1;i<=m;i++){
			for(int j=1;j<=n;j++){
				System.out.print("*");
			}
			System.out.println();
		}
		return m*n;
		
	}
}