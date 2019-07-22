package com.lynch.queue;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-04-15. <br>
 **/
public class Queue2 {
    private static List<List<String>> resultList = new LinkedList<>();

    private static List<List<String>> solveNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] main_diag = new boolean[2 * n];
        boolean[] anti_diag = new boolean[2 * n];
        LinkedList<Integer> result = new LinkedList<>();
        dfs(result, 0, cols, main_diag, anti_diag, n);
        return resultList;
    }

    private static void dfs(LinkedList<Integer> result, int row, boolean[] cols, boolean[] main_diag, boolean[] anti_diag, int n) {
        if (row == n) {
            List<String> list = new LinkedList<>();
            for (int x = 0; x < n; ++x) {
                StringBuilder sb = new StringBuilder();
                for (int y = 0; y < n; ++y)
                    sb.append(result.get(x) == y ? "Q" : ".");
                list.add(sb.toString());
            }
            resultList.add(list);
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (cols[i] || main_diag[row + i] || anti_diag[row - i + n])
                continue;
            result.add(i);
            cols[i] = true;
            main_diag[row + i] = true;
            anti_diag[row - i + n] = true;
            dfs(result, row + 1, cols, main_diag, anti_diag, n);
            result.removeLast();
            cols[i] = false;
            main_diag[row + i] = false;
            anti_diag[row - i + n] = false;
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
