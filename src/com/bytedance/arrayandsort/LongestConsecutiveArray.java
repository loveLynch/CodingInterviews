package com.bytedance.arrayandsort;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by lynch on 2019-08-06. <br>
 * 最长连续递增序列
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
 **/
public class LongestConsecutiveArray {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = input.nextInt();
        }
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0 || nums == null)
            return 0;
        int maxLength = 0;
        Set<Integer> store = new HashSet<>();
        //利用set去重，存储各元素
        for (int num : nums)
            store.add(num);
        for (int i = 0; i < nums.length; i++) {
            //判断num[i]-1是否在set中，从而确定最小的起点
            if (!store.contains(nums[i] - 1)) {
                int y = nums[i] + 1;
                //一次累加，判断该元素是否在集合中
                while (store.contains(y))
                    y++;
                maxLength = Math.max(maxLength, y - nums[i]);
            }
        }

        return maxLength;
    }
}