package com.mi;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-18. <br>
 * 最长公共子串问题
 **/
public class LongestDupSubstring {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s1 = input.nextLine();
        String s2 = input.nextLine();
        System.out.println(getCount(s1, s2));
        System.out.println(findLongest(s1, s2));
    }

    /**
     * 暴力求解，用短的去匹配长的
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int getCount(String s1, String s2) {
        if (s1 == null || s2 == null)
            return 0;
        int maxCount = 0;
        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();
        int j = 0;
        int count = 0;
        if (s1Arr.length < s2Arr.length) {
            char[] tempArr = s1Arr;
            s1Arr = s2Arr;
            s2Arr = tempArr;

        }
        while (j < s2Arr.length) {
            int temp = j;
            int i = 0;
            while (i < s1Arr.length && temp < s2Arr.length) {
                if (s1Arr[i] == s2Arr[temp]) {
                    while (temp < s2Arr.length - 1 && i < s1Arr.length - 1 && s1Arr[i + 1] == s2Arr[temp] && s1Arr[i + 1] != s2Arr[temp + 1]) {
                        i++;
                    }
                    count++;
                    temp++;
                    if (count > maxCount) {
                        maxCount = count;
                    }
                } else {
                    count = 0;
                    temp = 0;
                }
                i++;
            }
            j++;
        }
        return maxCount;
    }
    /**
     * 动态规划
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int findLongest(String s1, String s2) {
        if (s1 == null || s2 == null)
            return 0;
        int rs = 0;
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    rs = Math.max(rs, dp[i][j]);//每次更新记录最大值
                } else//不相等的情况
                    dp[i][j] = 0;
            }
        return rs;//返回的结果为rs
    }


}