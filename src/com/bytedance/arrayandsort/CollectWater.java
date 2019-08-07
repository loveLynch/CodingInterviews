package com.bytedance.arrayandsort;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-07. <br>
 * 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 **/
public class CollectWater {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int[] height = new int[N];
        for (int i = 0; i < N; i++) {
            height[i] = input.nextInt();
        }
        System.out.println(trap(height));
    }

    public static int trap(int[] height) {
        if (height.length <= 2)
            return 0;
        int water = 0;
        int left = 0;
        int right = height.length - 1;
        int leftH = 0, rightH = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                //从左向右扫描，获取每个位置左边最高的边
                if (leftH <= height[left]) {
                    leftH = height[left];
                } else {
                    water += leftH - height[left];
                }
                left++;
            } else {
                //从右向左扫描，获取每个位置右边最高的边
                if (rightH <= height[right])
                    rightH = height[right];
                else {
                    water += rightH - height[right];
                }
                right--;
            }
        }
        return water;
    }

}