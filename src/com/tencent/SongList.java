package com.tencent;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-16. <br>
 * 小Q的歌单
 * 时间限制：1秒
 * <p>
 * 空间限制：32768K
 * <p>
 * 小Q有X首长度为A的不同的歌和Y首长度为B的不同的歌，现在小Q想用这些歌组成一个总长度正好为K的歌单，每首歌最多只能在歌单中出现一次，在不考虑歌单内歌曲的先后顺序的情况下，请问有多少种组成歌单的方法。
 * <p>
 * 输入描述:
 * 每个输入包含一个测试用例。
 * 每个测试用例的第一行包含一个整数，表示歌单的总长度K(1<=K<=1000)。
 * 接下来的一行包含四个正整数，分别表示歌的第一种长度A(A<=10)和数量X(X<=100)以及歌的第二种长度B(B<=10)和数量Y(Y<=100)。保证A不等于B。
 * <p>
 * 输出描述:
 * 输出一个整数,表示组成歌单的方法取模。因为答案可能会很大,输出对1000000007取模的结果。
 * <p>
 * 输入例子1:
 * 5
 * 2 3 3 3
 * <p>
 * 输出例子1:
 * 9
 **/
public class SongList {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int k = input.nextInt();
        int a = input.nextInt();
        int x = input.nextInt();
        int b = input.nextInt();
        int y = input.nextInt();
        System.out.println(getMethodOfSong(k, a, x, b, y));
    }

    /**
     * 背包问题
     *
     * @param k
     * @param a
     * @param x
     * @param b
     * @param y
     * @return
     */
    private static int getMethodOfSong(int k, int a, int x, int b, int y) {
        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int i = 0; i < x; i++) {
            for (int j = k; j >= a; j--) {
                dp[j] = (dp[j] + dp[j - a]) % 1000000007;
            }
        }

        for (int i = 0; i < y; i++) {
            for (int j = k; j >= b; j--) {
                dp[j] = (dp[j] + dp[j - b]) % 1000000007;
            }
        }
        return dp[k];
    }
}
