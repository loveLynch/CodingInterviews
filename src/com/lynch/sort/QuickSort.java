package com.lynch.sort;

/**
 * Created by lynch on 2019-03-27. <br>
 * 快排
 **/
public class QuickSort {
    //数组快排，时间o(nlogn)(最差n^2)，空间o(logn)(最差n)，递归造成的栈空间的使用，不稳定
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
        //选择第一个值作为基准
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

    public static void testQuickSort() {
        int[] data = {5, 4, 3, 1, 2};
        quickSort(data);
        System.out.print("数组快速排序：\t");
        for (int item : data) {
            System.out.print(item);
            System.out.print('\t');
        }
        System.out.println();
    }
}
