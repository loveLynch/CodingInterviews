package com.lynch;

/**
 * Created by lynch on 2019/3/6. <br>
 * 11.旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转
 * 输入一个递增排序的数组的一个元素旋转,输出旋转数组的最小元素
 * 例如，数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1
 * <p>
 * 特例：前0个元素旋转，即原数组
 * 注意：数组中多个重复的数字
 **/
public class RotatingMinInArray_11 {
    public static int min(int[] data) {
        if (data == null || data.length == 0)
            return -1;
        int leftIndex = 0;
        int rightIndex = data.length - 1;
        int midIndex;
        while (leftIndex < rightIndex) {
            midIndex = leftIndex + (rightIndex - leftIndex) / 2;
            //left < right
            if (data[leftIndex] < data[rightIndex])
                return data[leftIndex];
                //left > right
            else if (data[leftIndex] > data[rightIndex]) {
                if (data[midIndex] >= data[leftIndex])
                    leftIndex = midIndex + 1;//右移
                else
                    rightIndex = midIndex;//左移
            }
            //left = right 相等时
            else {
                if (data[leftIndex] < data[midIndex])
                    leftIndex = midIndex + 1;
                else if (data[leftIndex] > data[midIndex])
                    rightIndex = midIndex;
                else {
                    //移动下标
                    leftIndex = leftIndex + 1;
                    rightIndex = rightIndex - 1;
                }
            }
        }
        return data[rightIndex];
    }

    public static void main(String[] args) {
        int[] data1 = {3, 4, 5, 1, 2};
        int[] data2 = {1, 0, 1, 1, 1};
        int[] data3 = {1, 1, 1, 0, 1};
        System.out.println(min(data1));
        System.out.println(min(data2));
        System.out.println(min(data3));
    }

}
