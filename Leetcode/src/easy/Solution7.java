package easy;

import java.util.ArrayList;

/**
 * @author Mrs.An Xueying
 * 2020/5/30 16:24
 * 7. 整数反转
 */
public class Solution7 {
    public static void main(String[] args) {
        /**
         *temp = rev*10+pop
         *
         if (rev > INT_MAX/10 || (rev == INT_MAX / 10 && pop > 7)) return 0;
         if (rev < INT_MIN/10 || (rev == INT_MIN / 10 && pop < -8)) return 0;
         */
        System.out.println("Integer.MIN_VALUE/10 = " + Integer.MIN_VALUE / 10);
        System.out.println("Integer.MAX_VALUE/10 = " + Integer.MAX_VALUE / 10);

    }

    public static int reverse(int x) {
        int value = 0;
        while (x!=0){
            int temp = value*10 + x%10;
            //溢出判断 正常应该是 value + x%10/10 即 value 如果溢出了，那就不等于value了
            if (temp/10!=value){
                return 0;
            }
            value = temp;
            x = x/10;
        }
        return value;
    }
}
