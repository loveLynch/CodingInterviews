package com.lynch;

/**
 * Created by lynch on 2019/3/10. <br>
 * 13.机器人的运动范围（回溯法）
 * 地上有一个m*n的方格。一个机器人从坐标（0，0）的格子开始移动，它每次可以从左、右、上、下移动一格，
 * 但不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如：当k为18时，机器人能够进入方格（35,37），因为3+5+3+7=18。但它不能进入方格（35,38），因为3+5+3+8=19。
 * 请问该机器人能够到达多少个格子
 **/
public class RoboticMotion_13 {

    private static int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows < 0 || cols < 0)
            return 0;
        boolean[] visited = new boolean[rows * cols];
        for (int i = 0; i < rows * cols; i++) {
            visited[i] = false;
        }

        return movingCore(threshold, rows, cols, 0, 0, visited);

    }

    /**
     * 移动计数
     *
     * @param threshold
     * @param rows
     * @param cols
     * @param row
     * @param col
     * @param visited
     * @return
     */
    private static int movingCore(int threshold, int rows, int cols, int row, int col, boolean[] visited) {
        int count = 0;
        if (check(threshold, rows, cols, row, col, visited)) {
            visited[row * cols + col] = true;
            count = 1 + movingCore(threshold, rows, cols, row - 1, col, visited) +
                    movingCore(threshold, rows, cols, row, col - 1, visited) +
                    movingCore(threshold, rows, cols, row + 1, col, visited) +
                    movingCore(threshold, rows, cols, row, col + 1, visited);
        }
        return count;
    }

    /**
     * 判断机器人能否进入坐标为（row,col）的方格
     *
     * @param threshold
     * @param rows
     * @param cols
     * @param row
     * @param col
     * @param visited
     * @return
     */
    private static boolean check(int threshold, int rows, int cols, int row, int col, boolean[] visited) {
        if (row >= 0 && row < rows && col >= 0 && col < cols && getDigitSum(row) + getDigitSum(col) <= threshold && !visited[row * cols + col])
            return true;
        return false;
    }

    /**
     * 一个数字的数位之和
     *
     * @param number
     * @return
     */
    private static int getDigitSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;

    }

    public static void main(String[] args) {
        System.out.println(movingCount(0, 3, 4)); //1
        System.out.println(movingCount(1, 3, 4)); //3
        System.out.println(movingCount(9, 2, 20)); //36

    }
}
