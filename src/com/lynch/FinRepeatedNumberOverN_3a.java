package com.lynch;

/**
 * Created by lynch on 2019/2/25. <br>
 * 3(2).不修改数组找出任意一个重复的数字
 * 长度为n+1的数组
 * 数组中的数字都在0～n的范围内
 **/
public class FinRepeatedNumberOverN_3a {

    public static void main(String[] args) {
        int[] numbers = {2, 3, 5, 4, 3, 2, 6, 7};
        System.out.println(getDuplication(numbers));
    }

    /**
     * 二分查找的思路，时间复杂度O(nlogn)，空间复杂度O(1)
     *
     * @param numbers
     * @return
     */
    public static int getDuplication(int numbers[]) {
        if (numbers == null || numbers.length < 2) {
            return -1;
        }
        int start = 1;
        int end = numbers.length - 1;
        while (end >= start) {
            int middle = (end - start) / 2 + start;
            int count = countRange(numbers, start, middle);
            if (end == start) {
                if (count > 1)
                    return start;
                else
                    break;
            }
            if (count > (middle - start + 1))
                end = middle;
            else
                start = middle + 1;
        }

        return -1;


    }

    public static int countRange(int numbers[], int start, int end) {
        if (numbers == null)
            return 0;
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] >= start && numbers[i] <= end)
                ++count;
        }
        return count;


    }
}
