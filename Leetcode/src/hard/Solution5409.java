package hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Mrs.An Xueying
 * 2020/5/30 23:31
 * 检查一个字符串是否包含所有长度为 K 的二进制子串
 * 给你一个二进制字符串 s 和一个整数 k 。
 * 如果所有长度为 k 的二进制字符串都是 s 的子串，请返回 True ，否则请返回 False 。
 *
 * 1 <= s.length <= 5 * 10^5
 * s 中只含 0 和 1 。
 * 1 <= k <= 20
 */
public class Solution5409 {
    public static  boolean hasAllCodes(String s,int k){
        if (s.length()<=k){
            return false;
        }

        StringBuilder sb =new StringBuilder(s);
        HashSet<String> hashSet = new HashSet<>();

        for (int i = 0; i < sb.length()-k+1; i++) {
            hashSet.add(sb.substring(i, i+k));
        }

        System.out.println(hashSet);

        if (hashSet.size()<Math.pow(2, k)){
            return false;
        }else {
            return true;
        }
    }


    public static void main(String[] args) {
        System.out.println(hasAllCodes("00110", 2));
    }
}
