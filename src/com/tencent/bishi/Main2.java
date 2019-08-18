package com.tencent.bishi;


import java.util.Scanner;

/**
 * Created by lynch on 2019-08-17. <br>
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int N = (int) Math.pow(2, n);
        int[] original = new int[N];
        for (int i = 0; i < N; i++) {
            original[i] = input.nextInt();
        }
        int m = input.nextInt();
        int[] nums = new int[m];
        for (int i = 0; i < m; i++) {
            nums[i] = input.nextInt();
        }
        for (int j = 0; j < m; j++) {
            int k = (int) Math.pow(2, nums[j]);
            int[] reserve = reverseArray(original, k);
            original = reserve.clone();
            System.out.println(inversePair(reserve));

        }
    }

    public static int[] reverseArray(int[] original, int k) {
        int[] array = new int[original.length];
        if (k == 1)
            return original;
        //超过数组长度直接倒序返回
        if (k >= original.length) {
            int m = 0;
            for (int n = original.length - 1; n >= 0; n--) {
                array[m] = original[n];
                m++;
            }
            return array;
        }
        int i = 0;
        int j = 0;
        while (i < original.length) {
            if ((i + 1) % k == 0) {
                for (int x = i; x > i - k && x >= 0; x--) {
                    array[j] = original[x];
                    j++;
                }
            }
            i++;
        }
        return array;

    }

    private static int inversePair(int[] array) {

        if (array == null || array.length < 0) {
            return 0;
        }
        int[] copy = array.clone();
        int count = inversePairCore(array, copy, 0, array.length - 1);
        return count;

    }

    private static int inversePairCore(int[] array, int[] copy, int start, int end) {
        if (start == end) {
            copy[start] = array[start];
            return 0;
        }
        int length = (end - start) / 2;
        int left = inversePairCore(copy, array, start, start + length);
        int right = inversePairCore(copy, array, start + length + 1, end);
        int i = start + length;
        int j = end;
        int indexCopy = end;
        int count = 0;
        while (i >= start && j >= start + length + 1) {
            if (array[i] > array[j]) {
                copy[indexCopy--] = array[i--];
                count += j - start - length;
            } else {
                copy[indexCopy--] = array[j--];
            }
        }
        for (; i >= start; i--) {
            copy[indexCopy--] = array[i];
        }
        for (; j >= start + length + 1; j--) {
            copy[indexCopy--] = array[j];
        }
        return left + right + count;

    }
}
