package com.niuke.bytedance;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by lynch on 2019-07-02. <br>
 **/
public class SplitNum {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String N = input.nextLine();
        String nums = input.nextLine();
        String[] numArray = nums.split(" ");
        ArrayList<Integer> numList = new ArrayList<>();
//        int[] numList = new int[Integer.parseInt(N)];
        for (int i = 0; i < Integer.valueOf(N); i++)
            numList.add(Integer.valueOf(numArray[i]));

        System.out.println(getClassNum(numList));


    }

    /**
     * 计算班级
     *
     * @param numList
     * @return
     */
    public static int getClassNum(ArrayList<Integer> numList) {
        int classNum = 0;
        if (numList == null || numList.size() == 0)
            return classNum;
        int maxCommonDivisor = getMoreBigDiv(numList, numList.size());
        for (Integer num : numList)
            classNum += num / maxCommonDivisor;

        return classNum;


    }

    /**
     * 两个数的最大公约数
     *
     * @param m
     * @param n
     * @return
     */
    public static int maxCommonDivisor(int m, int n) {
        if (m < n) {
            int temp = m;
            m = n;
            n = temp;
        }
        if (m % n == 0) {
            return n;
        } else {
            return maxCommonDivisor(n, m % n);
        }
    }

    /**
     * 多个数的最大公约数
     *
     * @param numList
     * @param n
     * @return
     */
    public static int getMoreBigDiv(ArrayList<Integer> numList, int n) {
        if (n == 1)
            return numList.get(n - 1);
        return maxCommonDivisor(numList.get(n - 1), getMoreBigDiv(numList, n - 1));
    }
}
