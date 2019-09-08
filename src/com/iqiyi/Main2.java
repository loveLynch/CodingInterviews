package com.iqiyi;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-08. <br>
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        System.out.printf("%.5f", getProbabilityOfA(n, m));
    }

    private static double getProbabilityOfA(int n, int m) {
        double dp[][] = new double[n + 1][m + 1];
        for (int i = 1; i <= n; i++)
            dp[i][0] = 1.0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = (double) i / (double) (i + j);
                if (j >= 3)
                    dp[i][j] += (double) j * (double) (j - 1) * (double) (j - 2) * dp[i][j - 3] / ((double) (i + j) * (double) (i + j - 1) * (double) (i + j - 2));
                if (i >= 1 && j >= 2)
                    dp[i][j] += (double) j * (double) (j - 1) * (double) i * dp[i - 1][j - 2] / ((double) (i + j) * (double) (i + j - 1) * (double) (i + j - 2));
            }
        }
        return dp[n][m];
    }
}
