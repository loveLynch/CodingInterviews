package com.leetcode;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-11. <br>
 * 72. 编辑距离
 * <p>
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 * <p>
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:
 * <p>
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class EditDistance {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String word1 = input.nextLine();
        String word2 = input.nextLine();
        System.out.println(minDistance(word1, word2));

    }

    /**
     * 1.状态
     * <p>
     * f[i][j]表示s1的前 i 个字符，和s2的前 j 个字符，的编辑距离
     * 2.转移方程
     * <p>
     * a)如果s1的第 i 个字符和s2的第 j 个字符相同的话，f[i][j] = f[i - 1][j - 1]
     * b)如果s1的第 i 个字符和s2的第 j 个字符不同的话，f[i][j] = min(f[i - 1][j], f[i][j - 1], f[i - 1][j - 1]) + 1
     * f[i - 1][j]意味着删除s1的第 i 个字符
     * f[i][j - 1]意味着s1增加s2的第 j 个字符
     * f[i - 1][j - 1]意味着s1的第 i 个字符替换成s2的第 j 个字符
     * 3.初始化
     * <p>
     * f[i][0] = i
     * f[0][j] = j
     * s1的前i个字符和s2的前0个字符的编辑距离是i（也就是需要删除i次才能变成一样）
     * 4.结果
     * <p>
     * f[m][n]
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] cost = new int[m + 1][n + 1];
        for (int i = 0; i <= m; ++i) {
            cost[i][0] = i;
        }
        for (int j = 0; j <= n; ++j) {
            cost[0][j] = j;
        }
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++)
//                System.out.print(cost[i][j] + " ");
//            System.out.println();
//        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    cost[i][j] = cost[i - 1][j - 1];
                } else {
                    cost[i][j] = 1 + Math.min(cost[i - 1][j - 1], Math.min(cost[i][j - 1], cost[i - 1][j]));
                }
            }
        }
        return cost[m][n];
    }
}
