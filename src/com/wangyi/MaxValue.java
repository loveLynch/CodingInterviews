package com.wangyi;

import java.util.Scanner;

/**
 * @author Lynch
 * @date 2019/8/2 17:0
 * [编程题]表达式求值
 * 时间限制：1秒
 *
 * 空间限制：262144K
 *
 * 今天上课，老师教了小易怎么计算加法和乘法，乘法的优先级大于加法，但是如果一个运算加了括号，那么它的优先级是最高的。例如：
 * 1+2*3=7
 * 1*(2+3)=5
 * 1*2*3=6
 * (1+2)*3=9
 * 现在小易希望你帮他计算给定3个数a，b，c，在它们中间添加"+"， "*"， "("， ")"符号，能够获得的最大值。
 *
 * 输入描述:
 * 一行三个数a，b，c (1 <= a, b, c <= 10)
 *
 * 输出描述:
 * 能够获得的最大值
 *
 * 输入例子1:
 * 1 2 3
 *
 * 输出例子1:
 * 9
 *
 */
public class MaxValue {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] nums = new int[3];
        for (int i = 0; i < 3; i++)
            nums[i] = input.nextInt();
        System.out.println(getMaxValue(nums));
    }

    private static int getMaxValue(int[] nums) {
        int value = 0;
        if (nums[0] >= nums[1] && nums[1] >= nums[2]) {
            value = nums[0] * (nums[1] + nums[2]);
        } else if (nums[0] <= nums[1] && nums[1] <= nums[2]) {
            value = (nums[0] + nums[1]) * nums[2];

        } else {
            if (nums[0] >= nums[2]) {
                value = nums[0] * (nums[1] + nums[2]);
            } else {
                value = (nums[0] + nums[1]) * nums[2];

            }
        }
        if (nums[0]*nums[1]*nums[2]>value)
            value = nums[0]*nums[1]*nums[2];
        return value;
    }
}
