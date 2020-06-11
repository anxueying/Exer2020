package easy;

/**
 * @author Mrs.An Xueying
 * 2020/6/10 20:09
 * 9. 回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 */
public class Solution9 {
    public boolean isPalindrome(int x) {
        if(x<0) {
            return false;
        }
        int xBefore = x;
        int xAfter = 0;
        while(x!=0){
            xAfter = xAfter*10 + x%10;
            x/=10;
        }

        return xAfter==xBefore;
    }

    /**
     * 官方题解：只转一半
     * @param x
     * @return
     */
    public boolean isPalindrome1(int x) {
        if(x<0 || (x%10==0 && x!=0)) {
            return false;
        }
        int xAfter = 0;
        while(x > xAfter){
            xAfter = xAfter*10 + x%10;
            x/=10;
        }
        return x==xAfter || x==xAfter/10;
    }
}
