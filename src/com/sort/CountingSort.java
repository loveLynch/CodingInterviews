package com.sort;

import java.util.Arrays;

/**
 * Created by lynch on 2019-09-09. <br>
 * 计数排序
 **/
public class CountingSort {

    /**
     * 计数排序
     * 算法描述
     * 1.找出待排序的数组中最大和最小的元素；
     * 2.统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
     * 3.对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
     * 4.反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
     * <p>
     * 最佳情况：T(n) = O(n+k)  最差情况：T(n) = O(n+k)  平均情况：T(n) = O(n+k)
     *
     * @param array
     * @return
     */
    public static int[] countingSort(int[] array) {
        if (array.length == 0) return array;
        int bias, min = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
            if (array[i] < min)
                min = array[i];
        }
        bias = 0 - min;
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, 0);
        for (int i = 0; i < array.length; i++) {
            bucket[array[i] + bias]++;
        }
        int index = 0, i = 0;
        while (index < array.length) {
            if (bucket[i] != 0) {
                array[index] = i - bias;
                bucket[i]--;
                index++;
            } else
                i++;
        }
        return array;
    }
}
