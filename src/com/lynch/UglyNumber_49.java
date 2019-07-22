package com.lynch;

/**
 * Created by lynch on 2019/3/19. <br>
 * 49.丑数
 * 只包含因子2,3,5的数成为丑数。求按照从小到大的顺序第1500个丑数。1作为第一个丑数。
 **/
public class UglyNumber_49 {
    /**
     * 从第1到第position的丑数
     * 返回的是第position丑数
     * 获取丑数
     *
     * @param position
     * @return
     */
    private static int getUglyNumber_ONE(int position) {
        if (position < 0)
            return 0;
        int number = 0;
        int uglyFound = 0;
        while (uglyFound < position) {
            number++;
            if (isUglyNumber(number)) {
                uglyFound++;
            }
        }
        return number;

    }

    /**
     * 判断是否是丑数
     *
     * @param number
     * @return
     */
    private static boolean isUglyNumber(int number) {
        while (number % 2 == 0)
            number /= 2;
        while (number % 3 == 0)
            number /= 3;
        while (number % 5 == 0)
            number /= 5;
        return number == 1;
    }

    private static int getUglyNumber_TWO(int position) {
        if (position==0)
            return 0;
        int[] uglyNumber = new int[position];
        uglyNumber[0] = 1;
        int uglyIndex = 0, multiply2 = 0, multiply3 = 0, multiply5 = 0;
        while (uglyIndex + 1 < position) {
            uglyNumber[++uglyIndex] = min(uglyNumber[multiply2] * 2, uglyNumber[multiply3] * 3, uglyNumber[multiply5] * 5);
            //2*3=6，3*2=6，会有重复值，因此下面需要使用if，而不能用if-else
            if (uglyNumber[multiply2] * 2 == uglyNumber[uglyIndex])
                multiply2++;
            if (uglyNumber[multiply3] * 3 == uglyNumber[uglyIndex])
                multiply3++;
            if (uglyNumber[multiply5] * 5 == uglyNumber[uglyIndex])
                multiply5++;
        }
        return uglyNumber[position - 1];
    }

    private static int min(int x, int y, int z) {
        int temp = x < y ? x : y;
        return temp < z ? temp : z;
    }

    public static void main(String[] args) {
        //获取第10个丑数
        System.out.println(getUglyNumber_ONE(10));
        for (int i = 1; i <= 10; i++) {
            System.out.print(getUglyNumber_TWO(i) + " ");
        }
    }
}
