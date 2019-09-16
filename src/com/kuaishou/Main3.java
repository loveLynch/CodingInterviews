package com.kuaishou;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-16. <br>
 **/
public class Main3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = input.nextInt();
        }
        System.out.println(getMinCha(nums));
    }

    private static int getMinCha(int[] nums) {
        int s1 = 0;
        int s2 = 0;
        if (nums.length == 1)
            return nums[0];
        int mid = 1;
        s1 = sumOfLeft(nums, mid);
        s2 = sumOfRight(nums, mid);
        int cha = Math.abs(s1 - s2);
        while (mid < nums.length) {
            mid++;
            s1 = sumOfLeft(nums, mid);
            s2 = sumOfRight(nums, mid);
            if (cha > Math.abs(s1 - s2)) {
                cha = Math.abs(s1 - s2);
                mid++;
            } else {
                break;
            }
        }
        return Math.abs(sumOfLeft(nums, mid - 1) - sumOfRight(nums, mid - 1));
    }

    private static int sumOfLeft(int[] nums, int mid) {
        int s1 = 0;
        for (int i = 0; i < mid; i++) {
            s1 += nums[i];
        }
        return s1;

    }

    private static int sumOfRight(int[] nums, int mid) {
        int s2 = 0;
        for (int i = mid; i < nums.length; i++) {
            s2 += nums[i];
        }
        return s2;
    }
}
