package com.bytedance.bishi;

import java.util.Scanner;

/**
 * 6 2
 * 1110001
 * 101111
 * Created by lynch on 2019-08-11. <br>
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int K = input.nextInt();
        input.nextLine();
        String S = input.nextLine();
        System.out.println(getOrignalText(S, K, N));
    }

    private static String getOrignalText(String s, int k, int n) {
        StringBuilder originalText = new StringBuilder();

        int pre = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - '0';
            if (i >= k) {
                pre ^= originalText.toString().charAt(i - k) - '0';
            }
            val ^= pre;
            pre ^= val;
            originalText.append(val);
        }

        return originalText.toString().substring(0, n);
    }

    public static String getText(String s, int k, int n) {
        StringBuilder original = new StringBuilder();
        int length = n + k - 1;
        int encry[] = new int[length];
        for (int i = 0; i < length; i++) {
            encry[i] = s.charAt(i) - '0';

        }
        int[] res = new int[length + 1];
        res[0] = encry[0];
        res[length] = encry[length - 1];
        for (int j = 0; j < length - 1; j++) {
            res[j + 1] = encry[j] ^ encry[j + 1];
        }
        for (int j = length-1; j < length - n+k+1; j--) {
            res[j] = encry[j] ^ encry[j - 1];
        }
        for (int j = 0; j < k; j++)
            original.append(res[j]);
        for (int j = length - n + k + 1; j < length + 1; j++)
            original.append(res[j]);
        return original.toString();
    }
}
