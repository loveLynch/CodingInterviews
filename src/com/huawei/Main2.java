package com.huawei;

import java.util.Scanner;

/**
 * Created by lynch on 2019-04-10. <br>
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.next();
        char[] inputChar = input.toCharArray();
        StringBuilder outStr = new StringBuilder();
        for (int i = inputChar.length - 1; i >= 0; i--) {
            outStr.append(inputChar[i]);
        }
        StringBuilder getStr = new StringBuilder();
        char[] invserChar = outStr.toString().toCharArray();
        for (int i = 0; i < invserChar.length; i++) {
            if (invserChar[i] == ')') {
                for (int j = 0; j < Integer.parseInt(String.valueOf(invserChar[i + 3])) - 1; j++)
                    getStr.append(invserChar[i + 1]);
            } else {
                getStr.append(invserChar[i]);
            }
        }
        String finalStr = getStr.toString();
        //去除括号和数字
        finalStr = finalStr.replaceAll("\\(|\\)|\\d+", "");
        System.out.println(finalStr);

    }
}
