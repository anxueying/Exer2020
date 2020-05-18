class HomeworkPExer9{
	public static void main(String[] args){
		
		for(int i=100;i<=999;i++){
			int hundred = i/100,decade = i/10%10,unit = i%10;
			if(hundred*hundred*hundred+decade*decade*decade+unit*unit*unit==i){
				System.out.println(i);
			}
		}
	}
}