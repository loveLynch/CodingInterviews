package com.wangyi.bishi921;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-21. <br>
 **/
public class Main3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = input.nextInt();
            }
            System.out.println(getMaxLength(nums));
        }
    }

    /**
     * 满足每个数字大于前面所有数字的和
     * nums中的最长长度
     *
     * @param nums
     * @return
     */
    private static int getMaxLength(int[] nums) {
        int maxLength = 1;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i + maxLength >= length)
                break;
            long sum = 0;
            for (int j = i; j < length; j++) {
                if (nums[j] < sum)
                    break;
                sum += nums[j];
                maxLength = Math.max(maxLength, j - i + 1);
            }

        }
        return maxLength;
    }
}
