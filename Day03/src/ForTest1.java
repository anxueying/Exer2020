
class ForTest1{
	public static void main(String[] args){
		int sum = 0;
		int count = 0;
		for(int i=1;i <= 100;i++){
			if (i%7 == 0){
				sum += i;
				count += 1;
			}
		}
		System.out.println(sum);
		System.out.println(count);
	}
}