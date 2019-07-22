package com.lynch.sort;

/**
 * Created by lynch on 2019-03-27. <br>
 * 希尔排序
 **/
public class ShellSort {
    //数组希尔排序(插入排序缩小增量)，时间o(n^1.3)，空间o(1)，不稳定
    //时间复杂度是模糊的，有人在大量的实验后得出结论：当n在某个特定的范围后希尔排序的比较和移动次数减少至n^1.3。次数取值在1到2之间。
    public static void shellSort(int[] data) {
        if (data == null || data.length <= 1)
            return;
        for (int d = data.length / 2; d > 0; d = d / 2) {
            for (int i = d; i < data.length; i++) {
                int cur = i;
                int temp = data[i];
                while (cur >= d && data[cur - d] > temp) {
                    data[cur] = data[cur - d];
                    cur = cur - d;
                }
                data[cur] = temp;
            }
        }
    }

    public static void testShellSort() {
        int[] data = {5, 4, 3, 1, 2};
        shellSort(data);
        System.out.print("数组希尔排序：\t");
        for (int item : data) {
            System.out.print(item);
            System.out.print('\t');
        }
        System.out.println();
    }
}
