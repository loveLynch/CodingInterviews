package com.tencent;


import java.util.Scanner;

/**
 * Created by lynch on 2019-08-16. <br>
 * 贪吃的小Q
 * 时间限制：1秒
 * <p>
 * 空间限制：32768K
 * <p>
 * 小Q的父母要出差N天，走之前给小Q留下了M块巧克力。小Q决定每天吃的巧克力数量不少于前一天吃的一半，但是他又不想在父母回来之前的某一天没有巧克力吃，请问他第一天最多能吃多少块巧克力
 * <p>
 * 输入描述:
 * 每个输入包含一个测试用例。
 * 每个测试用例的第一行包含两个正整数，表示父母出差的天数N(N<=50000)和巧克力的数量M(N<=M<=100000)。
 * <p>
 * 输出描述:
 * 输出一个数表示小Q第一天最多能吃多少块巧克力。
 * <p>
 * 输入例子1:
 * 3 7
 * <p>
 * 输出例子1:
 * 4
 **/
public class EatChocolate {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        System.out.println(getFirstDayMaxEating(n, m));
    }

    private static int getFirstDayMaxEating(int n, int m) {
        if (n == 1)
            return m;
        int left = 1;
        int right = m;
        while (left <= right) {
            int mid = (left + right + 1) / 2;
            if (sumOfN(n, mid) == m)
                return mid;
            else if (sumOfN(n, mid) < m) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    public static int sumOfN(int n, int eat) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += eat;
            //向上取整，保证第二天大于等于第一天的1/2
            eat = (eat + 1) / 2;
        }
        return sum;

    }
}
