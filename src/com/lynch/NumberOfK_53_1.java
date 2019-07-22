package com.lynch;

/**
 * Created by lynch on 2019-04-02. <br>
 * 53.1.数字在排序数组中出现的次数
 * <p>
 * 统计一个数字在排序数组中出现的次数。
 * 如:输入{1,2,3,3,3,3,4,5}和数字3，由于3在这个数组中出现了4次，因此输出4。
 **/
public class NumberOfK_53_1 {
    /**
     * 遍历的话时间复杂度为o(n)
     * 考虑到数组是排序好的，可使用二分法，时间复杂度o(logn)
     *
     * @param data
     * @param k
     * @return
     */

    private static int getNumberOfK(int[] data, int k) {
        if(data==null)
            return 0;
        int start = getStartOfK(data, k);
        if (start == -1)
            return 0;
        int end = getEndOfK(data, start, k);
        return end - start + 1;
    }

    private static int getStartOfK(int[] data, int k) {
        if (data[0] > k || data[data.length - 1] < k)
            return -1;
        int left = 0, right = data.length - 1, mid;
        while (left <= right) {
            if (right == left) {
                if (data[left] == k)
                    return left;
                else
                    return -1;
            }
            //当长度为2，mid取左值
            mid = left + (right - left) / 2;
            if (data[mid] > k)
                right = mid - 1;
            else if (data[mid] < k)
                left = mid + 1;
            else
                right = mid;
        }
        return -1;
    }

    private static int getEndOfK(int[] data, int start, int k) {
        int left = start, right = data.length - 1, mid;
        while (left <= right) {
            if (left == right) {
                if (data[left] == k)
                    return left;
                else
                    return -1;
            }
            //当长度为2，mid取右值
            mid = left + (right - left + 1) / 2;
            if (data[mid] > k)
                right = mid - 1;
            else if (data[mid] < k)
                left = mid + 1;
            else
                left = mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] data1 = new int[]{1, 2, 3, 3, 3, 3, 5, 6};
        int[] data2 = new int[]{1, 2, 3, 3, 3, 3, 4, 5};
        int[] data3 = new int[]{3, 3, 3, 3, 3, 3, 3, 3};
        System.out.println(getNumberOfK(data1, 4));
        System.out.println(getNumberOfK(data2, 3));
        System.out.println(getNumberOfK(data3, 3));
    }
}
