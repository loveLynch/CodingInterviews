package com.vivo;

import java.io.*;

/**
 * Welcome to vivo !
 * 最少多少步到跳跃到数组最后
 * 每个数组即为从当前能往前跳跃的最大步数
 * input
 * 2 2 3 0 4
 * output
 * 2
 */
public class Main1 {

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

    /**
     * 动态规划
     *
     * @param input
     * @return
     */
    private static int solution(int[] input) {
        if (input.length == 0 || input == null)
            return -1;
        int length = input.length;
        int step[] = new int[length];
        for (int i = 1; i < length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                int dis = i - j;
                if (input[j] >= dis)
                    min = Math.min(min, step[j]);

            }
            if (min == Integer.MAX_VALUE)
                return -1;

            step[i] = min + 1;
        }
        return step[length - 1];
    }

    private static int solution2(int[] input) {
        if (input.length == 0 || input == null)
            return -1;
        int sum = input[0];
        if (input[0] >= input.length)
            return 1;
        int step = 0;
        for (int i = 0; i < input.length; i++) {
            int num = input[i];
            if (sum >= input.length)
                break;
            if (num < input.length) {
                sum += input[num];
                step++;
            }


        }
        if (sum < input.length)
            return -1;
        return step;

    }


}