package com.bytedance.arrayandsort;

import java.util.*;

/**
 * Created by lynch on 2019-08-06. <br>
 * 第k个排列
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * 说明：
 * <p>
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * <p>
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 * <p>
 * 输入: n = 4, k = 9
 * 输出: "2314"
 **/
public class TheKOfPermutation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        System.out.println(getPermutation(n, k));

    }

    public static String getPermutation(int n, int k) {
        if (n <= 1) {
            return "" + n;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i <= n; i++) {
            arrayList.add(i);
        }

        StringBuilder result = new StringBuilder();
        int leaf = n;
        k = k - 1;
        while (leaf > 0) {
            // 循环求出(n-1)阶乘
            int val = 1;
            for (int i = 1; i <= leaf - 1; i++) {
                val = val * i;
            }
            // 求出下标索引index
            int index = k / val;
            result.append(arrayList.get(index));
            arrayList.remove(index);
            k = k % val;
            leaf--;
        }
        return result.toString();
    }


    public static String getPermutation1(int n, int k) {
        StringBuilder result = new StringBuilder();
        if (n == 1) {
            return "1";
        } else if (n == 2) {
            if (k == 1) {
                return "12";
            } else {
                return "21";
            }
        } else {
            int firstNum = k / getCount(n - 1);
            getPermutationCore(n, k, firstNum, result);
        }
        int nextNum = result.toString().charAt(result.toString().length() - 1) - '0';
        result.deleteCharAt(result.toString().length() - 1);
        Set<Integer> all = new HashSet<>();
        for (int i = 1; i <= n; i++)
            all.add(i);
        char[] resultChar = result.toString().toCharArray();
        for (int i = 0; i < resultChar.length; i++) {
            all.remove(resultChar[i] - '0');
        }
        List<Integer> twoList = new ArrayList<>();
        for (int two : all)
            twoList.add(two);
        Collections.sort(twoList);
        if (nextNum == 1) {
            result.append(twoList.get(0));
            result.append(twoList.get(1));
        } else {
            result.append(twoList.get(1));
            result.append(twoList.get(0));
        }


        return result.toString();

    }

    public static void getPermutationCore(int n, int k, int firstNum, StringBuilder result) {
        if (n > 2) {
            int count = getCount(n - 1);
            int nowFirst = k / count;
            int nowNext = k % count;
            if (nowFirst >= firstNum)
                nowFirst++;
            result.append(nowFirst);
            if (n == 3)
                result.append(nowNext);
            getPermutationCore(n - 1, nowNext, nowFirst, result);
        }


    }

    public static int getCount(int n) {
        int count = 1;
        while (n > 1) {
            count = count * n;
            n--;
        }
        return count;
    }
}