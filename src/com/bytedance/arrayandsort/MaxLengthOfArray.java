package com.bytedance.arrayandsort;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-06. <br>
 * 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,5,4,7]
 * 输出: 3
 * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
 * 示例 2:
 * <p>
 * 输入: [2,2,2,2,2]
 * 输出: 1
 * 解释: 最长连续递增序列是 [2], 长度为1。
 * 注意：数组长度不会超过10000。
 */
public class MaxLengthOfArray {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = input.nextInt();
        }
        System.out.println(findLengthOfLCIS(nums));
    }

    public static int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0 || nums == null)
            return 0;
        if (nums.length == 1)
            return 1;
        int maxLength = 0;
        int left = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                maxLength = Math.max(maxLength, i - left);
                left = i;
            }
            if (i == nums.length - 1) {
                maxLength = Math.max(maxLength, i - left + 1);
            }
        }
        return maxLength;
    }
}
