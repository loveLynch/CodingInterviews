package com.lynch;

/**
 * Created by lynch on 2019-04-02. <br>
 * 60.n个骰子的点数
 * <p>
 * 把n个骰子仍在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值的出现概率。
 * 思路：
 * 新加入一个骰子，它出现1-6的概率是相等的，可以看成各出现一次，
 * 那么出现和为s的次数等于再加入之前出现和为s-1,s-2,s-3,s-4,s-5,s-6这6种情况的次数之和。
 * 如此循环，直到加入n个骰子结束。
 **/
public class DicesProbability_60 {
    /**
     * @param number 骰子的个数
     */
    public static void printProbability(int number) {
        if (number <= 0)
            return;
        int result[][] = new int[2][6 * number + 1];
        for (int i = 1; i <= 6; i++)
            result[1][i] = 1;
        for (int num = 2; num <= number; num++) {
            for (int i = num; i < 6 * num + 1; i++) {
                for (int j = i - 6; j < i; j++)
                    if (j > 0)
                        result[num % 2][i] += result[(num - 1) % 2][j];
            }
        }
        System.out.println("number = " + number);
        double sum = 0;
        for (int i = number; i < 6 * number + 1; i++)
            sum += result[number % 2][i];
        for (int i = number; i < 6 * number + 1; i++)
            System.out.println("probability->" + i + ": " + result[number % 2][i] / sum);
    }

    public static void main(String[] args) {
        printProbability(0);
        printProbability(2);
        printProbability(11);
    }
}
