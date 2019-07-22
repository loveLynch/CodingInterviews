package com.lynch.sort;

import static com.lynch.sort.BubbleSort.testBubbleSort;
import static com.lynch.sort.HeapSort.testHeapSort;
import static com.lynch.sort.InsertSort.testInsertionSort;
import static com.lynch.sort.MergeSort.testMergeSort;
import static com.lynch.sort.QuickSort.testQuickSort;
import static com.lynch.sort.SelectSort.testSelectionSort;
import static com.lynch.sort.ShellSort.testShellSort;

/**
 * Created by lynch on 2019-03-27. <br>
 * 排序测试
 * 排序算法比较：
 *
 *
 *
 * 排序算法
 * 时间复杂度
 * 空间复杂度
 * 稳定性
 * 简要介绍
 *
 *
 *
 *
 * 快排quickSort
 * o(nlogn)
 * o(logn)
 * 不稳定举例：1,2,3(A),3(B)=》1,2,3(B),3(A)
 * （小数，基准值，大数）
 *
 *
 * 归并排序mergeSort
 * o(nlogn)
 * o(n)
 * 稳定
 * 把数据分为两个有序段，从两段中逐个选最小的元素移入新数据段的末尾。
 *
 *
 * 堆排序heapSort
 * o(nlogn)
 * o(1)
 * 不稳定举例：2(A),2(B),2(C)=》2(C),2(B),2(A)
 * 建立最大堆，交换堆的第一个与最后一个元素，调整堆（堆排序的0号元素不能使用）。
 *
 *
 * 冒泡排序bubbleSort
 * o(n^2)
 * o(1)
 * 稳定
 * （无序区，有序区）无序区从左到右通过两两交换找出最大元素放到有序区的左边。
 *
 *
 * 选择排序selectionSort
 * o(n^2)
 * o(1)
 * 不稳定举例：2(A),2(B),1=》1,2(B),2(A)
 * （有序区，无序区）。从右到左在无序区里找一个最小的元素跟在有序区的后面。比较得多，换得少。
 *
 *
 * 插入排序insertionSort
 * o(n^2)
 * o(1)
 * 稳定
 * （有序区，无序区）。从左到右把无序区的第一个元素插入到有序区的合适的位置。比较得少，换得多。
 *
 *
 * 希尔排序shellSort
 * o(n^1.3)
 * o(1)
 * 不稳定举例：2(A),1(A),1(B),2(B)=》1(B),1(A),2(A),2(B)
 * 每一轮按照事先决定的间隔进行插入排序，间隔会依次缩小，最后一次一定要是1。
 *
 *
 *
 * 希尔排序并无一个确定、可计算的时间复杂度。次数值在1~2之间。有人在大量的实验后得出结论：当n在某个特定的范围后希尔排序的比较和移动次数减少至n^1.3。
 **/
public class TestSort {
    public static void main(String[] args) {
        testQuickSort();
        testMergeSort();
        testHeapSort();
        testBubbleSort();
        testSelectionSort();
        testInsertionSort();
        testShellSort();
    }
}
