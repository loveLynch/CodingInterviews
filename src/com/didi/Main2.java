package com.didi;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-19. <br>
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = input.nextInt();
        }
        System.out.println(getMinSum(nums, m));
    }

    private static int getMinSum(int[] nums, int m) {
        int start = 0;
        int end = m - 1;
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += nums[i];
        }
        int index = 0;
        int temp = 0;
        while (index < nums.length) {
            while (end < nums.length - 1 && nums[start] > nums[end + 1]) {
                index++;
                start++;
                end++;
                sum += (nums[end] - nums[start - 1]);
            }
            while (end < nums.length - 1 && nums[end + 1] < 0) {
                index++;
                end++;
                sum += nums[end];

            }
            for (int i = end + 1; i < index; i++) {
                temp += nums[i];
                if (temp < 0) {
                    int count = i - end;
                }
            }
            index++;

        }
        return sum;

    }
}
