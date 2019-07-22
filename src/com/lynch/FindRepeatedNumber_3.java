package com.lynch;

import java.util.Scanner;

/**
 * Created by lynch on 2019/2/25. <br>
 * 3.找出数组中一个任意重复的数字
 * 长度为n的数组
 * 数组中的数字都在0～n-1的范围内
 **/
public class FindRepeatedNumber_3 {
    public static int duplication;

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        System.out.print("Input numbers length:");
//        int M = in.nextInt();
//        int[] numbers = new int[M];
//        System.out.println("Input the elements in numbers:");
//        for (int i = 0; i < numbers.length; i++) {
//            int input = in.nextInt();
//            numbers[i] = input;
//        }
        int[] numbers = {2, 3, 0, 2, 5, 3};
        if (duplicate3(numbers))
            System.out.println("the repeated number is: " + duplication);
        else
            System.out.println("no duplication");


    }

    /**
     * 法一：根据数字特点排序，会修改原始数据，时间复杂度o(n),空间复杂度o(1)
     *
     * @param numbers
     * @return
     */
    public static boolean duplicate(int numbers[]) {
        if (numbers == null || numbers.length < 2) {
            return false;
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 0 || numbers[i] > numbers.length - 1) {
                return false;
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            while (numbers[i] != i) {
                if (numbers[i] == numbers[numbers[i]]) {
                    duplication = numbers[i];
                    return true;
                }
                //交换numbers[i]与numbers[numbers[i]]
                int temp = numbers[i];
                numbers[i] = numbers[temp];
                numbers[temp] = temp;
            }
        }
        return false;

    }

    /**
     * 法二：暴力求解，不会修改原始数据，时间复杂度o(n^2)，空间复杂度o(1)
     *
     * @param numbers
     * @return
     */
    public static boolean duplicate2(int[] numbers) {
        if (numbers == null || numbers.length < 2)
            return false;
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] == numbers[j]) {
                    duplication = numbers[i];
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 法三：借助哈希表，不会修改原始数据，时间复杂度o(n),空间复杂度o(n)
     *
     * @param numbers
     * @return
     */
    public static boolean duplicate3(int[] numbers) {
        if (numbers == null || numbers.length < 2)
            return false;
        int[] hashTable = new int[numbers.length];
        for (int number : numbers) {
            if (hashTable[number] >= 1) {
                duplication = number;
                return true;
            } else {
                hashTable[number] = 1;
            }
        }
        return false;
    }

}
