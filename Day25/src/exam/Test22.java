package exam;

/**
 * @author Mrs.An Xueying
 * 2020/5/28 9:59
 * 2、通项公式如下：f(n)=n + (n-1) + (n-2) + .... + 1，
 * 其中n是大于等于5并且小于10000的整数，
 * 例如：f(5) = 5 + 4 + 3 + 2 + 1，f(10) = 10 + 9 + 8 + 7+ 6 + 5 + 4 + 3 + 2 + 1，
 * 请用非递归的方式完成方法long f( int n)的方法体。
 */
public class Test22 {
    public static void main(String[] args) {
        System.out.println(f(5));
        System.out.println(f(10));
    }

    public static long f(int n){
        return (1+n)*n/2;
    }
}
