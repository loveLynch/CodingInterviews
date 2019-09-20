package com.webank;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-19. <br>
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println(getLastNMulti(n));

    }

    public static int getLastNMulti(int n) {
        int c2 = 0;
        int c5 = 0;
        int multi = 1;
        for (int i = 1; i <= n; ++i) {
            int j = i;
            while (j % 5 == 0) {
                ++c5;
                j /= 5;
            }
            while (j % 2 == 0) {
                ++c2;
                j /= 2;
            }
            multi = (multi * j) % 10;
        }
        c5 -= c2;
        c2 = -c5;
        for (; c5 > 0; --c5) {
            multi = (multi * 5) % 10;
        }
        for (; c2 > 0; --c2) {
            multi = (multi * 2) % 10;
        }
        return multi;

    }

    public static int getLastNMulti2(int n) {
        int temp = 1;
        for (int i = 1; i <= n; i++) {
            temp *= i;
            while (temp % 10 == 0) {
                temp /= 10;
            }
            temp %= 100;

        }
        return temp % 10;
    }

    public static int getLastNMulti1(int n) {
        long temp = 1;
        while (n > 0) {
            temp = temp * n;
            n--;
        }
        System.out.println(temp);
        int result = 0;
        String num = String.valueOf(temp);
        for (int i = num.length() - 1; i >= 0; i--) {
            if (num.charAt(i) != '0') {
                result = num.charAt(i) - '0';
                break;
            }
        }
        return result;

    }
}
