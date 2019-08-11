package com.pdd;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-11. <br>
 **/
public class SumOfK {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int S = input.nextInt();
        System.out.println(getCountSumOfK(N, S));
    }

    public static long getCountSumOfK(int n, int s) {
        long count = 0;
        if (n == 1)
            return 1;
        int j = 1;
        if (n > 2) {
            while (sumOfN(j, n) <= s) {
                count += getSingleCount(n, s, j);
                j++;
            }
        } else {
            count = getSingleCount(n, s, j);
        }
        return count;
    }

    private static long getSingleCount(int n, int s, int j) {
        long count = 0;
        int[] nums = new int[n];
        for (int i = 0; i < nums.length - 1; i++) {
            nums[i] = j;
            j++;
        }
        while (s - sumOfArray(nums) > nums[n - 2]) {
            count++;
            nums[n - 2]++;
        }
        return count % 1000000007;

    }

    public static int sumOfN(int j, int n) {
        int sum = 0;
        for (int i = j; i < j + n; i++)
            sum += i;
        return sum;
    }

    public static int sumOfArray(int[] nums) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        return sum;
    }
}
