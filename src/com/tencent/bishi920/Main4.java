package com.tencent.bishi920;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-09-20. <br>
 **/
public class Main4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int K = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = input.nextInt();
        }
        List<Integer> results = changeNums(K, nums);
        for (int result : results) {
            System.out.println(result);
        }
    }

    private static List<Integer> changeNums(int K, int[] num) {
        List<Integer> results = new ArrayList<>();

        Arrays.sort(num);

        int accum = 0, i = 0, len = num.length;
        boolean over = false;
        for (int k = 0; k < K; k++) {

            while (i < len && num[i] - accum <= 0) i++;
            if (i >= len) over = true;
            if (over) {
                results.add(0);
                continue;
            }

            int out = num[i];
            int diff = out - accum;
            results.add(diff);
            accum += diff;
        }
        return results;
    }


    private static int[] lessOfX(int x, int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            nums[i] -= x;
            if (nums[i] == 0) {
                nums[i] = nums[i + 1];
                count++;
            }
        }
        int[] newArr = new int[nums.length - count];
        System.arraycopy(nums, 0, newArr, 0, newArr.length);
        return newArr;

    }

}
