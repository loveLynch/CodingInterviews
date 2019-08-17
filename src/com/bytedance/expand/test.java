package com.bytedance.expand;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-11. <br>
 **/
public class test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        System.out.println(mySqrt(x));
    }

    public static int mySqrt(int x) {
        if (x == 0)
            return 0;
        int sqrt = 1;
        for (int i = 1; i <= x / 2; i++) {
            if (Math.pow(i, 2) <= x) {
                sqrt = Math.max(sqrt, i);
            } else {
                break;
            }
        }
        return sqrt;
    }
}
