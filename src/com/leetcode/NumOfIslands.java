package com.leetcode;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-29. <br>
 * 给定一个由 1（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * <p>
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * <p>
 * 输出: 3
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class NumOfIslands {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        char grid[][] = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = input.next().charAt(0);
            }
        }
        System.out.println(numIslands(grid));

    }

    /**
     * DFS
     * 将访问过的标记，防止重复访问
     *
     * @param grid
     * @return
     */
    public static int numIslands(char[][] grid) {
        int maxIsland = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    isConnectOfIsland(i, j, grid);
                    maxIsland++;


                }
            }
        }
        return maxIsland;
    }

    public static void isConnectOfIsland(int i, int j, char[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1')
            return;
        grid[i][j] = '2';
        isConnectOfIsland(i + 1, j, grid);
        isConnectOfIsland(i - 1, j, grid);
        isConnectOfIsland(i, j - 1, grid);
        isConnectOfIsland(i, j + 1, grid);

    }
}
