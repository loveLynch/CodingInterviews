package com.wangyi;

import java.util.Scanner;

/**
 * @author Lynch
 * @date 2019/8/2 14:13
 * <p>
 * [编程题]瞌睡
 * 时间限制：1秒
 * <p>
 * 空间限制：262144K
 * <p>
 * 小易觉得高数课太无聊了，决定睡觉。不过他对课上的一些内容挺感兴趣，所以希望你在老师讲到有趣的部分的时候叫醒他一下。你知道了小易对一堂课每分钟知识点的感兴趣程度，并以分数量化，以及他在这堂课上每分钟是否会睡着，你可以叫醒他一次，这会使得他在接下来的k分钟内保持清醒。你需要选择一种方案最大化小易这堂课听到的知识点分值。
 * <p>
 * 输入描述:
 * 第一行 n, k (1 <= n, k <= 105) ，表示这堂课持续多少分钟，以及叫醒小易一次使他能够保持清醒的时间。
 * 第二行 n 个数，a1, a2, ... , an(1 <= ai <= 104) 表示小易对每分钟知识点的感兴趣评分。
 * 第三行 n 个数，t1, t2, ... , tn 表示每分钟小易是否清醒, 1表示清醒。
 * <p>
 * 输出描述:
 * 小易这堂课听到的知识点的最大兴趣值。
 */
public class Doze {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String NK = input.nextLine();
        int N = Integer.parseInt(NK.split(" ")[0]);
        int K = Integer.parseInt(NK.split(" ")[1]);
        int[] interestings = new int[N];
        int[] isWake = new int[N];
        for (int i = 0; i < N; i++) {
            interestings[i] = input.nextInt();
        }
        for (int i = 0; i < N; i++) {
            isWake[i] = input.nextInt();
        }
        System.out.println(getMaxInteresting(N, K, interestings, isWake));
    }

    private static int getMaxInteresting(int n, int k, int[] interestings, int[] isWake) {
        int maxInteresting = 0;
        int canWake = 0;
        if (k >= n) {
            for (int interesting : interestings)
                maxInteresting += interesting;
            return maxInteresting;
        }
        for (int i = 0; i < isWake.length; i++) {
            if (isWake[i] == 1) {
                maxInteresting += interestings[i];
            } else {
                int tempWake = 0;
                for (int j = i; j < i + k && j < isWake.length; j++) {
                    tempWake += Math.abs(isWake[j] - 1) * interestings[j];
                    if (tempWake > canWake)
                        canWake = tempWake;
                }
            }
        }
        maxInteresting += canWake;

        return maxInteresting;
    }
}
