package com.vivo;

import java.io.*;

/**
 * Welcome to vivo !
 * ↔左右砝码，要求两边差值最小；
 * 且砝码的数量差值不能超过1
 * input
 * 3 7 4 11 8 10
 * output
 * 1
 */

public class Main3 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        int input[] = parseInts(inputStr.split(" "));
        int output = solution(input);
        System.out.println(output);
    }

    private static int[] parseInts(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return new int[0];
        }
        int[] intArr = new int[strArr.length];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }

    private static int solution(int[] input) {
//        Arrays.sort(input);
//        int left = 0;
//        int right = 0;
//        for (int i = input.length - 1; i >= 0; i--) {
//            if (left < right) {
//                left += input[i];
//            } else {
//                right += input[i];
//            }
//        }
//        return Math.abs(left - right);
        if (input.length == 0 || input == null)
            return 0;
        int sum = 0;
        for (int s : input) {
            sum += s;
        }
        int length = input.length;
        int[][] dp = new int[length + 1][sum / 2 + 1];
        for (int i = 0; i < length; i++) {
            for (int j = 1; j <= sum / 2; j++) {
                dp[i + 1][j] = dp[i][j];
                if (input[i] <= j && dp[i][j - input[i]] + input[i] > dp[i][j]) {
                    dp[i + 1][j] = dp[i][j - input[i]] + input[i];
                }
            }
        }
        return sum - 2 * dp[length][sum / 2];
    }
}