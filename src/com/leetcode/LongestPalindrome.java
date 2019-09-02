package com.leetcode;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-31. <br>
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class LongestPalindrome {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        System.out.println(longestPalindrome(s));

    }

    /**
     * 求最长公共子串
     * 用动态规划的方法，
     * 整体思想就是，申请一个二维的数组初始化为 0，然后判断对应的字符是否相等，相等的话
     * arr [ i ][ j ] = arr [ i - 1 ][ j - 1] + 1 。
     * 当 i = 0 或者 j = 0 的时候单独分析，字符相等的话 arr [ i ][ j ] 就赋为 1 。
     * arr [ i ][ j ] 保存的就是公共子串的长度。
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s.equals(""))
            return "";
        String origin = s;
        String reverse = new StringBuffer(s).reverse().toString();
        int length = s.length();
        int[] arr = new int[length];
        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < length; i++)
            for (int j = length - 1; j >= 0; j--) {
                if (origin.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr[j] = 1;
                    } else {
                        arr[j] = arr[j - 1] + 1;
                    }
                    //之前二维数组，每次用的是不同的列，所以不用置 0 。
                } else {
                    arr[j] = 0;
                }
                if (arr[j] > maxLen) {
                    int beforeRev = length - 1 - j;
                    if (beforeRev + arr[j] - 1 == i) {
                        maxLen = arr[j];
                        maxEnd = i;
                    }

                }
            }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);


    }
}
