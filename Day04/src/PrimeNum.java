//打印1-100以内的质数
class PrimeNum{
	public static void main(String[] args){
		for(int i =2;i<=100;i++){
			boolean flag = true;
			for(int j=2;j<i;j++){
				//如可以被整除，则肯定不是
				if(i%j==0){
					flag = false;
				}
			}
			//如果flag是true则说明没进入if
			if(flag){
				System.out.println(i);
			}
		}
	}
}