package com.lynch;

/**
 * Created by lynch on 2019/3/17. <br>
 * 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
 * 1  2  3  4
 * 5  6  7  8
 * 9 10 11 12
 * 13 14 15 16
 * 则依次打印为1，2，3，4，8，12，16，15，14，13，9，5，6，7，11，10
 **/
public class ClockwisePrintMatrix_29 {
    private static void printMatrix(int[][] numbers) {
        if (numbers == null)
            return;
        int start = 0;
        int rows = numbers.length;
        int cols = numbers[0].length;
        while (cols > start * 2 && rows > start * 2) {
            printMatrixInCircle(numbers, rows, cols, start);
            start++;
        }
        System.out.println();
    }

    private static void printMatrixInCircle(int[][] numbers, int rows, int cols, int start) {
        int endX = cols - 1 - start;
        int endY = rows - 1 - start;

        //从左到右打印一行
        for (int i = start; i <= endX; i++) {
            int number = numbers[start][i];
            System.out.print(number + "\t");
        }

        //从上到下打印一列
        if (start < endY) {
            for (int i = start + 1; i <= endY; i++) {
                int number = numbers[i][endX];
                System.out.print(number + "\t");
            }
        }

        //从右到左打印一行
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; i--) {
                int number = numbers[endY][i];
                System.out.print(number + "\t");
            }
        }

        //从下到上打印一列
        if (start < endX && start < endY - 1) {
            for (int i = endY - 1; i >= start + 1; i--) {
                int number = numbers[i][start];
                System.out.print(number + "\t");
            }
        }


    }

    public static void main(String[] args) {
        int[][] data1 = {
                {1, 2, 3, 4},
                {12, 13, 14, 5},
                {11, 16, 15, 6},
                {10, 9, 8, 7},
        };
        int[][] data2 = {
                {1, 2, 3, 4},
                {10, 11, 12, 5},
                {9, 8, 7, 6},
        };
        int[][] data3 = {
                {1, 2, 3},
                {10, 11, 4},
                {9, 12, 5},
                {8, 7, 6},
        };
        int[][] data4 = {
                {1, 2, 3},
                {8, 9, 4},
                {7, 6, 5},
        };
        printMatrix(data1);
        printMatrix(data2);
        printMatrix(data3);
        printMatrix(data4);
    }

}
