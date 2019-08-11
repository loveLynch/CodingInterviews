package com.bytedance;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int len = N + K - 1;
        scanner.nextLine();
        String str = scanner.nextLine();
        int[] a = new int[len];
        for (int i = 0; i < len; i++) {
            a[i] = str.charAt(i) - '0';
        }
        int[] res = new int[len + 1];
        res[0] = a[0];
        res[len] = a[len - 1];
        for (int i = 0; i < K; i++) {
            res[i + 1] = a[i] ^ a[i + 1];
        }
        for (int i = len - 1; i >= len - N + K + 1; i--) {
            res[i] = a[i] ^ a[i - 1];
        }
        for (int i = 0; i < K; i++) {
            System.out.print(res[i]);
        }
        for (int i = len - N + K + 1; i < len + 1; i++) {
            System.out.print(res[i]);
        }

    }
}