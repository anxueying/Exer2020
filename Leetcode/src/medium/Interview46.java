package medium;


/**
 * @author Mrs.An Xueying
 * 2020/6/9 22:29
 * 没必要将所有的转成字母，只要确定有多少种分割方式即可
 */
public class Interview46 {
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int[] dp = new int[s.length()+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= s.length(); i ++){
            String temp = s.substring(i-2, i);
            if(temp.compareTo("10") >= 0 && temp.compareTo("25") <= 0) {
                dp[i] = dp[i-1] + dp[i-2];
            } else {
                dp[i] = dp[i-1];
            }
        }
        return dp[s.length()];

    }

    public static void main(String[] args) {
        System.out.println("11".compareTo("10"));
    }
}
