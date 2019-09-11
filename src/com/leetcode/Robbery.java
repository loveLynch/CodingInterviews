package com.leetcode;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-09. <br>
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class Robbery {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = input.nextInt();
        }
        System.out.println(rob(nums));

    }

    /**
     * 分两种情况
     * 第一种情况下，状态空间只包括nums[0]......nums[n-1]（即不包含最后一个元素）
     * 第二种则是nums[1]......nums[n] (即不包含第一个元素)，返回两种情况中的较大者即可
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        int[] dp1 = new int[nums.length];
        int[] dp2 = new int[nums.length];
        dp1[0] = 0;
        dp2[0] = 0;
        dp1[1] = nums[0];
        dp2[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp1[i] = Math.max(dp1[i - 1], nums[i - 1] + dp1[i - 2]);
            dp2[i] = Math.max(dp2[i - 1], nums[i] + dp2[i - 2]);

        }

        return Math.max(dp1[nums.length - 1], dp2[nums.length - 1]);
    }
}
