package com.bytedance.arrayandsort;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-05. <br>
 * 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 **/
public class SearchArrayTarget {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt(); //数组大小
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = input.nextInt();
        }
        int target = input.nextInt(); //目标值
        System.out.println(search(nums, target));

    }

    /**
     * 二分法
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        if (nums.length <= 0 || nums == null)
            return -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            //如果相等则返回下标
            if (nums[mid] == target)
                return mid;
            //如果中值大于左值，则中值左边是一个递增子序列
            if (nums[mid] >= nums[left]) {
                //若target大于等于左值和小于右值，则右下标递减
                if (target >= nums[left] && target < nums[mid])
                    right = mid - 1;
                    //反之，则左下标递增
                else {
                    left = mid + 1;
                }
                //小于左值，则右边是一个递增子序列
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid == 0 ? mid : mid - 1;
                }
            }
        }
        return -1;
    }
}