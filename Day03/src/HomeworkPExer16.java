import java.util.Scanner;
class HomeworkPExer16{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入月份：");
		int month = scan.nextInt();
		System.out.print("请输入日期：");
		int day = scan.nextInt();		
		String constel = "";
		switch(month){
			case 1:
				if(day>19){
					constel = "水瓶座";
				}else{
					constel = "摩羯座";
				}
				break;
			case 2:
				if(day>18){
					constel = "双鱼座";
				}else{
					constel = "水瓶座";
				}
				break;
			case 3:
				if(day>20){
					constel = "白羊座";
				}else{
					constel = "双鱼座";
				}
				break;
			case 4:
				if(day>19){
					constel = "金牛座";
				}else{
					constel = "白羊座";
				}
				break;
			case 5:
				if(day>20){
					constel = "双子座";
				}else{
					constel = "金牛座";
				}
				break;
			case 6:
				if(day>21){
					constel = "巨蟹座";
				}else{
					constel = "双子座";
				}
				break;
			case 7:
				if(day>22){
					constel = "狮子座";
				}else{
					constel = "巨蟹座";
				}
				break;
			case 8:
				if(day>22){
					constel = "处女座";
				}else{
					constel = "狮子座";
				}
				break;
			case 9:
				if(day>22){
					constel = "天秤座";
				}else{
					constel = "处女座";
				}
				break;
			case 10:
				if(day>23){
					constel = "水瓶座";
				}else{
					constel = "摩羯座";
				}
				break;
			case 11:
				if(day>22){
					constel = "射手座";
				}else{
					constel = "天蝎座";
				}
				break;
			case 12:
				if(day>21){
					constel = "摩羯座";
				}else{
					constel = "射手座";
				}
				break;
		}
		
		
		System.out.println("生日"+month+"月"+day+"日是"+constel);

	}
}