class ForExer1{
	public static void main(String[] args){
		int sumFor = 0;
		for(int i=0;i<=100;i++){
			if(i%2 ==0){
				sumFor += i;
			}			
		}
		System.out.println(sumFor);
		
		int k =0 ,sumWhile= 0;
		while(k<=100){
			if(k%2 ==0){
				sumWhile += k;
			}			
			k++;
		}
		System.out.println(sumWhile);

		int t =0 ,sumDoWhile= 0;		
		do{
			if(t%2 ==0){
				sumDoWhile += t;
			}
			t++;
		}while(t<=100);
		System.out.println(sumDoWhile);		
		
	}
}