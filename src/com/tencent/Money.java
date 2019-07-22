package com.tencent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-04-05. <br>
 **/
public class Money {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        in.nextLine();
        int[] value = new int[n];
        for (int i = 0; i < n; i++) {
            int input = in.nextInt();
            in.nextLine();
            value[i] = input;
        }
        System.out.println(getMoneyCount(m, value));
    }

    /**
     * 利用快速排序对单个
     * 所有面值排序
     *
     * @param data
     */
    public static void quickSort(int[] data) {
        if (data == null || data.length <= 1) return;
        quickSortCore(data, 0, data.length - 1);
    }

    public static void quickSortCore(int[] data, int start, int end) {
        if (end - start <= 0)
            return;
        int index = quickSortPartition(data, start, end);
        quickSortCore(data, start, index - 1);
        quickSortCore(data, index + 1, end);
    }

    public static int quickSortPartition(int[] data, int start, int end) {
        int pivot = data[start];
        int left = start, right = end;
        while (left < right) {
            while (left < right && data[right] >= pivot)
                right--;
            if (left < right)
                data[left] = data[right];
            while (left < right && data[left] < pivot)
                left++;
            if (left < right)
                data[right] = data[left];
        }
        data[left] = pivot;
        return left;
    }

    /**
     * 主要考虑个位
     * 如果存在1的面值肯定有解，只是数量问题
     *
     * @param money
     * @param value
     * @return
     */
    private static int getMoneyCount(int money, int[] value) {
        if (money <= 0 || value == null || value.length == 0)
            return -1;
        quickSort(value);//快速排序
        //没有1的面值一定不能满足
        if (value[value.length - 1] > money || value[0] != 1)
            return -1;
        int needData[] = new int[money];
        List<Integer> storeUnder10 = new ArrayList<>();
        for (int i = 0; i < money; i++) {
            needData[i] = (i + 1);
        }
        for (int j = 0; j < value.length; j++) {
            if (value[j] / 10 < 1) {
                storeUnder10.add(value[j]);
            }
        }
        int[] under10 = new int[storeUnder10.size()];
        for (int x = 0; x < storeUnder10.size(); x++) {
            under10[x] = storeUnder10.get(x);
        }
        //先利用小于10的面值组合出1～10，否则return -1，并计算个数
        int count = (money / 10);
        int minCount = 0;
        if (underTen(under10) != -1) {
            if (count > 1) {
                count -= 1;
                minCount = count + underTen(under10) + (count - 1) * underTen(under10);
            } else {
                minCount = count + underTen(under10);
            }
        }
        return minCount;
    }

    /**
     * 组合出10以下的数需要的硬币数量
     *
     * @param data
     * @return
     */
    public static int underTen(int data[]) {
        if (data.length == 0 || data == null)
            return -1;
        if (data.length == 1)
            return 9;
        else {
            int count2 = 10 / data[1];
            //长度大于1但没有2面值不可行
            if (count2 > 5)
                return -1;
            else {
                if (data.length == 2)
                    return 5;
                int count3 = 10 / data[2];
                if (count3 > 3)
                    return 5;
                else
                    return 4;
            }
        }

    }
}
