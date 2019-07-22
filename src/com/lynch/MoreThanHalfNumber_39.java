package com.lynch;

/**
 * Created by lynch on 2019-03-27. <br>
 * 39.数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 如：输入长度为9的数组{1,2,3,2,2,2,5,4,2}，则超过数组长度的数字一半是2
 * 思路：：排序后求中位数、用快排的分区函数求中位数（topK问题）
 **/
public class MoreThanHalfNumber_39 {

    /**
     * 排序之后位于数组中间的数字一定是那个出现的次数超过数组长度的一半的数字
     * 结合快速排序
     *
     * @param numbers
     * @return
     */
    private static int moreThanHalfNum_ONE(int[] numbers) {
        if (numbers == null || numbers.length == 0)
            return 0;
        int left = 0, right = numbers.length - 1;
        //获取执行分区后下标为k的数据值（即第k+1小的数字）
        int k = numbers.length >>> 1;
        int index = partition(numbers, left, right);
        while (index != k) {
            if (index > k)
                index = partition(numbers, left, index - 1);
            else
                index = partition(numbers, index + 1, right);
        }
        return numbers[k];
    }

    /**
     * 快排
     *
     * @param numbers
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] numbers, int left, int right) {
        int pivot = numbers[left];
        while (left < right) {
            while (left < right && numbers[right] >= pivot)
                right--;
            if (left < right)
                numbers[left] = numbers[right];
            while (left < right && numbers[left] < pivot)
                left++;
            if (left < right)
                numbers[right] = numbers[left];
        }
        numbers[left] = pivot;
        return left;
    }

    /**
     * 根据数组特点进行记录、查找，时间复杂度o(n)
     *
     * @param data
     * @return
     */
    public static int moreThanHalfNum_TWO(int[] data) {
        if (data == null || data.length == 0)
            return 0;
        int count = 1;
        int value = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] == value)
                count++;
            else if (data[i] != value && count == 1)
                value = data[i];
            else
                count--;
        }
        return value;
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(moreThanHalfNum_ONE(data));
        System.out.println(moreThanHalfNum_TWO(data));
    }

}
