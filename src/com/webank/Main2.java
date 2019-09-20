package com.webank;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-19. <br>
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        System.out.println(getAskTimes(x));
    }

    private static long getAskTimes(int x) {
        int MOD = 1000003;
        long result = 1;
        while (x > 0) {
            result = result * x;
            result %= MOD;
            x--;
        }
        return result % MOD;
    }
}
