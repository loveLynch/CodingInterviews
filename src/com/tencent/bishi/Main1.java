package com.tencent.bishi;

import java.util.LinkedList;
import java.util.Scanner;
import java.lang.StringBuilder;

/**
 * Created by lynch on 2019-08-17. <br>
 **/

public class Main1 {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        String result = input.nextLine();

        System.out.println(getUnzip(result));
    }

    private static String getUnzip(String string) {
        LinkedList<String> inStack = new LinkedList<>();
        StringBuilder result = new StringBuilder();
        StringBuilder strTmp;
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) != ']') {
                String t = String.valueOf(string.charAt(i));
                inStack.push(t);
                continue;
            }
            result = new StringBuilder();
            strTmp = new StringBuilder();
            int num = 0;
            int m10 = 1;

            String tmp = inStack.pop();
            while (tmp.charAt(0) != '[') {
                if (tmp.charAt(0) == '|') ;
                else if (tmp.charAt(0) >= '0' && tmp.charAt(0) <= '9') {
                    num = num + ((int) (tmp.charAt(0) - '0')) * m10;
                    m10 = m10 * 10;
                } else {
                    strTmp.insert(0, tmp);
                }
                tmp = inStack.pop();
            }
            for (int j = 0; j < num; ++j) result.append(strTmp.toString());
            inStack.push(result.toString());
        }

        result = new StringBuilder();
        while (inStack.size() != 0) {
            result.insert(0, inStack.pop());
        }
        return result.toString();
    }

}