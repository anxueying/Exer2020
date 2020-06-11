package easy;

/**
 * @author Mrs.An Xueying
 * 2020/6/11 11:19
 * 344. 反转字符串
 */
public class Solution344 {
    /**
     * 迭代
     * @param s 待反转字符数组
     */
    public void reverseString1(char[] s) {
        int len = s.length;
        for(int i=0;i<len/2;i++){
            char temp = s[i];
            s[i] = s[len-i-1];
            s[len-i-1] = temp;
        }
    }


    /**
     * 递归
     * @param s 待反转字符数组
     */
    public void reverseString(char[] s) {
        if(s.length<2) {
            return;
        }
        reverse(s,0,s.length-1);
    }

    public void reverse(char[] c , int left, int right){
        if(left<right-1){
            reverse(c,left+1,right-1);
        }
        char temp = c[left];
        c[left] = c[right];
        c[right] = temp;
    }
}
