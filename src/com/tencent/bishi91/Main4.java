package com.tencent.bishi91;


import java.util.Scanner;

/**
 * Created by lynch on 2019-09-01. <br>
 **/
public class Main4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] score = new int[n];
        for (int i = 0; i < n; i++) {
            score[i] = input.nextInt();
        }
        System.out.println(getMaxScore(score));
    }

    private static int getMaxScore(int[] score) {
        int maxScore = 0;
        for (int i = 0; i < score.length; i++) {
            int temp = calSocre(score, i);
            maxScore = Math.max(temp, maxScore);

        }

        return maxScore;
    }

    private static int getSum(int start, int end, int[] nums) {
        int sum = 0;
        for (int i = start; i <= end; i++)
            sum += nums[i];

        return sum;

    }

    private static int calSocre(int[] nums, int index) {
        int min = nums[index];
        int score = 0;
        for (int i = index; i < nums.length; i++) {
            if (min >= nums[i]) {
                min = nums[i];
            }
            int temp = min * getSum(index, i, nums);
            score = Math.max(temp, score);
        }
        return score;
    }

    private static int getMinIndex(int start, int end, int[] nums) {
        int min = nums[0];
        int minTh = start;
        for (int i = start; i <= end; i++) {
            if (min > nums[i]) {
                min = nums[i];
                minTh = i;

            }
        }
        return minTh;
    }
}
