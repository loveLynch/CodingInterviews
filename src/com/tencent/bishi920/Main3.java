package com.tencent.bishi920;

import java.util.*;

/**
 * Created by lynch on 2019-09-20. <br>
 **/
public class Main3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        List<int[]> people = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            int[] x = new int[n];
            for (int j = 0; j < n; j++) {
                x[j] = input.nextInt();
            }
            people.add(x);
        }
        List<int[]> resultList = getSplitSolution(people);
        for (int[] result : resultList) {
            System.out.println(result[0] + " " + result[1]);
        }

    }

    private static List<int[]> getSplitSolution(List<int[]> people) {
        List<int[]> resultList = new ArrayList<>();
        for (int[] x : people) {
            int[] temp = new int[2];
            int sum = 0;
            for (int s : x) {
                sum += s;
            }
            int length = x.length;
            int[][] dp = new int[length + 1][sum / 2 + 1];
            for (int i = 0; i < length; i++) {
                for (int j = 1; j <= sum / 2; j++) {
                    dp[i + 1][j] = dp[i][j];
                    if (x[i] <= j && dp[i][j - x[i]] + x[i] > dp[i][j]) {
                        dp[i + 1][j] = dp[i][j - x[i]] + x[i];
                    }
                }
            }
            int a = dp[length][sum / 2];
            int b = sum-dp[length][sum / 2];
            temp[0] = a;
            temp[1] = b;
            resultList.add(temp);
        }
        return resultList;
    }
}
