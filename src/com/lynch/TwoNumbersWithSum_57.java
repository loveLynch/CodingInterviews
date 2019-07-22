package com.lynch;

/**
 * Created by lynch on 2019-04-02. <br>
 * 57.和为s的数字
 * <p>
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使它们的和为s。如果有多对和为s，输入任意一对即可。
 **/
public class TwoNumbersWithSum_57 {
    /**
     * 使用两个指针分别指向数组的头、尾。如果和大于s，头部指针后移，如果和小于s，尾部指针前移。
     *
     * @param data
     * @param sum
     * @return
     */
    private static int[] findNumbersWithSum(int[] data, int sum) {
        int[] result = new int[]{0, 0};
        if (data == null || data.length < 2)
            return result;
        int curSum = data[0] + data[data.length - 1];
        int left = 0, right = data.length - 1;
        while (curSum != sum && left < right) {
            if (curSum > sum)
                right--;
            else
                left++;
            curSum = data[left] + data[right];
        }
        if (curSum == sum) {
            result[0] = data[left];
            result[1] = data[right];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 4, 7,8,4, 11,13, 15};
        int[] result = findNumbersWithSum(data, 15);
        for (int i = 0; i < result.length; i++)
            System.out.print(result[i]+" ");
    }
}
