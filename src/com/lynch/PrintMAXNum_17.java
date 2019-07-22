package com.lynch;

import java.util.ArrayList;

/**
 * Created by lynch on 2019/3/13. <br>
 * 17.打印从1到最大的n位数
 * 输入数字n，按顺序打印出从1到最大的n为十进制数
 * 如：输入3，打印出1、2、3一直到最大的3位数999
 * 注意n的类型从而导致溢出
 **/
public class PrintMAXNum_17 {
    private static void print1ToMaxOfDigits_ONE(int n) {
        if (n <= 0)
            return;
        StringBuilder number = new StringBuilder(n);
        for (int i = 0; i < n; i++)
            number.append('0');

        while (increment(number)) {
            printNumber(number);
        }
    }

    /**
     * 打印出number
     *
     * @param number
     */
    private static void printNumber(StringBuilder number) {
        boolean flag = false;
        for (int i = 0; i < number.length(); i++) {
            if (flag) {
                System.out.print(number.charAt(i));

            } else {
                if (number.charAt(i) != '0') {
                    flag = true;
                    System.out.print(number.charAt(i));
                }
            }
        }
        System.out.println();
    }

    /**
     * 数字的字符串str（位）上增加1
     * 字符串来存储数字
     * 结合打印即可输出到最大位的值
     *
     * @param str
     * @return
     */
    private static boolean increment(StringBuilder str) {
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) < '9' && str.charAt(i) >= '0') {
                str.setCharAt(i, (char) (str.charAt(i) + 1));
                return true;
            } else if (str.charAt(i) == '9') {
                str.setCharAt(i, '0');
            } else {
                return false;
            }
        }
        return false;

    }

    //剑指Offer
    private static void print1ToMaxOfDigits_TWO(int n) {

        if (n <= 0) {
            return;
        }
        StringBuffer number = new StringBuffer();

        for (int i = 0; i < n; i++) {
            number.append('0');

        }

        while (!Increment(number)) {
            PrintNumber(number);
        }
    }

    private static boolean Increment(StringBuffer s) {
        boolean isOverflow = false;
        int nTakeOver = 0;
        int nLength = s.length();
        for (int i = nLength - 1; i >= 0; i--) {
            int nSum = s.charAt(i) - '0' + nTakeOver;
            if (i == nLength - 1) {
                nSum++;
            }
            if (nSum >= 10) {
                if (i == 0) {
                    isOverflow = true;

                } else {
                    nSum -= 10;
                    nTakeOver = 1;
                    s.setCharAt(i, (char) ('0' + nSum));
                }

            } else {
                s.setCharAt(i, (char) ('0' + nSum));
                break;
            }
        }
        return isOverflow;
    }

    private static void PrintNumber(StringBuffer s) {
        boolean isBeginning0 = true;
        for (int i = 0; i < s.length(); i++) {
            if (isBeginning0 && s.charAt(i) != '0') {
                isBeginning0 = false;
            }
            if (!isBeginning0) {
                System.out.print(s.charAt(i));
            }
        }

        System.out.println();
    }

    private static void print1ToMaxOfDigits_THREE(int n) {
        ArrayList<int[]> list = new ArrayList<>(); // 实质是一个二维数组
        int answer[] = new int[n]; // answer[i] 表示 二维数组中 第 i 行所选的 列 值。 假如
        // answer[1]=6,
        // 那么就表示取二维数组 list[1][6]

        int num[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < n; i++) {
            list.add(num); // 生成二维数组
        }
        recursive(0, n, answer, list);
    }

    /**
     * 递归
     *
     * @param index
     * @param n
     * @param answer
     * @param list
     */
    private static void recursive(int index, int n, int[] answer, ArrayList<int[]> list) {
        if (index == n) { // 递归结束条件
            boolean beginWith0 = true; // 是否是0 开头
            for (int i = 0; i < n; i++) {
                if (list.get(i)[answer[i]] != 0) {
                    beginWith0 = false;
                }
                if (!beginWith0) {
                    System.out.printf("%d", list.get(i)[answer[i]]); // list.get(i)[answer[i]] 表示 list[i][j]
                }
            }
            if (!beginWith0) {
                System.out.println();

            }
            return;
        }
        for (answer[index] = 0; answer[index] < 10; answer[index]++) {
            recursive(index + 1, n, answer, list); // 递归
        }

    }

    public static void main(String[] args) {
        print1ToMaxOfDigits_ONE(2);
        print1ToMaxOfDigits_TWO(2);
        print1ToMaxOfDigits_THREE(2);
    }
}
