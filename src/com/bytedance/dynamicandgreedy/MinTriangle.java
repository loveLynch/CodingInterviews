package com.bytedance.dynamicandgreedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-08-11. <br>
 * 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 说明：
 * <p>
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分
 **/
public class MinTriangle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int level = input.nextInt(); //行数
        List<List<Integer>> triangle = new ArrayList<>();
        int lati = 1;
        for (int i = 0; i < level; i++) {
            List<Integer> latiList = new ArrayList<>();
            for (int j = 0; j < lati; j++) {
                latiList.add(input.nextInt());
            }
            lati++;
            triangle.add(latiList);
        }
        System.out.println(minimumTotal(triangle));


    }

    /**
     * 自低向上，动态规划
     * 1.申请n个空间minNums[n]，初始化minNums[n]为数据triangle[][]的最后一行。
     * 最后一行的数字到最底层的最小路径和就是他们自己本身。
     * 2.从倒数第二行开始往上（row），从左向右（col）循环计算并更新minNums的值，
     * minNums[col]=min(minNums[col], minNums[col+1]) + triangle[row][col]
     * 3.最后minNums[0]就是我们要的答案。
     *
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() <= 0)
            return 0;
        int[] dp = new int[triangle.size()];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (i == triangle.size() - 1) {
                    dp[j] = triangle.get(i).get(j);  //初始化最后一层dp
                } else {
                    dp[j] = Math.min(dp[j] + triangle.get(i).get(j), dp[j + 1] + triangle.get(i).get(j));
                }
            }

        }
        return dp[0];
    }
}
