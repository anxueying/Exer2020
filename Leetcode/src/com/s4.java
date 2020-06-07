package com;

/**
 * @author Mrs.An Xueying
 * 2020/6/7 12:16
 */
public class s4 {
    public int minCost(int[] houses, int[][] p, int n, int m, int k) {
        int c[] = new int[n + 1];
        for (int i = 0; i < n; i++) {
            c[i + 1] = houses[i];
        }
        c[0] = -1;
        long dp[][][] = new long[n + 1][m][n + 5];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m; j++) {
                for (int l = 0; l < n + 5; l++) {
                    dp[i][j][l] = (long) 1e16;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            dp[0][i][1] = 0;
        }
        for (int i = 1; i <= n; i++) {
            if (c[i] == 0) {
                for (int j = 0; j < m; j++) {
                    for (int ka = 0; ka < n + 4; ka++) {
                        for (int l = 0; l < m; l++) {
                            if (l == j || i == 1) {
                                dp[i][l][ka] = Math.min(dp[i - 1][j][ka] + p[i - 1][l], dp[i][l][ka]);
                            } else {
                                dp[i][l][ka + 1] = Math.min(dp[i - 1][j][ka] + p[i - 1][l], dp[i][l][ka + 1]);
                            }
                        }
                    }
                }
            } else {
                for (int j = 0; j < m; j++) {
                    for (int ka = 0; ka < n + 4; ka++) {
                        if (c[i] - 1 == j || i == 1) {
                            dp[i][c[i] - 1][ka] = Math.min(dp[i - 1][j][ka], dp[i][c[i] - 1][ka]);
                        } else {
                            dp[i][c[i] - 1][ka + 1] = Math.min(dp[i - 1][j][ka], dp[i][c[i] - 1][ka + 1]);
                        }
                    }
                }
            }
        }
        long ans = Long.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            ans = Math.min(ans, dp[n][j][k]);
        }
        return (int) (ans == (long) 1e16 ? -1 : ans);
    }
}
