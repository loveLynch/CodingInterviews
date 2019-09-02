package com.tencent.bishi91;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-01. <br>
 **/
public class Main4_1 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] score = new int[n];
        for (int i = 0; i < n; i++) {
            score[i] = input.nextInt();
        }
        System.out.println(getMaxScore(score));

    }

    private static long getMaxScore(int[] score) {
        long maxScore = 0;

        for (int i = 0; i < score.length; i++) {
            int min = score[i];
            long sum = 0;

            for (int j = i; j < score.length; j++) {
                min = Math.min(score[j], min);

                sum += score[j];
                long temp = sum * min;
                if (temp > maxScore) {
                    maxScore = temp;
                }
            }

        }
        return maxScore;
    }
}
