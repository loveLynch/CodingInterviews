package com.leetcode;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-30. <br>
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 * <p>
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 * <p>
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 * <p>
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输入: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wildcard-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class Match {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        String p = input.nextLine();
        System.out.println(isMatch1(s, p));
        System.out.println(isMatch2(s, p));

    }

    /**
     * 动态规划
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch1(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (p.charAt(i - 1) == '*')
                dp[0][i] = true;
            else {
                break;
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[m][n];

    }

    /**
     * 回溯
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch2(String s, String p) {
        int i = 0;
        int j = 0;
        int star = -1;
        int k = 0; // 记录最后一次匹配的位置  + 1
        //遍历整个字符串
        while (i < s.length()) {
            // 一对一匹配，两指针同时后移。
            if (j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                ++i;
                ++j;
            }
            // 碰到 *，假设它匹配空串，并且用 star 记录 * 的位置，k记录当前字符串的位置，j 后移
            else if (j < p.length() && p.charAt(j) == '*') {
                star = j++;
                k = i;
            }

            // 当前字符不匹配，并且也没有 *，回退
            // j 回到 * 的下一个位置
            // k 更新到下一个位置
            // i 回到更新后的 match
            // 这步代表用 * 匹配了一个字符
            else if (star != -1) {
                i = ++k;
                j = star + 1;
            } else {
                return false;
            }
        }
        ////将末尾多余的 * 直接匹配空串 例如 text = ab, pattern = a*******
        while (j < p.length() && p.charAt(j) == '*') ++j;

        return j == p.length();
    }
}
