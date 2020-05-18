class PptExer5{
	public static void main(String[] args){
		mOL(2);
		mOL(3,4);
		mOL("test");
	}
	
	public static void mOL(int i){
		System.out.println(i*i);
	}
	
	public static void mOL(int i,int j){
		System.out.println(i*j);
	}
	
	public static void mOL(String i){
		System.out.println(i);
	}
}