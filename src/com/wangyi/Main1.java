package com.wangyi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-08-03. <br>
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = Integer.parseInt(input.nextLine());
        int[] score = new int[N];
        for (int i = 0; i < N; i++)
            score[i] = input.nextInt();
        input.nextLine();
        int Q = Integer.parseInt(input.nextLine());
        List<Integer> questionList = new ArrayList<>();
        for (int i = 0; i < Q; i++) {
            questionList.add(Integer.valueOf(input.nextLine()));
        }
        for (int question : questionList) {
            getGailv(score, question);
        }
    }

    private static void getGailv(int[] score, int question) {
        int[] tempScore = score.clone();
        Arrays.sort(tempScore);
        int index = 0;
        while (index < tempScore.length && tempScore[index] <= score[question - 1]) {
            index++;
        }
        System.out.printf("%.6f\n", 100.0 * (index - 1) / score.length);
    }
}
