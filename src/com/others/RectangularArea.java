package com.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-09-11. <br>
 * input
 * 3
 * 3 3
 * 000
 * 010
 * 000
 * 5 4
 * 1100
 * 0000
 * 0000
 * 0000
 * 0000
 * 3 5
 * 00001
 * 00000
 * 10100
 * output
 * 9
 * 16
 * 8
 **/
public class RectangularArea {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        List<int[][]> boardList = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            int m = input.nextInt();
            int n = input.nextInt();
            int checkerboard[][] = new int[m][n];
            input.nextLine();
            for (int j = 0; j < m; j++) {
                char[] line = input.nextLine().toCharArray();
                for (int k = 0; k < n; k++) {
                    checkerboard[j][k] = line[k] - '0';
                }
            }
            boardList.add(checkerboard);
        }
        List<Integer> areaList = getColorArea(boardList);
        for (int area : areaList) {
            System.out.println(area);
        }

    }

    private static List<Integer> getColorArea(List<int[][]> boardList) {
        List<Integer> areaList = new ArrayList<>();
        for (int[][] board : boardList) {
            int m = board.length;
            int n = board[0].length;

            int[] left = new int[n]; // initialize left as the leftmost boundary possible
            int[] right = new int[n];
            int[] height = new int[n];

            Arrays.fill(right, n); // initialize right as the rightmost boundary possible

            int maxarea = 0;
            for (int i = 0; i < m; i++) {
                int cur_left = 0, cur_right = n;
                // update height
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 0) {
                        height[j]++;
                    } else {
                        if (i > 0 && j > 0 && i < m - 1 && j < n - 1 && board[i][j] == 1 && board[i - 1][j] == 0 && board[i + 1][j] == 0 && board[i][j - 1] == 0 && board[i][j + 1] == 0) {
                            height[j]++;
                        } else {
                            height[j] = 0;
                        }
                    }
                }
                // update left
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 0) {
                        left[j] = Math.max(left[j], cur_left);
                    } else {
                        left[j] = 0;
                        cur_left = j + 1;
                    }
                }
                // update right
                for (int j = n - 1; j >= 0; j--) {
                    if (board[i][j] == 0) {
                        right[j] = Math.min(right[j], cur_right);
                    } else {
                        right[j] = n;
                        cur_right = j;
                    }
                }
                // update area
                for (int j = 0; j < n; j++) {
                    maxarea = Math.max(maxarea, (right[j] - left[j]) * height[j]);
                }
            }
            areaList.add(maxarea);
        }
        return areaList;
    }



}
