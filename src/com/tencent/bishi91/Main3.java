package com.tencent.bishi91;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-01. <br>
 * 4 100
 * 3 4 5 4
 * <p>
 * <p>
 * 5
 **/
public class Main3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();//office
        int m = input.nextInt();//people
        int[] boxNum = new int[n];
        for (int i = 0; i < n; i++) {
            boxNum[i] = input.nextInt();

        }
        System.out.println(getMinTime(m, boxNum));
    }

    private static int getMinTime(int m, int[] boxNum) {
        return 0;
    }
}
