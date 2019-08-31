package com.leetcode;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-30. <br>
 * 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
 * <p>
 * （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
 * <p>
 * 返回区域的数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * [
 *   " /",
 *   "/ "
 * ]
 * 输出：2
 * 解释：2x2 网格如下：
 * <p>
 * 示例 2：
 * <p>
 * 输入：
 * [
 *   " /",
 *   "  "
 * ]
 * 输出：1
 * 解释：2x2 网格如下：
 * <p>
 * 示例 3：
 * <p>
 * 输入：
 * [
 *   "\\/",
 *   "/\\"
 * ]
 * 输出：4
 * 解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
 * 2x2 网格如下：
 * <p>
 * 示例 4：
 * <p>
 * 输入：
 * [
 *   "/\\",
 *   "\\/"
 * ]
 * 输出：5
 * 解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。）
 * 2x2 网格如下：
 * <p>
 * 示例 5：
 * <p>
 * 输入：
 * [
 *   "//",
 *   "/ "
 * ]
 * 输出：3
 * 解释：2x2 网格如下：
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regions-cut-by-slashes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class RegionsBySlashes {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        String[] grid = new String[n];
        for (int i = 0; i < n; i++)
            grid[i] = input.nextLine();
        System.out.println(regionsBySlashes(grid));

    }

    /**
     * 提供一个巧妙的思路。将 / 转为
     * <p>
     * 001
     * 010
     * 100.
     * <p>
     * 空格转为
     * 000
     * 000
     * 000
     * <p>
     * \转为
     * 100
     * 010
     * 001。
     * <p>
     * 将原来n*n方格转为3n * 3n 方格。求0的连通量个数。
     * <p>
     * 比如
     * <p>
     * //
     * /
     * <p>
     * 转化为
     * <p>
     * 001001
     * 010010
     * 100100
     * 001000
     * 010000
     * 100000
     * <p>
     * 作者：ma-dong-dong
     * 链接：https://leetcode-cn.com/problems/regions-cut-by-slashes/solution/mei-ge-xiao-ge-fen-jie-wei-3-3-fang-ge-qiu-lian-to/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param grid
     * @return
     */
    public static int regionsBySlashes(String[] grid) {
        int n = grid.length;
        boolean[][] graph = new boolean[n * 3][n * 3];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i].charAt(j) == '/') {
                    graph[i * 3][j * 3 + 2] = true;
                    graph[i * 3 + 1][j * 3 + 1] = true;
                    graph[i * 3 + 2][j * 3] = true;
                } else if (grid[i].charAt(j) == '\\') {
                    graph[i * 3][j * 3] = true;
                    graph[i * 3 + 1][j * 3 + 1] = true;
                    graph[i * 3 + 2][j * 3 + 2] = true;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n * 3; ++i) {
            for (int j = 0; j < n * 3; ++j) {
                if (graph[i][j] == false) {
                    dfs(graph, i, j);
                    res++;
                }
            }
        }
        return res;

    }

    /**
     * dfs
     *
     * @param graph
     * @param i
     * @param j
     */
    private static void dfs(boolean[][] graph, int i, int j) {
        int n = graph.length;
        if (i >= 0 && j >= 0 && i < n && j < n && graph[i][j] == false) {
            graph[i][j] = true;
            dfs(graph, i, j - 1);
            dfs(graph, i, j + 1);
            dfs(graph, i - 1, j);
            dfs(graph, i + 1, j);
        }
    }

}
