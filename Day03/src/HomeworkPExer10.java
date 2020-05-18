class HomeworkPExer10{
	public static void main(String[] args){
		int sumFor= 0,sumWhile= 0,sumDoWhile = 0;
		for(int i=1;i<=100;i++){
			if(i%2==0){
				sumFor += i;
			}
		}
		
		int numWhile= 1, numDoWhile = 1;
		while(numWhile<=100){
			if(numWhile%2==0){
				sumWhile += numWhile;
			}
			numWhile++;
		}

		do{
			if(numDoWhile%2==0){
				sumDoWhile += numDoWhile;
			}
			numDoWhile++;
		}while(numDoWhile<=100);
			
		System.out.println(sumFor);
		System.out.println(sumWhile);
		System.out.println(sumDoWhile);
	}
}