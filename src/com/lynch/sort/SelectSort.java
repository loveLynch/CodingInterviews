package com.lynch.sort;

/**
 * Created by lynch on 2019-03-27. <br>
 * 选择排序
 **/
public class SelectSort {
    //数组选择排序，时间o(n^2)，空间o(1)，不稳定
    public static void selectionSort(int[] data) {
        if (data == null || data.length <= 1)
            return;
        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[minIndex])
                    minIndex = j;
            }
            if (i != minIndex) {
                int temp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = temp;
            }
        }
    }

    public static void testSelectionSort() {
        int[] data = {5, 4, 3, 1, 2};
        selectionSort(data);
        System.out.print("数组选择排序：\t");
        for (int item : data) {
            System.out.print(item);
            System.out.print('\t');
        }
        System.out.println();
    }
}
