package com.huawei.bishi8_21;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-21. <br>
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] str = input.nextLine().split(" ");
        int length = Integer.parseInt(str[0]);
        String[] original = new String[length - 1];
        for (int i = 0; i < original.length; i++) {
            original[i] = str[i + 1];
        }
        System.out.println(getEscapeText(original));

    }

    private static String getEscapeText(String[] original) {
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (int i = 0; i < original.length; i++) {
            if (original[i].equals("A")) {
                result.append("12 34");
                result.append(" ");
                count++;
            } else if (original[i].equals("B")) {
                result.append("AB CD");
                result.append(" ");
                count++;
            } else {
                result.append(original[i]);
                result.append(" ");
            }
        }
        count = count + original.length;
        result.reverse();
        result.append(" ");
        result.append(count);
        return result.reverse().toString().trim();
    }
}
