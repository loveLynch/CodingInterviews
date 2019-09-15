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
 * 0 0 2 4
 * 0 2 2 2
 * 0 4 2 2
 * 8 8 2 2
 * output
 * 2 4 0 0
 * 4 2 0 0
 * 4 4 0 0
 * 16 4 0 0
 **/


public class Main2 {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static String solution(String[] input) {
        int n = input.length;
        int m = input[0].split(" ").length;
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(input[i].split(" ")[j]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                if (arr[i][j] == 0) {
                    int k = j + 1;
                    for (; k < m; k++) {
                        if (arr[i][k] != 0) {
                            int temp = arr[i][j];
                            arr[i][j] = arr[i][k];
                            arr[i][k] = temp;
                            break;
                        }

                    }
                    if (k >= m - 1)
                        break;

                }

            }
            for (int j = 0; j < m - 1; j++) {
                if (arr[i][j] == arr[i][j + 1]) {
                    arr[i][j] *= 2;
                    arr[i][j + 1] = 0;

                }
            }
            for (int j = 0; j < m - 1; j++) {
                if (arr[i][j] == 0) {
                    int k = j + 1;
                    for (; k < m; k++) {
                        if (arr[i][k] != 0) {
                            int temp = arr[i][j];
                            arr[i][j] = arr[i][k];
                            arr[i][k] = temp;
                            break;
                        }
                    }
                    if (k >= m - 1)
                        break;
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result.append(arr[i][j]);
                result.append(" ");
            }
            result.append("\n");
        }
        return result.toString();

    }

    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String res;

        int _input_size = 0;
        _input_size = Integer.parseInt(in.nextLine().trim());
        String[] _input = new String[_input_size];
        String _input_item;
        for (int _input_i = 0; _input_i < _input_size; _input_i++) {
            try {
                _input_item = in.nextLine();
            } catch (Exception e) {
                _input_item = null;
            }
            _input[_input_i] = _input_item;
        }

        res = solution(_input);
        System.out.println(res);
    }
}
