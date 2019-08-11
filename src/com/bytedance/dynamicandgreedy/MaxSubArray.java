package com.bytedance.dynamicandgreedy;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-10. <br>
 * 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 **/
public class MaxSubArray {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = input.nextInt();
        System.out.println(maxSubArray(nums));
        System.out.println(maxSubArray1(nums));
        System.out.println(maxSubArray2(nums));

    }

    /**
     * 分治法 O(nlogn)
     * 将一个数组分为两部分，关于它的最大子数组存在如下三种情况：
     * 1.最大子数组属于第一部分 2.属于第二部分 3.横跨两个部分
     * 只需将这三个可能的值求出来，它们中的最大值即为最大子数组之和
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length == 0)
            return 0;
        return maxSubArraySum(nums, 0, nums.length - 1);
    }


    private static int maxSubArraySum(int[] nums, int l, int r) {
        if (l == r) {
            return nums[l];
        }
        int mid = l + (r - l) / 2;
        return max3(maxSubArraySum(nums, l, mid),
                maxSubArraySum(nums, mid + 1, r),
                maxCrossingSum(nums, l, mid, r));
    }

    private static int max3(int num1, int num2, int num3) {
        return Math.max(num1, Math.max(num2, num3));
    }

    private static int maxCrossingSum(int[] nums, int l, int m, int r) {
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        // 左半边包含 nums[mid] 元素，最多可以到什么地方
        // 走到最边界，看看最值是什么
        // 计算以 mid 结尾的最大的子数组的和
        for (int i = m; i >= l; i--) {
            sum += nums[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        // 右半边不包含 nums[mid] 元素，最多可以到什么地方
        // 计算以 mid+1 开始的最大的子数组的和
        for (int i = m + 1; i <= r; i++) {
            sum += nums[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }
        return leftSum + rightSum;
    }

    /**
     * 迭代
     * 贪心法，将子串和为负数的子串丢掉，只留和为正的子串。O(n)
     *
     * @param nums
     * @return
     */
    public static int maxSubArray1(int[] nums) {
        if (nums.length == 0)
            return 0;
        int sum = 0;
        int ans = nums[0];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > ans) {
                ans = sum;
            }
            if (sum < 0) {
                sum = 0;   //子串和为负数，丢掉
            }
        }
        return ans;


    }

    /**
     * 动态规划 O(n)
     * dp(n) = nums[n] + max(dp(n-1), 0)
     * 其中f(n)表示以第n个元素结尾的最大子序列和
     *
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        int max = dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
//            if (dp[i - 1] < 0)
//                dp[i] = nums[i];
//            else {
//                dp[i] = dp[i - 1] + nums[i];
//            }
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            if (dp[i] > max)
                max = dp[i];
        }
        return max;
    }
}
