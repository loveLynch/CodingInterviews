package com.cetv;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-05. <br>
 **/
public class CountNum {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long n = input.nextInt();
        System.out.println(getThisNum(n));
    }

    private static long getThisNum(long n) {
        long temp = n / 5 + 1;
        int count = 0;
        while (temp > 0) {
            temp = temp >> 1;
            count++;
        }
        if (n == 5 * (Math.pow(2, count - 1) - 1))
            count--;
        long cha = (long) (n - 5 * (Math.pow(2, count - 1) - 1));
        return cha;

    }
}
