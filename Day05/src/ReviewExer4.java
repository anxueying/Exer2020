/**
 * @author Lenovo
 */
public class ReviewExer4 {
    public static void main(String[] args) {
        if(ifLeap(2000)){
            System.out.println("闰年");
        }else{
            System.out.println("非闰年");
        }
    }

    public static boolean ifLeap(int year){
        boolean leapYear = (year%4==0&&year%100!=0)||(year%400==0);
        if(leapYear){
            return true;
        }else{
            return false;
        }
    }
}
