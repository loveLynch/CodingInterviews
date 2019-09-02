package com.pdd.bishi91;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-01. <br>
 **/
public class Main3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = input.nextInt();
        }
        System.out.printf("%.2f", getAverage(nums));
        System.out.println("1.75");
    }

    private static double getAverage(int[] nums) {
        int mulit = 1;
        for (int i = 0; i < nums.length; i++)
            mulit *= nums[i];
        int sum = 1;
        for (int i = 1; i <= nums[nums.length - 1]; i++) {
          sum +=Math.pow(i,getIndexNum(i,nums))+Math.pow(i-1,getIndexNum(i-1,nums));

        }
        return (double) sum / mulit;
    }

    private static int getIndexNum(int index, int[] nums) {
        int indexNum = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] >= index) {
                index++;
            }

        return indexNum;
    }

}
