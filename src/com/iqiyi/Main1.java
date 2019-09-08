package com.iqiyi;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-08. <br>
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int[] A = new int[N - 1];
        for (int i = 0; i < N - 1; i++)
            A[i] = input.nextInt();
        System.out.println(getCountSeq(A));
        System.out.println(getCountSeq1(A));
    }

    /**
     * dp[i][j] 表示由 i+1 个数字组成且第 i+1 个数字（即序列中的最后一个数字）是剩余数字中（包括当前数字）中第 j+1 小的数字
     *
     * @param a
     * @return
     */
    private static int getCountSeq(int[] a) {
        int N = a.length + 1;
        int MOD = (int) (1e9 + 7);
        int dp[][] = new int[N + 1][N + 1];
        dp[1][1] = 1;
        for (int i = 2; i <= N; ++i) {
            for (int j = 1; j <= i; ++j) {
                if (a[i - 2] == 0) {
                    dp[i][j] = (dp[i][j - 1] + (dp[i - 1][i - 1] - dp[i - 1][j - 1]) % MOD) % MOD;
                } else {
                    dp[i][j] = (dp[i][j - 1] + (dp[i - 1][j - 1] - dp[i - 1][0]) % MOD) % MOD;
                }
            }
        }
        return (dp[N][N] + MOD) % MOD;
    }

    /**
     * dp[i][j] 表示由范围 [0, i] 内的数字组成且最后一个数字为j的不同序列的个数。
     *
     * @param a
     * @return
     */
    public static int getCountSeq1(int[] a) {
        int res = 0, n = a.length, M = (int) (1e9 + 7);
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (a[i - 1] == 1) {
                    for (int k = j; k <= i - 1; ++k) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][k]) % M;
                    }
                } else {
                    for (int k = 0; k <= j - 1; ++k) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][k]) % M;
                    }
                }
            }
        }
        for (int i = 0; i <= n; ++i) {
            res = (res + dp[n][i]) % M;
        }
        return res;
    }
}
