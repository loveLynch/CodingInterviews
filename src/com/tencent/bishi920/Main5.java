package com.tencent.bishi920;

/**
 * Created by lynch on 2019-09-20. <br>
 * input
 * 5
 * 1 2 1 0 0
 * 1 2 3 0 0
 * output
 * 2
 **/

import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] num1 = new int[N];
        for (int n = 0; n < N; n++) {
            num1[n] = sc.nextInt();
        }
        int[] num2 = new int[N];
        for (int n = 0; n < N; n++) {
            num2[n] = sc.nextInt();
        }
        System.out.println(process(num1, num2));
    }

    public static int process(int[] num1, int[] num2) {

        int len1 = num1.length;
        int len2 = num2.length;
        int res = 0, t;
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                t = num1[i] + num2[j];
                res ^= t;
            }
        }
        return res;
    }
}