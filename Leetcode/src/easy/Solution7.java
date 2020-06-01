package easy;

import java.util.ArrayList;

/**
 * @author Mrs.An Xueying
 * 2020/5/30 16:24
 * 7. 整数反转
 */
public class Solution7 {
    public static void main(String[] args) {
        //1534236469
        System.out.println(reverse(1534236469));
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
