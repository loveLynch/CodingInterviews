package com.lynch;

/**
 * Created by lynch on 2019-03-28. <br>
 * 42.连续子数组的最大和
 *
 * 输入一个整型数组，数组里面有正数也有负数。数组中的一个或连续多个整数组成一个子数组。
 * 求所有子数组的和的最大值。要求时间复杂度为O(n)
 **/
public class FindGreatestSumOfSUbArray_42 {
    /**
     * 分析数组规律
     *
     * @param data
     * @return
     */
    private static int findGreastestSum(int[] data) {
        if (data == null || data.length <= 0) {
            return 0;
        }
        int curSum = 0;
        int greastestSum = Integer.MIN_VALUE;//int的最小值
        for (int i = 0; i < data.length; i++) {
            if (curSum <= 0)
                curSum = data[i];
            else {
                curSum += data[i];
            }
            if (curSum > greastestSum)
                greastestSum = curSum;
        }
        return greastestSum;

    }

    /**
     * 动态规划，递归公式：dp[i] =  data[i]          i=0或dp[i-1]<=0
     *                            dp[i-1]+data[i]  i!=0且dp[i-1]>0
     * 由于只需知道前一个情况的dp值，因此可省去dp数组，申请个变量记录即可
     *
     * @param data
     * @return
     */
    private static int findGreatestSumOfSumArrays(int[] data) {
        if (data == null || data.length == 0)
            return 0;
        //dp[i]用于计算以data[i]为结尾元素的连续数组的最大和
        //maxdp用于记录在上述过程中的最大的dp值
        int dp = data[0], maxdp = dp;
        for (int i = 1; i < data.length; i++) {
            if (dp > 0)
                dp += data[i];
            else
                dp = data[i];
            if (dp > maxdp)
                maxdp = dp;
        }
        return maxdp;
    }

    public static void main(String[] args) {
        int[] data = {1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println(findGreatestSumOfSumArrays(data));
        System.out.println(findGreastestSum(data));
    }
}
