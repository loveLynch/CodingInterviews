package com.lynch;

/**
 * Created by lynch on 2019-04-02. <br>
 * 56.数组中只出现一次的两个数字
 * <p>
 * 一个整数数组里除了两个数字出现一次，其他数字都出现两次。请找出这两个数字。
 * 要求时间复杂度为o(n)，空间复杂度为o(1)。
 **/
public class NumberAppearOnce_56_1 {
    /**
     * 2, 4, 3, 6, 3, 2, 5, 5
     * 相关数字的二进制表示为：
     * 2 = 0010       3 = 0011       4 = 0100
     * 5 = 0101       6 = 0110
     * <p>
     * 步骤1 全体异或：2^4^3^6^3^2^5^5 = 4^6 = 0010
     * 步骤2 确定位置：对于0010，从右数的第二位为1，因此可以根据倒数第2位是否为1进行分组
     * 步骤3 进行分组：分成[2,3,6,3,2]和[4,5,5]两组
     * 步骤4 分组异或：2^3^6^3^2 = 6，4^5^5 = 4，因此结果为4，6。
     *
     * @param data
     * @return
     */
    private static int[] findNumsAppearOnce(int[] data) {
        int result = 0;
        for (int i = 0; i < data.length; i++)
            result ^= data[i];
        int indexOf1 = findFirstBit1(result);
        int ret[] = new int[]{0, 0};
        if (indexOf1 < 0)
            return ret;
        for (int i = 0; i < data.length; i++) {
            if ((data[i] & indexOf1) == 0)
                ret[0] ^= data[i];
            else
                ret[1] ^= data[i];
        }
        return ret;
    }

    public static int findFirstBit1(int num) {
        if (num < 0)
            return -1;
        int indexOf1 = 1;
        while (num != 0) {
            if ((num & 1) == 1)
                return indexOf1;
            else {
                num = num >> 1;
                indexOf1 *= 2;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 4, 3, 6, 3, 2, 5, 5};
        int[] result = findNumsAppearOnce(data); // 4,6
        System.out.println(result[0]);
        System.out.println(result[1]);

    }
}
