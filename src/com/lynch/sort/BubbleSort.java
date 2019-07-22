package com.lynch.sort;

/**
 * Created by lynch on 2019-03-27. <br>
 * 冒泡排序
 **/
public class BubbleSort {
    //数组冒泡，时间o(n^2)，空间o(1)，稳定
    public static void bubbleSort(int[] data) {
        if (data == null || data.length <= 1)
            return;
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 1; j < data.length - i; j++) {
                if (data[j - 1] > data[j]) {
                    int temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

    public static void testBubbleSort() {
        int[] data = {5, 4, 3, 1, 2};
        bubbleSort(data);
        System.out.print("数组冒泡排序：\t");
        for (int item : data) {
            System.out.print(item);
            System.out.print('\t');
        }
        System.out.println();
    }
}
