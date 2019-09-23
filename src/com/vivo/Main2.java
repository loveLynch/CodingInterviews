package com.vivo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Welcome to vivo !
 * 约瑟夫环
 * input
 * 6 3
 * output
 * 3 6 4 2 5 1
 */

public class Main2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        int input[] = parseInts(inputStr.split(" "));
        String output = solution(input);
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

    private static String solution(int[] input) {
        int N = input[0];
        int M = input[1];
        StringBuilder result = new StringBuilder();
        List<Integer> start = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            start.add(i);
        }
        int k = 0;
        while (start.size() > 0) {
            k = k + M;
            k = k % (start.size()) - 1;
            if (k < 0) {
                result.append(start.get(start.size() - 1));
                result.append(" ");
                start.remove(start.size() - 1);
                k = 0;
            } else {
                result.append(start.get(k));
                result.append(" ");
                start.remove(k);
            }
        }
        return result.toString();
    }

}