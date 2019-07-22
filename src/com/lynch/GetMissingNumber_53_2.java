package com.lynch;

/**
 * Created by lynch on 2019-04-02. <br>
 * 53.2.0~n中缺失的数字
 * <p>
 * 一个长度为n的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0~n之内。
 * 在范围0~n内的n个数字有且只有一个数字不在该数组中，请找出。
 * <p>
 * 用二分法找到数组中第一个数值不等于下标的数字。
 **/
public class GetMissingNumber_53_2 {
    private static int getMissingNumber(int[] data) {
        int left = 0, right = data.length - 1, mid;
        while (left <= right) {
            //mid=left+(right-left)/2 用位运算替换除法
            //注意加减法优先级高于位运算
            mid = left + ((right - left) >> 1);
            if (data[mid] == mid)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] data1 = new int[]{0, 1, 2, 3, 4, 5}; //6
        int[] data2 = new int[]{0, 1, 3, 4, 5}; //2
        int[] data3 = new int[]{1, 2}; //0
        System.out.println(getMissingNumber(data1));
        System.out.println(getMissingNumber(data2));
        System.out.println(getMissingNumber(data3));
    }
}
