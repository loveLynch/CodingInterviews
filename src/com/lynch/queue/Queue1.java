package com.lynch.queue;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-04-15. <br>
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * <p>
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 **/
public class Queue1 {
    private static List<List<String>> solveNQueens(int n) {
        // 下标代表行，值代表列。如result[0] = 3 表示第1行的Q在第3列
        int[] result = new int[n];
        List<List<String>> resultList = new LinkedList<>();
        dfs(resultList, result, 0, n);
        return resultList;
    }

    private static void dfs(List<List<String>> resultList, int[] result, int row, int n) {
        // 递归终止条件
        if (row == n) {
            List<String> list = new LinkedList<>();
            for (int x = 0; x < n; ++x) {
                StringBuilder sb = new StringBuilder();
                for (int y = 0; y < n; ++y)
                    sb.append(result[x] == y ? "Q" : ".");
                list.add(sb.toString());
            }
            resultList.add(list);
            return;
        }
        for (int column = 0; column < n; ++column) {
            boolean isValid = true;
            result[row] = column;
            /*
             * 逐行往下考察每一行。同列，result[i] == column
             * 同对角线，row - i == Math.abs(result[i] - column)
             */
            for (int i = row - 1; i >= 0; --i) {
                if (result[i] == column || row - i == Math.abs(result[i] - column)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) dfs(resultList, result, row + 1, n);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int queueNum = in.nextInt();
        List<List<String>> result = solveNQueens(queueNum);
        int i = 1;
        for (List<String> rows : result) {
            System.out.println("第" + i + "种方案");
            for (String quenue : rows) {
                System.out.print(quenue+" ");
            }
            System.out.println();
            i++;
        }
    }
}
