package easy;

import java.math.BigInteger;

/**
 * @author Mrs.An Xueying
 * 2020/5/30 20:19
 * you need treat n as an unsigned value
 */
public class Solution190 {
    /**
     * @param n an unsigned value  无符号整数的二进制位
     * @return 颠倒二进制位(是翻转，别被骗了，即1011100变为0011101)
     */
    public static int reverseBits(int n) {

        String result = Integer.toBinaryString(n);
        StringBuffer s = new StringBuffer();

        for (int i = result.length()-1; i >=0; i--) {
            s.append(result.charAt(i));
        }

        while (s.length()<32){
            s.append('0');
        }

        return Integer.parseInt(s.toString(),2);
    }


    public static void main(String[] args) {
        int result = reverseBits(-1);
        System.out.println(result);



    }
}
