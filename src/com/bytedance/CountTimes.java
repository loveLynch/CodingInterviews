package com.bytedance;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-07-12 21:34:36 <br>
 * 有问题待修改
 *
 **/
public class CountTimes {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> numList = new ArrayList<>();
        String nextLine = input.nextLine();

        while (nextLine != null && !nextLine.equals("")) {
            numList.add(nextLine);
            nextLine = input.nextLine();
        }

        for (String num : numList) {
            int count = getCheckTimes(num);
            System.out.println(count);
        }


    }

    public static int getCheckTimes(String str) {
        int count = 0;
        if (str == null)
            return count;
        char[] strArray = str.toCharArray();
        int[] numArray = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            numArray[i] = strArray[i] - '0';

        }

        if (numArray.length == 1) {
            return singleNum(numArray[0] - 1, 4, 5);

        }
        //将大于1的长度的奇数键拆成多个偶数键和1个奇数键
        if (numArray.length % 2 == 0) {
            count = evenArrayNum(numArray, numArray.length);

        } else {
            count = evenArrayNum(numArray, numArray.length - 1) + singleNum(numArray[numArray.length - 1] - 1, numArray[numArray.length - 2] - 1, numArray[numArray.length - 3] - 1);

        }


        return count;

    }

    /**
     * 单个输入键，注意左右下标的大小
     *
     * @param numIndex
     * @param leftIndex
     * @param rightIndex
     * @return
     */
    public static int singleNum(int numIndex, int leftIndex, int rightIndex) {
        //交换，左下标一定小于右下标
        if (leftIndex > rightIndex) {
            int temp = leftIndex;
            leftIndex = rightIndex;
            rightIndex = temp;
        }

        int count = 0;
        if (numIndex == leftIndex || numIndex == rightIndex)
            count = 1;
        if (numIndex < leftIndex)
            count = leftIndex - numIndex;
        if (numIndex > rightIndex)
            count = numIndex - rightIndex;

        return count;

    }

    /**
     * 偶数个输入键
     *
     * @param numArray
     * @param length
     * @return
     */
    public static int evenArrayNum(int[] numArray, int length) {
        int count = 0;
        int leftIndex = 4;
        int rightIndex = 5;
        for (int i = 0; i < length; i = i + 2) {
            //转换为下标
            int a = numArray[i] - 1;
            int b = numArray[(i + 1)] - 1;
            if (a == 0) a = 10;
            if (b == 0) b = 10;
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            while (a < leftIndex && b < rightIndex) {
                leftIndex--;
                rightIndex--;
                count++;
            }
            while (a > leftIndex && b > rightIndex) {
                leftIndex++;
                rightIndex++;
                count++;
            }
            while (a == leftIndex && b > rightIndex) {
                rightIndex++;
            }
            while (a < leftIndex && b == rightIndex) {
                leftIndex--;
            }
            while (a < leftIndex && b > rightIndex) {
                leftIndex--;
                rightIndex++;
                count++;
                if (a == leftIndex && b > rightIndex) {
                    while (a == leftIndex && b > rightIndex) {
                        rightIndex++;
                    }
                }
                if (a < leftIndex && b == rightIndex) {
                    while (a < leftIndex && b == rightIndex) {
                        leftIndex--;
                    }
                }

            }
            if (a == leftIndex || a == rightIndex)
                count++;
            if (b == rightIndex || b == leftIndex)
                count++;
        }
        return count;

    }
}
