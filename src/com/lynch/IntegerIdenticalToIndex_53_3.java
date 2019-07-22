package com.lynch;

/**
 * Created by lynch on 2019-04-02. <br>
 * 53.3数组中数值和下标相等的元素
 * <p>
 * 假设一个单调递增的数组里的每个元素都是整数且是唯一的。
 * 编写一个程序，找出数组中任意一个数值等于其下标的元素。例如，输入{-3,-1,1,3,5}，输出3。
 * 二分法查找
 **/
public class IntegerIdenticalToIndex_53_3 {
    private static int getNumberSameAsIndex(int[] data) {
        if (data == null || data.length == 0)
            return -1;
        int left = 0, right = data.length - 1;
        if (data[left] > 0 || data[right] < 0)
            return -1;
        int mid;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (data[mid] == mid)
                return mid;
            else if (data[mid] < mid)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(getNumberSameAsIndex(new int[]{-3, -1, 1, 3, 5})); //3
        System.out.println(getNumberSameAsIndex(new int[]{0, 1, 2, 3, 4}));   //0~4
        System.out.println(getNumberSameAsIndex(new int[]{4, 5, 6, 7, 8}));   //-1
    }
}
