package com.pdd;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个长度为偶数的数组arr，将该数组中的数字两两配对并求和，
 * 在这些和中选出最大和最小值，请问该如何两两配对，才能让最大值和最小值的差值最小？
 * Created by lynch on 2019-07-22. <br>
 **/
public class MinDiffenerceInMaxMIn {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(getMinDifference(arr, n));
    }

    public static int getMinDifference(int[] arr, int n) {
        //对数组进行升序排序
        Arrays.sort(arr);
        int[] newArr = new int[n / 2];
        //两两匹配，排序后保证所有和差值最小
        for (int i = 0; i < n / 2; i++)
            newArr[i] = arr[i] + arr[n - i - 1];
        //对新数组排序，求最小差值
        Arrays.sort(newArr);
        int min = newArr[newArr.length - 1] - newArr[0];
        return min;

    }
}
