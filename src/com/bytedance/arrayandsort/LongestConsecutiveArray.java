package com.bytedance.arrayandsort;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by lynch on 2019-08-06. <br>
 * 最长连续序列
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * <p>
 * 要求算法的时间复杂度为 O(n)。
 * <p>
 * 示例:
 * <p>
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
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