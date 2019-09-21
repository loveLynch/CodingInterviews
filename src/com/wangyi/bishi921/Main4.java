package com.wangyi.bishi921;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-21. <br>
 **/
public class Main4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = input.nextInt();
        }
        System.out.println(getSumOfDistance(nums));
        System.out.println(getSumOfDistance1(nums));

    }

    /**
     * 逆序对：一个有 n 个数字的有序集 (n>1)，其中所有数字各不相同。
     * 如果存在正整数 i, j 使得 1 ≤ i < j ≤ n 而且 A[i] > A[j]，则 <A[i], A[j]> 这个有序对称为 A 的一个逆序对，也称作逆序数
     * <p>
     * 所有逆序对数的下标距离和
     *
     * @param nums
     * @return
     */
    private static long getSumOfDistance1(int[] nums) {
        int dis = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    dis += (j - i);
                }
            }
        }
        return dis;

    }

    private static long getSumOfDistance(int[] nums) {
        long distance = 0;
        int n = nums.length;
        long[] arr1 = new long[n + 1];
        long[] arr2 = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            long from = get(nums[i] - 1, arr1);
            long to = get(nums[i] - 1, arr2);
            distance += to - from * i;
            add(nums[i], 1, arr1);
            add(nums[i], i, arr2);
        }
        return distance;

    }

    private static void add(int num, int i, long[] arr) {
        int n = arr.length;
        while (num < n) {
            arr[num] += i;
            num += num & (-num);

        }
    }

    private static long get(int i, long[] arr) {
        long res = 0;
        while (i != 0) {
            res += arr[i];
            i -= i & (-i);
        }
        return res;

    }
}
