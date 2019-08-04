package com.wangyi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Lynch
 * @date 2019/8/2 17:48
 * 小易的字典
 * 时间限制：1秒
 * <p>
 * 空间限制：262144K
 * <p>
 * 小易在学校中学习了关于字符串的理论, 于是他基于此完成了一个字典的项目。
 * <p>
 * 小易的这个字典很奇特, 字典内的每个单词都包含n个'a'和m个'z', 并且所有单词按照字典序排列。
 * <p>
 * 小易现在希望你能帮他找出第k个单词是什么。
 * <p>
 * <p>
 * 输入描述:
 * 输入包括一行三个整数n, m, k(1 <= n, m <= 100, 1 <= k <= 109), 以空格分割。
 * <p>
 * <p>
 * 输出描述:
 * 输出第k个字典中的字符串，如果无解，输出-1。
 * <p>
 * 输入例子1:
 * 2 2 6
 * <p>
 * 输出例子1:
 * zzaa
 */
public class XiaoYiDict {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] NMK = input.nextLine().split(" ");
        int N = Integer.parseInt(NMK[0]);
        int M = Integer.parseInt(NMK[1]);
        int K = Integer.parseInt(NMK[2]);
        System.out.println(getTheKDict(N, M, K));
    }

    /**
     * 排列组合
     * n个'a'和m个'z'只能组成C(m+n,m)个单词
     * <p>
     * 1。若第一个字符为a，则剩下n-1个'a'和m个'z'组成的子序列只能构成count(n-1+m,n-1)个单词，
     * 且是字典中前count(n-1+m,n-1)个单词。
     * 2。比较k和count(n-1+m,n-1)，若k小，说明k是前count(n-1+m,n-1)个单词，
     * 则第一个字符必为'a'。子问题化为在子序列(n-1个'a'和m个'z')找到第k个单词
     * 3。若k大，则说明第一个字符必为'z',单词是以'z'开头的单词中的第k-count(n-1+m,n-1)个。
     * 子问题化为在子序列(n个'a'和m-1个'z')找到第k-count(n+m-1,m-1)个单词。
     *
     * @param n
     * @param m
     * @param k
     * @return
     */
    private static String getTheKDict(int n, int m, int k) {
        StringBuilder strBuilder = new StringBuilder();

        while (n > 0 && m > 0) {
            //每次迭代问题规模缩减一个单位
            ////排列组合:假设当前序列首字符为a，剩下n-1个a放在剩下n - 1 +m 个位置共有的可能数
            long count = 1;
            for (int i = 0; i < n - 1; i++) {//求组合数
                count *= n - 1 + m - i;
                count /= (i + 1);
                if (count > k) break;//防止越界。count>k就可以退出计算了
            }
            if (k <= count) {//如果k小于等于count，则表明首字符的确应为a
                strBuilder.append('a');
                n--;//问题缩减为 n-1个a和m个z 中找第k大
            } else {
                strBuilder.append('z');
                m--;//问题缩减为 n-1个a和m个z 中找第k-count大
                k -= count;
            }
        }

        //循环结束后，剩余子序列只存在"aa..aaa" 或 "zz..zzz"1种情况
        if (k != 1) {//
            return "-1";
        }
        while (n > 0) {
            strBuilder.append('a');
            n--;
        }
        while (m > 0) {
            strBuilder.append('z');
            m--;
        }


        return strBuilder.toString();
    }
}
