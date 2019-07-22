package com.lynch;

/**
 * Created by lynch on 2019/3/14. <br>
 * 21.调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有奇数位于数组的前半部分，所有的偶数位于数组的后半部分
 **/
public class OddOverEven_21 {
    /**
     * 前后移动下标，（两者）遇到偶数向后交换，遇到奇数向前交换
     *
     * @param data
     * @return
     */
    private static int[] ReorderOddEven_ONE(int[] data) {
        if (data == null || data.length == 0)
            return null;
        int begin = 0;
        int end = data.length - 1;
        while (begin < end) {
            //begin向后移动，直到遇到偶数
            while ((begin < end) && (data[begin] & 1) != 0) {
                begin++;
            }
            //end向前移动，直到遇到奇数
            while ((begin < end) && (data[end] & 1) == 0) {
                end--;
            }
            if (begin < end) {
                int temp = data[begin];
                data[begin] = data[end];
                data[end] = temp;
            }
        }
        return data;
    }

    /**
     * 移动下标
     * 解耦合判断
     *
     * @param data
     * @return
     */
    private static int[] ReorderOddEven_TWO(int[] data) {
        if (data == null || data.length == 0)
            return null;
        int begin = 0;
        int end = data.length - 1;
        while (begin < end) {
            //begin向后移动，直到遇到偶数
            while ((begin < end) && !isEven(data[begin])) {
                begin++;
            }
            //end向前移动，直到遇到奇数
            while ((begin < end) && isEven(data[end])) {
                end--;
            }
            if (begin < end) {
                int temp = data[begin];
                data[begin] = data[end];
                data[end] = temp;
            }

        }
        return data;
    }

    /**
     * 判断条件
     * <p>
     * 本题为是否为偶数
     * number&1==0为偶
     * number&1!=0为奇
     *
     * @param number
     * @return
     */
    private static boolean isEven(int number) {
        return (number & 1) == 0;
    }

    /**
     * 相对位置不变
     * @param array
     * @return
     */
    private static int[] reOrderArray(int[] array) {
        if (array == null || array.length == 0)
            return null;
        //相对位置不变，稳定性
        //插入排序的思想
        int m = array.length;
        int k = 0;//记录已经摆好位置的奇数的个数
        for (int i = 0; i < m; i++) {
            if (array[i] % 2 == 1) {
                int j = i;
                while (j > k) {//j >= k+1
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                    j--;
                }
                k++;
            }

        }
        return array;
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int newData1[] = ReorderOddEven_ONE(data);
        for (int i = 0; i < newData1.length; i++) {
            System.out.print(newData1[i] + " ");
        }
        System.out.println();
        int newData2[] = ReorderOddEven_TWO(data);
        for (int i = 0; i < newData2.length; i++) {
            System.out.print(newData2[i] + " ");
        }
        System.out.println();
        int newData3[] = reOrderArray(data);
        for (int i = 0; i < newData3.length; i++) {
            System.out.print(newData3[i] + " ");
        }
    }
}
