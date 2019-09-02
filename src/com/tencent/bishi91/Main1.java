package com.tencent.bishi91;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-01. <br>
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();//box
        int m = input.nextInt();//key
        int[] boxs = new int[n];
        int[] keys = new int[m];
        for (int i = 0; i < n; i++)
            boxs[i] = input.nextInt();
        for (int j = 0; j < m; j++) {
            keys[j] = input.nextInt();
        }
        System.out.println(getOpenNumOfBox(boxs, keys));
    }

    private static int getOpenNumOfBox(int[] boxs, int[] keys) {
        int boxNum = 0;
        int keyNum = 0;
        for (int i = 0; i < boxs.length; i++) {
            if ((boxs[i] & 1) == 1) {
                boxNum++;
            }
        }
        for (int i = 0; i < keys.length; i++) {
            if ((keys[i] & 1) == 1) {
                keyNum++;
            }
        }

        return Math.min(boxNum, keys.length - keyNum) + Math.min(boxs.length - boxNum, keyNum);
    }
}
