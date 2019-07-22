package com.tencent;

import java.util.Scanner;

/**
 * Created by lynch on 2019-04-05. <br>
 **/
public class StringMiss {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        in.nextLine();
        String data = in.next();
        System.out.println(getCount(data, count));
    }

    private static int getCount(String data, int count) {
        int n1 = 0;
        int n2 = 0;
        if (data == null)
            return 0;

        for (int i = 0; i < count; i++) {
            char c = data.charAt(i);
            if (c == '1') n1++;
            if (c == '0') n2++;
        }
        return Math.abs(n1 - n2);

    }
}
