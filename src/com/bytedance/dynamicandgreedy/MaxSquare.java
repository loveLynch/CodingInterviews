package com.bytedance.dynamicandgreedy;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-10. <br>
 * 最大正方形
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * 输出: 4
 **/
public class MaxSquare {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        char[][] matrix = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                matrix[i][j] = input.next().charAt(0);
        }
        System.out.println(maximalSquare(matrix));

    }

    /**
     * 动态规划
     *
     * @param matrix
     * @returni
     */
    public static int maximalSquare(char[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        int[][] dp = new int[m][n];
        //第一列赋值
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] - '0';
            max = Math.max(max, dp[i][0]);
        }
        //第一行赋值
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i] - '0';
            max = Math.max(max, dp[0][i]);
        }
        // 递推
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = matrix[i][j] == '1' ? Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1 : 0;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;

    }


}
