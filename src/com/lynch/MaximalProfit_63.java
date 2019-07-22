package com.lynch;

/**
 * Created by lynch on 2019-04-03. <br>
 * 63：股票的最大利润
 * <p>
 * 求买卖股票一次能获得的最大利润。
 * 如，输入{9，11，8，5，7，12，16，14}，
 * 5的时候买入，16的时候卖出，则能获得最大利润11。
 **/
public class MaximalProfit_63 {
    /**
     * 遍历过程中记录最小值min，然后计算当前值与min的差值diff，
     * 并更新maxDiff，maxDiff=max(diff)。
     *
     * @param data
     * @return
     */
    private static int maxDiff(int[] data) {
        if (data == null || data.length < 2)
            return 0;
        int min = data[0];
        int maxDiff = data[1] - min;
        if (data[1] < min)
            min = data[1];
        for (int i = 2; i < data.length; i++) {
            if (data[i] - min > maxDiff)
                maxDiff = data[i] - min;
            if (data[i] < min)
                min = data[i];
        }
        return maxDiff;
    }

    public static void main(String[] args) {
        int[] data1 = new int[]{9, 11, 8, 5, 7, 12, 16, 14};
        int[] data2 = new int[]{9, 8, 7, 6, 5, 4, 3, 1};
        System.out.println(maxDiff(data1)); //11
        System.out.println(maxDiff(data2)); //-1
    }
}
