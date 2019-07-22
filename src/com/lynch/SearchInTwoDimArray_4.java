package com.lynch;

import java.util.Scanner;

/**
 * Created by lynch on 2019/2/25. <br>
 * 4.二维数组中的查找
 * 在一个二维数组中每一行从左到右递增的顺序排序，从上到下递增的顺序排序
 * 输入一个这样的二维数组和一个整数，判读数组中是否有该整数
 **/
public class SearchInTwoDimArray_4 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input M:");
        int M = in.nextInt();
        int[][] matrix = new int[][]{{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        if (findInteger(matrix, M))
            System.out.println("found");
        else {
            System.out.println("not find");
        }

    }

    /**
     * 选取右上角数字，然后比较剔除所在行或列（也可选取左下角）
     * 但是不能选取左上角或者右下角数字，不能从查找范围内剔除所在行或者列
     *
     * @param matrix
     * @param number 要查找的整数
     * @return
     */
    public static boolean findInteger(int matrix[][], int number) {
        boolean found = false;
        if (matrix != null && matrix.length > 0 && matrix[0].length > 0) {
            int row = 0;
            int column = matrix.length - 1;
            while (row < matrix.length && column >= 0) {
                if (matrix[row][column] == number) {
                    found = true;
                    break;
                } else if (matrix[row][column] > number) {
                    --column;
                } else {
                    ++row;
                }
            }
        }

        return found;

    }
}
