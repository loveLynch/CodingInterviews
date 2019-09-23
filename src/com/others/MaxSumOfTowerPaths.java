package com.others;

/**
 * Created by lynch on 2019-09-22. <br>
 * 数字塔从上到下所有路径中和最大的路径
 * 3
 * <p>
 * 1    5
 * <p>
 * 8    4    3
 * <p>
 * 2    6    7    9
 * <p>
 * 6    2    3    5    1
 **/
public class MaxSumOfTowerPaths {
    public static void main(String[] args) {
        int[][] nums = new int[][]{{3}, {1, 5}, {8, 4, 3}, {2, 6, 7, 9}, {6, 2, 3, 5, 1}};
        System.out.println(maxNumberInRotateArray(nums));
        System.out.println(maxNumberInRotateArray2(nums));
        System.out.println(maxNumberInRotateArray3(nums));
    }

    /**
     * 从上往下看：当从上往下看时，每进来新的一行，新的一行每个元素只能选择他正上方或者左左方的元素，
     * 也就是说，第一个元素只能连他上方的元素，最后一个元素只能连他左上方的元素，
     * 其他元素可以有两种选择，所以需要选择加起来更大的那一个数字，并把这个位置上的数字改成相应的路径值
     *
     * @param n
     * @return
     */
    public static int maxNumberInRotateArray(int n[][]) {
        int max = 0;
        int dp[][] = new int[n.length][n.length];
        dp[0][0] = n[0][0];
        for (int i = 1; i < n.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    //如果是第一列，直接跟他上面数字相加
                    dp[i][j] = dp[i - 1][j] + n[i][j];
                } else {
                    //如果不是第一列，比较他上面跟上面左面数字谁大，谁大就跟谁相加，放到这个位置
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + n[i][j];
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }

    /**
     * 从上往下看优化
     * 二维降成一维
     *
     * @param n
     * @return
     */
    public static int maxNumberInRotateArray2(int n[][]) {
        int[] temp = new int[n.length];
        temp[0] = n[0][0];
        for (int i = 1; i < n.length; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == i) {
                    temp[i] = temp[i - 1] + n[i][j];
                } else if (j == 0) {
                    temp[0] += n[i][0];
                } else {
                    temp[j] = Math.max(temp[j], temp[j - 1]) + n[i][j];
                }
            }
        }
        int max = temp[0];
        //从temp数组里取出最大的值
        for (int i = 1; i < temp.length; i++) {
            if (temp[i] > max) {
                max = temp[i];
            }
        }
        return max;
    }


    /**
     * 从下往上看时：从下往上看时大体思路跟从上往下看一样，但是要简单一些，因为不用考虑边界数据，
     * 从下往上看时，每进来上面一行，上面一行每个数字有两条路径到达下面一行，所以选一条最大的就可以
     *
     * @param n
     * @return
     */
    public static int maxNumberInRotateArray3(int n[][]) {
        int[] temp = new int[n.length];
        for (int i = 0; i < n.length; i++) {
            temp[i] = n[n.length - 1][i];
        }
        for (int i = n.length - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                temp[j] = Math.max(temp[j], temp[j + 1]) + n[i][j];
            }
        }
        return temp[0];
    }

}
