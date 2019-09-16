package com.kuaishou;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by lynch on 2019-09-16. <br>
 **/
public class Main4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = input.nextInt();
        }
        System.out.println(getLengthOfCha(nums));
    }

    private static int getLengthOfCha(int[] nums) {
        if (nums.length == 0 || nums == null)
            return 0;
        if (nums.length == 1)
            return 1;
        if (nums.length == 2)
            return 2;
        Arrays.sort(nums);
        int maxLength = 0;
        int temp = 2;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i + 1] - nums[i] == nums[i] - nums[i - 1]) {
                temp++;
                maxLength = temp;
            } else {
                if (maxLength < temp) {
                    maxLength = temp;
                }
                temp = 2;
            }
        }

        return maxLength;
    }
}
