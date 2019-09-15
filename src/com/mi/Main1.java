package com.mi;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * Created by lynch on 2019-09-11. <br>
 * input
 * 4
 * 4
 * 2
 * 7
 * 6
 * output
 * 2
 **/


public class Main1 {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int solution(int[] arr) {
        if (arr.length == 0)
            return 0;
        int[] arrClone = arr.clone();
        int count1 = 0;
        int current1;
        for (int i = 0; i < arr.length - 1; i++) {
            current1 = arr[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current1 < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
                count1++;
            }
            arr[preIndex + 1] = current1;
        }
        int count2 = 0;
        int current2;
        for (int i = 0; i < arrClone.length - 1; i++) {
            current2 = arrClone[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current2 > arrClone[preIndex]) {
                arrClone[preIndex + 1] = arrClone[preIndex];
                preIndex--;
                count2++;
            }
            arr[preIndex + 1] = current2;
        }

        return count1 > count2 ? count2 : count1;

    }

    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int res;

        int _arr_size = 0;
        _arr_size = Integer.parseInt(in.nextLine().trim());
        int[] _arr = new int[_arr_size];
        int _arr_item;
        for (int _arr_i = 0; _arr_i < _arr_size; _arr_i++) {
            _arr_item = Integer.parseInt(in.nextLine().trim());
            _arr[_arr_i] = _arr_item;
        }

        res = solution(_arr);
        System.out.println(String.valueOf(res));

    }
}
