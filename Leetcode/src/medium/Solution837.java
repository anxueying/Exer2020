package medium;

import java.util.Random;

/**
 * @author Mrs.An Xueying
 * 2020/6/3 22:08
 */
public class Solution837 {
    public static double new21Game(int N, int K, int W) {
        //[1,w]范围随机获得一个整数累计 每次抽取独立
        //小于k继续抽取
        //得分不超过N的概率是多少
        if (K == 0) {
            return 1.0;
        }
        double[] dp = new double[K + W];
        for (int i = K; i <= N && i < K + W; i++) {
            dp[i] = 1.0;
        }
        dp[K - 1] = 1.0 * Math.min(N - K + 1, W) / W;
        for (int i = K - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] - (dp[i + W + 1] - dp[i + 1]) / W;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(new21Game(10, 1, 10));
        System.out.println(new21Game(6, 1, 10));
        System.out.println(new21Game(21, 17, 10));
    }
}
