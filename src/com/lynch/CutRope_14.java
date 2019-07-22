package com.lynch;

/**
 * Created by lynch on 2019/3/11. <br>
 * 14.剪绳子
 * 给一根长为n的绳子，请把绳子分为m段（m、n都是整数，n>1,m>=1）
 * 每段绳子的长度记为k[0]、k[1]...k[m]
 * k[0]*k[1]*...*k[m]可能的最大乘积是多少？
 * 如：当绳子为8时，剪成2、3、3三段，此时得到的面积最大是18
 **/
public class CutRope_14 {
    /**
     * 动态规划
     *
     * @param length
     * @return
     */
    private static int maxProductAfterCutting_ONE(int length) {
        if (length < 2)
            return 0;
        if (length == 2)
            return 1;
        if (length == 3)
            return 2;
        //第i个元素表示把长度为i的绳子剪成若干段之后格段长度乘积的最大值
        int[] products = new int[length + 1];
        products[0] = 0;
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;
        int max = 0;
        for (int i = 4; i <= length; i++) {
            max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int product = products[j] * products[i - j];
                if (max < product)
                    max = product;
            }
            products[i] = max;
        }
        return products[length];
    }

    /**
     * 贪婪算法
     * 当绳子长度n>=5时，2(n-2)>n , 3(n-3)>n,且3(n-3)>=2(n-2)
     * 当n<5时，n=4只能剪成1、3或2、2
     *
     *
     * @param length
     * @return
     */
    private static int maxProductAfterCutting_TWO(int length) {
        if (length < 2)
            return 0;
        if (length == 2)
            return 1;
        if (length == 3)
            return 2;
        //尽可能多地剪去长度为3的绳子段
        int timesOf3 = length / 3;
        //当绳子最后剩下的长度为4的时候，不能再剪去长度为3的绳子段
        //此时更好的方法是把绳子剪成长度为2的两段，因为2*2>1*3
        if (length - timesOf3 * 3 == 1)
            timesOf3 -= 1;
        int timesOf2 = (length - timesOf3 * 3) / 2;

        return (int) ((Math.pow(3, timesOf3)) * Math.pow(2, timesOf2));
    }

    public static void main(String[] args) {
        System.out.println("动态规划");
        for (int i = 2; i < 20; i++) {
            System.out.println("长度为" + i + "的最大值->" + maxProductAfterCutting_ONE(i));
        }
        System.out.println("贪婪算法");
        for (int i = 2; i < 20; i++) {
            System.out.println("长度为" + i + "的最大值->" + maxProductAfterCutting_TWO(i));
        }
    }
}
