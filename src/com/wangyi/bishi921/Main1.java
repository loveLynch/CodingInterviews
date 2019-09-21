package com.wangyi.bishi921;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-21. <br>
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int num = input.nextInt();
            System.out.println(getMinOfN(num));

        }
    }

    private static String getMinOfN(int num) {
        if (num <= 9)
            return String.valueOf(num);
        StringBuilder res = new StringBuilder();
        int a = num / 9;
        int b = num % 9;
        for (int i = 1; i <= a; i++) {
            res.append(9);
        }
        if (b != 0) {
            res.append(b);
        }
        return res.reverse().toString();
    }

}
