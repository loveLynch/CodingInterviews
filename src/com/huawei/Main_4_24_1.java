package com.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-04-24. <br>
 **/
public class Main_4_24_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> strList = new ArrayList<>();
        String nextLine = scanner.nextLine();
        while (nextLine != null && !nextLine.equals("")) {
            strList.add(nextLine);
            nextLine = scanner.nextLine();

        }
        for (String singStr : strList) {
            System.out.println(isDoubleAABBString(singStr));
        }

    }

    public static String isDoubleAABBString(String str) {
        int length = str.length();
        String leftStr = str.substring(0, length / 2);
        String right = str.substring(length / 2, length);
        StringBuilder rightBuffer = new StringBuilder(right);
        String reverseRight = String.valueOf(rightBuffer.reverse());
        if (length % 2 == 0 && leftStr.equals(reverseRight) && isAABB(str)) {
            return getAB(str);
        }
        return "false";


    }

    public static boolean isAABB(String str) {
        boolean flag = true;
        List<String> booleanList = new ArrayList<>();
        for (int i = 0; i < str.length(); i = i + 2) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                booleanList.add("true");
            } else {
                booleanList.add("false");
            }

        }
        if (booleanList.contains("false"))
            flag = false;
        return flag;
    }

    public static String getAB(String str) {
        String returnStr = "";
        for (int i = 0; i < str.length(); i = i + 2) {
            returnStr = returnStr + str.substring(i, i + 1);

        }
        return returnStr;


    }
}
