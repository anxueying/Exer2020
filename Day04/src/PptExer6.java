class PptExer6{
	public static void main(String[] args){
		System.out.println(max(3,4));
		System.out.println(max(3.1,4.2));
		System.out.println(max(3.1,4.4,1.2));
	}
	
	public static int max(int i,int j){
		if(i>j){
			return i;
		}else{
			return j;
		}
	}
	
	public static double max(double i,double j){
		if(i>j){
			return i;
		}else{
			return j;
		}
	}
	
	public static double max(double i,double j,double k){
		return max(max(i,j),k);
	}
}