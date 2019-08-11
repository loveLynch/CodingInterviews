package com.bytedance.bishi;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-11. <br>
 * 注意递减
 **/
public class Main3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int year[] = new int[N];
        for (int i = 0; i < N; i++) {
            year[i] = input.nextInt();
        }
        System.out.println(getMinMoney1(year));
    }


    private static int getMinMoney(int[] year) {
        int reward = 0;
        int[] money = new int[year.length];
        for (int i = 0; i < money.length; i++) {
            money[i] = 100;
        }
        for (int i = 1; i < year.length; i++) {
            if (year[i] > year[i - 1])
                money[i] = money[i - 1] + 100;
            reward += money[i];
        }
        return reward + 100;
    }

    private static int getMinMoney2(int[] year) {
        int reward = 0;
        int[] money = new int[year.length];
        for (int i = 0; i < money.length; i++) {
            money[i] = 100;
        }
        for (int i = 0; i < year.length; i++) {
            if (i > 0 && year[i] > year[i - 1])
                money[i] = Math.max(money[i], money[i - 1] + 100);
            if (i < year.length - 1 && year[i] > year[i + 1])
                money[i] = Math.max(money[i], money[i + 1] + 100);
        }
        for (int mon : money)
            reward += mon;
        return reward;
    }

    private static int getMinMoney1(int[] year) {
        int reward = 0;
        int[] money = new int[year.length];
        for (int i = 0; i < year.length; i++) {
            if (i > 0 && year[i] > year[i - 1])
                continue;
            if (i < year.length - 1 && year[i] > year[i + 1])
                continue;
            money[i] = 100;
            int j = i;
            while (j > 0 && year[j] < year[j - 1]) {
                money[j - 1] = Math.max(money[j - 1], money[i] + 100);
                j--;
            }
            j = i;
            while (j < year.length - 1 && year[j] < year[j + 1]) {
                money[j + 1] = Math.max(money[j + 1], money[j] + 100);
                j++;
            }
        }
        for (int mon : money)
            reward += mon;
        return reward;

    }
}
