package com.bytedance.arrayandsort;

import java.util.Scanner;

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
        System.out.println(findLengthOfLCIS1(nums));
        System.out.println(LongestSubString(nums));
        System.out.println(getLength(nums));
    }

    /**
     * 最长连续递增子序列
     *
     * @param nums
     * @return
     */
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

    public static int findLengthOfLCIS1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] length = new int[nums.length];
        length[0] = 1;

        int maxLen = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                length[i] = length[i - 1] + 1;
            } else {
                length[i] = 1;
            }

            maxLen = Math.max(maxLen, length[i]);
        }

        return maxLen;
    }

    /**
     * 最长递增序列
     *
     * @param arr
     * @return
     */
    public static int LongestSubString(int arr[]) {
        int len = 0;
        if (arr == null || arr.length == 0)
            return 0;
        int dp[] = new int[arr.length];
        dp[0] = 1;
        //dp[i] 表示到i为止是最长递增子序列的长度
        for (int i = 1; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    //求dp[i]时遍历，dp[0...i-1],找出arr[j]<arr[i]小且dp[j]是最大的
                    //dp[i]=dp[j]+1;
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            len = Math.max(dp[i], len);
        }
        return len;
    }

    /**
     * 最长递增序列
     *
     * @param array
     * @return
     */
    private static int getLength(int[] array) {
        int[] assistArray = new int[array.length];
        assistArray[0] = array[0];
        int length = 0;
        for (int number : array) {
            if (assistArray[length] < number) {
                assistArray[++length] = number;
            } else {
                int low = 0;
                int high = length;
                while (low < high) {
                    int middle = (low + high) >> 1;
                    if (assistArray[middle] < number) {
                        low = middle + 1;
                    } else {
                        high = middle - 1;
                    }
                }
                if (assistArray[low] < number && (low + 1) < assistArray.length) {
                    assistArray[low + 1] = number;
                } else {
                    assistArray[low] = number;
                }
            }
        }
        for (int tmp : assistArray) {
            System.out.print(tmp + " ");
        }
        System.out.println();
        return length + 1;
    }
}
