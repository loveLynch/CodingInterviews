package com.leetcode;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-15. <br>
 * 10. 正则表达式匹配
 * <p>
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class MatchString {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        String p = input.nextLine();
        System.out.println(isMatch(s, p));
    }

    /**
     * 状态定义：
     * <p>
     * 设动态规划网格 dp ， dp[i][j] 代表字符串 s 中前 i 个字符和 p 中前 j 个字符是否匹配，值为 true 或 false 。
     * 记 s 第 i个字符记为 s[m]==s[i−1]； p 第 j个字符记为p[n]==p[j−1]。
     * 记 s 和 p 长度分别为 ls , lp 。
     * 初始状态：
     * <p>
     * 初始化第一列 dp[0][2] - dp[0][lp]] ： dp[0][j] = dp[0][j - 2] and p[j - 1] == '*'
     * Tips: p 第 j个字符记为 '*',且 dp[0][j - 2]为 True
     * 转移方程：
     * <p>
     * 1.当第 p[n] 为 '*' 时：
     * a)当 p[n-1]为 '.'或 s[m] == p[n-1]时： dp[i][j] = dp[i-1][j] ；
     * Tips: 此两种情况代表 s[m] 和 p[n-1] 可以匹配，等价于无 s[m] 的状态 dp[i-1][j]
     * b)否则： dp[i][j] = dp[i][j-2]
     * Tips: 此情况代表 s[m]和 p[n-1]无法匹配， p[n-1]p[n] 的组合必须出现 0 次，等价于没有 p[n-1]p[n] 时的状态 dp[i][j-2]
     * <p>
     * 2.否则，当 p[n]p[n] 为 '.'或 s[m] == p[n] 时： dp[i][j] = dp[i-1][j-1];
     * Tips: 此情况代表 s[m] 和 p[n]p 直接匹配，当前状态等价于未匹配此两字符前的状态 dp[i-1][j-1]
     * 返回值：
     * <p>
     * 字符串 s 中前 ls 个字符和 p 中前 lp 个字符是否匹配，即： dp[ls][lp].
     * <p>
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/regular-expression-matching/solution/zheng-ze-biao-da-shi-pi-pei-dong-tai-gui-hua-by-jy/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        int ls = s.length(), lp = p.length();
        boolean[][] dp = new boolean[ls + 1][lp + 1];
        dp[0][0] = true;
        for (int j = 2; j <= lp; j++)
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        for (int i = 1; i <= ls; i++) {
            for (int j = 1; j <= lp; j++) {
                int m = i - 1, n = j - 1;
                if (p.charAt(n) == '*')
                    dp[i][j] = dp[i][j - 2] || dp[i - 1][j] &&
                            (s.charAt(m) == p.charAt(n - 1) || p.charAt(n - 1) == '.');
                else if (s.charAt(m) == p.charAt(n) || p.charAt(n) == '.')
                    dp[i][j] = dp[i - 1][j - 1];
            }
        }
        return dp[ls][lp];
    }
}
