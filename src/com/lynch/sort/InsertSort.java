package com.lynch.sort;

/**
 * Created by lynch on 2019-03-27. <br>
 * 插入排序
 **/
public class InsertSort {
    //数组插入排序，时间o(n^2)，空间o(1)，稳定
    public static void insertionSort(int[] data) {
        if (data == null || data.length <= 1)
            return;
        for (int i = 1; i < data.length; i++) {
            int j = i;
            int temp = data[i];
            while (j > 0 && data[j - 1] > temp) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = temp;
        }
    }

    public static void testInsertionSort() {
        int[] data = {5, 4, 3, 1, 2};
        insertionSort(data);
        System.out.print("数组插入排序：\t");
        for (int item : data) {
            System.out.print(item);
            System.out.print('\t');
        }
        System.out.println();
    }
}
