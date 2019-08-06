package com.bytedance.arrayandsort;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-06. <br>
 * 朋友圈
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 * <p>
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [[1,1,0],
 * [1,1,0],
 * [0,0,1]]
 * 输出: 2
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回2。
 * 示例 2:
 * <p>
 * 输入:
 * [[1,1,0],
 * [1,1,1],
 * [0,1,1]]
 * 输出: 1
 * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
 * 注意：
 * <p>
 * N 在[1,200]的范围内。
 * 对于所有学生，有M[i][i] = 1。
 * 如果有M[i][j] = 1，则有M[j][i] = 1。
 **/
public class CircleNum {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int[][] M = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                M[i][j] = input.nextInt();

        }
        System.out.println(findCircleNum2(M));
    }

    /**
     * 深度优先搜索DFS
     *
     * @param M
     * @return
     */
    public static int findCircleNum1(int[][] M) {
        int count = 0;
        int book[] = new int[M.length];
        for (int i = 0; i < M.length; i++)
            if (book[i] == 0) {
                book[i] = 1;
                dfs(M, i, book);
                count++;
            }
        return count;
    }

    public static void dfs(int[][] M, int i, int[] book) {

        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && book[j] == 0) {
                book[j] = 1;
                dfs(M, j, book);
            }
        }
    }

    /**
     * 同为DFS,写法略微有区别
     *
     * @param M
     * @return
     */
    public static int findCircleNum2(int[][] M) {
        int count = 0;
        //访问标志
        int visited[] = new int[M.length];
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 1) {
                continue;
            }
            helper(M, i, visited);
            count++;
        }
        return count;
    }

    public static void helper(int[][] M, int i, int[] visited) {
        visited[i] = 1;
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] != 1 || visited[j] == 1) {
                continue;
            }
            helper(M, j, visited);

        }
    }
}
