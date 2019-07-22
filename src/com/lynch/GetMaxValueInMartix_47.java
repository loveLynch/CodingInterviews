package com.lynch;


import com.lynch.structure.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by lynch on 2019-03-28. <br>
 * 47.礼物的最大值
 * <p>
 * 在一个m*n的棋盘的每一个格都放有一个礼物，每个礼物都有一定价值（大于0）。
 * 从左上角开始拿礼物，每次向右或向下移动一格，直到右下角结束。给定一个棋盘，
 * 求拿到礼物的最大价值。例如，对于如下棋盘
 * 1    10   3    8
 * 12   2    9    6
 * 5    7    4    11
 * 3    7    16   5
 **/
public class GetMaxValueInMartix_47 {
    /**
     * 动态规划
     *
     * @param values
     * @return
     */
    private static int getMaxValue_ONE(int[][] values) {
        if (values == null)
            return 0;
        int rows = values.length;
        int cols = values[0].length;
        if (cols <= 0 || rows <= 0)
            return 0;
        int[][] maxValues = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int fromLeft = 0; //左边
                int fromUp = 0; //上面

                if (i > 0)
                    fromUp = maxValues[i - 1][j];
                if (j > 0)
                    fromLeft = maxValues[i][j - 1];

                maxValues[i][j] = Math.max(fromLeft, fromUp) + values[i][j];
            }
        }
        return maxValues[rows - 1][cols - 1];
    }

    /**
     * 度优先遍历
     * 这个棋盘其实可以看成一个有向图，起点为左上角，终点为右下角，
     * 每一点仅仅指向右侧和下侧。因此我们可以从左上角开始进行广度优先遍历。
     * 此外，遍历过程中可以进行剪枝，最终移动到右下角时会仅剩下一个枝，
     * 该路径所经的点的数值之和即为所求
     * @param data
     * @return
     */
    private static int getMaxValue_TWO(int[][] data) {

        if (data.length == 0 || data[0].length == 0)
            return 0;
        int maxRowIndex = data.length - 1;
        int maxColIndex = data[0].length - 1;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, data[0][0]));
        Node nodeCur = null;
        while (!(queue.peek().row == maxRowIndex && queue.peek().col == maxColIndex)) {
            nodeCur = queue.poll();
            if (nodeCur.row != maxRowIndex)
                queue.offer(new Node(nodeCur.row + 1, nodeCur.col, nodeCur.sum + data[nodeCur.row + 1][nodeCur.col]));
            if (nodeCur.col != maxColIndex)
                queue.offer(new Node(nodeCur.row, nodeCur.col + 1, nodeCur.sum + data[nodeCur.row][nodeCur.col + 1]));
        }
        int maxSum = 0, temp = 0;
        while (!queue.isEmpty()) {
            temp = queue.poll().sum;
            if (temp > maxSum)
                maxSum = temp;
        }
        return maxSum;
    }


    public static void main(String[] args) {
        int[][] data = {
                {1, 10, 3, 8},
                {12, 2, 9, 6},
                {5, 7, 4, 11},
                {3, 7, 16, 5}};
        System.out.println(getMaxValue_ONE(data));
        System.out.println(getMaxValue_TWO(data));
    }
}
