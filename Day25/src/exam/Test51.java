package exam;

/**
 * @author Mrs.An Xueying
 * 2020/5/28 10:11
 * 1.第一个人10，第2个比第1个人大2岁，以此类推，
 * 请用递归方式计算出第8个人多大？
 */
public class Test51 {
    public static void main(String[] args) {
        System.out.println(age(8));
    }

    public static int age(int num){
        if (num==1){
            return 10;
        }else {
           return age(num-1)+2;
        }
    }
}
