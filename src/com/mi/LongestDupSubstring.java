package com.mi;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-18. <br>
 * 最长公共子串问题
 * 两个字符串最大公共子序列
 **/
public class LongestDupSubstring {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s1 = input.nextLine();
        String s2 = input.nextLine();
        System.out.println(getCount(s1, s2));
        System.out.println(findLongest(s1, s2));
        System.out.println(MaxTwoArraySameOrderMethod(s1, s2));
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

    public static int MaxTwoArraySameOrderMethod(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        /*
         * 定义一个二维数组保存公共子序列长度
         * dp[i][j]表示字符串1从头开始长度是i，字符串2从头开始长度是j，这两个字符串的最长公共子序列的长度
         * 设置数组行列比他们长度大一往二维数组中填写数字时，每个位置的数字跟他上方或者左方或者左上方数字有关系，这样处理边界数字时不用处理这种情况，方便接下来的循环
         */
        int dp[][] = new int[m + 1][n + 1];
        /*
         * 初始化第一行第一列
         * dp[0,j]表示啥？表示字符串1的长度是0，字符串2的长度是j，这两个字符串的最长公共子序列的长度是0，因为，字符串1 根本就没有嘛
         */
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                /*
                 * 如果当c[i][j]时，字符串1从头开始长度是i，字符串2从头开始长度是j时他们最后一个字符相同
                 * 就同时把他们向前移动一位，找c[i-1][j-1]时长度最大的再加一
                 * 表现在二维数组中就是c[i][j]左上方的点
                 */
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    /*
                     * 如果当c[i][j]时，他们最后一个字符不相同
                     * 要将str1往前移动一位的c[i-1][j]的lcs长度，或者将str2往前移动一位的c[i][j-1]的lcs长度
                     * 哪个长，将它赋给c[i][j]
                     * 表现在二维数组中就是c[i][j]上方的点或者左方的点
                     */
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }


}