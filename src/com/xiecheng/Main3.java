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

    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int size = in.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = in.nextInt();
        }
        int res = schedule(m, array);
        System.out.println(String.valueOf(res));
    }
}
