package com.lynch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by lynch on 2019-03-28. <br>
 * 45.把数组排列成最小的数
 * <p>
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
 * 使其为所有可能的拼接结果中最小的一个。例如输入{3,32,321}，则输入321323。
 * 思路：
 * 此题需要对数组进行排序，关键点在于排序的规则需要重新定义。
 * 我们重新定义“大于”，“小于”，“等于”。如果a，b组成的数字ab的值大于ba，
 * 则称a"大于"b，小于与等于类似。比如3与32，因为332大于323，因此我们称3“大于”32。
 * 我们按照上述的“大于”，“小于”规则进行升序排列，即可得到题目要求的答案
 **/
public class SortArrayAsMinNumber_45 {
    /**
     * 按定义的大小"规则"交互数组顺序
     * 时间复杂度过大
     *
     * @param data
     */
    private static void printMinNumber(int[] data) {
        if (data == null || data.length == 0)
            return;
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (bigger(data[j], data[j + 1])) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
        for (int item : data) {
            System.out.print(item);
        }
    }

    /**
     * 定义比较规则
     *
     * @param a
     * @param b
     * @return
     */
    //if a>=b return true
    private static boolean bigger(int a, int b) {
        String temp1 = a + "" + b;
        String temp2 = b + "" + a;
        if (temp1.compareTo(temp2) > 0)
            return true;
        else
            return false;
    }


    /**
     * Collections
     * @param numbers
     * @return
     */
    private static String PrintMinNumber(int[] numbers) {
        String s = "";
        ArrayList<Integer> list = new ArrayList<Integer>();
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            list.add(numbers[i]);

        }
        Collections.sort(list, new Comparator<Integer>() {

            public int compare(Integer str1, Integer str2) {
                String s1 = str1 + "" + str2;
                String s2 = str2 + "" + str1;
                return s1.compareTo(s2);
            }
        });

        for (int j : list) {
            s += j;
        }
        return s;

    }

    public static void main(String[] args) {
        int data[] = {3, 32, 321};
        printMinNumber(data);
        System.out.println();
        System.out.println(PrintMinNumber(data));
    }

}
