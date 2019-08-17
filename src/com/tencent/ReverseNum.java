package com.tencent;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-16. <br>
 * 翻转数列
 * 时间限制：1秒
 * <p>
 * 空间限制：32768K
 * <p>
 * 小Q定义了一种数列称为翻转数列:
 * 给定整数n和m, 满足n能被2m整除。对于一串连续递增整数数列1, 2, 3, 4..., 每隔m个符号翻转一次, 最初符号为'-';。
 * 例如n = 8, m = 2, 数列就是: -1, -2, +3, +4, -5, -6, +7, +8.
 * 而n = 4, m = 1, 数列就是: -1, +2, -3, + 4.
 * 小Q现在希望你能帮他算算前n项和为多少。
 * <p>
 * 输入描述:
 * 输入包括两个整数n和m(2 <= n <= 109, 1 <= m), 并且满足n能被2m整除。
 * <p>
 * 输出描述:
 * 输出一个整数, 表示前n项和。
 * <p>
 * 输入例子1:
 * 8 2
 * <p>
 * 输出例子1:
 * 8
 **/
public class ReverseNum {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        System.out.println(getSum(n, m));
    }

    private static long getSum(int n, int m) {
        return m * n / 2;
    }
}
