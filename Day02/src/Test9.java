class Test9{
	public static void main(String[] args){
		int year = 2018;
		boolean if_leap = ((year % 4 == 0)&&(year % 100 != 0)) || (year % 400 == 0);
		System.out.println(year + ((if_leap == true)?"是":"不是") + "闰年");
	}
}