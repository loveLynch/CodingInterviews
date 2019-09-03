package com.tencent.bishi91;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-09-01. <br>
 * 3 2
 * 1 3
 * 2 3
 * 4 4
 * <p>
 * <p>
 * 6
 * 5
 * 5
 * 定义函数f(i)表示长度为i的时候合法方案数。那么
 * <p>
 * f(i)=f(i−1)+f(i−k)
 * f(i) 的方案数只能来源于两种情况：一种是前面的白花不连续，一种是白花连续。
 * 版权声明：本文为CSDN博主「coordinate_blog」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_17550379/article/details/100355642
 **/
public class Main5 {
    public static final int MAX = (int) (1e5 + 5);
    public static final int MOD = (int) (1e5 + 7);

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        int k = input.nextInt();
        int[][] locationLength = new int[t][2];
        for (int i = 0; i < t; i++) {
            locationLength[i][0] = input.nextInt();
            locationLength[i][1] = input.nextInt();

        }

        List<Long> results = getSolution(locationLength, k);
        for (long result : results)
            System.out.println(result);

    }

    private static List<Long> getSolution(int[][] locationLength, int k) {
        List<Long> results = new ArrayList<>();
        long[] dp = new long[MAX];
        long[] sum = new long[MAX];
        for (int i = 0; i < k; i++)
            dp[i] = 1;
        for (int i = k; i < MAX; i++)
            dp[i] = (dp[i - 1] % MOD + dp[i - k] % MOD) % MOD;
        sum[1] = dp[1];
        for (int i = 2; i < MAX; i++)
            sum[i] = (sum[i - 1] % MOD + dp[i] % MOD) % MOD;
        for (int i = 0; i < locationLength.length; i++) {
            results.add((MOD + sum[locationLength[i][1]] - sum[locationLength[i][0] - 1]) % MOD);
        }

        return results;
    }
}
