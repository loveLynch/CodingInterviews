package com.sort;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-09. <br>
 **/
public class Tests {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = input.nextInt();
        }
        System.out.println("原始数据5：");
        print(array);
        System.out.println("排序后的数据：");
        print(BubbleSort.bubbleSort(array));
        print(SelectionSort.selectionSort(array));
        print(InsertionSort.insertionSort(array));
    }

    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
