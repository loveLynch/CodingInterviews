package com.xiecheng;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main3 {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int schedule(int m, int[] array) {
        int[] arrayClone = array.clone();
        Arrays.sort(arrayClone);
        int max = arrayClone[arrayClone.length - 1];
        if (array.length <= m) {
            return max;
        } else if (m == 1) {
            int sum = 0;
            for (int num : array)
                sum += num;
            return sum;
        } else {
            List<Integer> result = new ArrayList<>();
            int temp = 0;
            for (int i = 0; i < array.length; i++) {
                if (temp < max) {
                    temp += array[i];
                } else {
                    result.add(temp);
                    temp = 0;

                }
            }
            Collections.sort(result);
            return result.get(result.size() - 1);
        }

    }

    static int schedule1(int m, int[] array) {
        int sum = 0;
        // 选择一个满足条件的步长，并先给出一个上界
        int step = array.length / m;
        if (array.length % m != 0)
            ++step;
        for (int i = 0; i < array.length; i += step) {
            int s = 0;
            for (int j = 0; j < step && i + j < array.length; ++j) {
                s += array[i + j];
            }
            if (s > sum) sum = s;
        }
        // 二分查找得到最终解
        int left = 0;
        int right = sum;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (match(array, m, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private static boolean match(int[] array, int m, int s) {
        int left = 0;
        int count = 0;
        while (left < array.length) {
            if (array[left] > s)
                return false;
            int s1 = s;
            while (left < array.length && s1 - array[left] >= 0) {
                s1 -= array[left];
                ++left;
            }
            ++count;
            if (count > m)
                return false;
        }
        return true;
    }


    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int size = in.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = in.nextInt();
        }
        int res = schedule1(m, array);
        System.out.println(String.valueOf(res));
    }
}
