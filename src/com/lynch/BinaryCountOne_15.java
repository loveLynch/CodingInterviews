package com.lynch;

/**
 * Created by lynch on 2019/3/12. <br>
 * 15.二进制中
 * 1的个数
 * 实现一个函数，输入一个整数，输出该树二进制表示中1的个数
 * 如：9 --> 1001 count=2
 * 注意输入为负整数，通过右移位运算会陷入死循环
 **/
public class    BinaryCountOne_15 {

    /**
     * 1. n&1 判断最低位是不是1
     * 2. 1<1 ->10 =2 n&(10) 判断次低位是不是1
     * 3. 反复左移1
     *
     * @param n
     * @return
     */
    private static int numberOf1_ONE(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0)
                count++;
            flag <<= 1;
        }
        return count;

    }

    /**
     * 一个整数减去1，都是最右边的1变成0，如果右边还有0，则所有的0都变成1
     * 如：12 -> 1100 12-1=11 -> 1011
     * 1100&1011 = 1000 8-1=7 -> 0111 count=1
     * 1000&0111 = 0000               count=2
     *
     * @param n
     * @return
     */
    private static int numberOf1_TWO(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n - 1) & n;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(numberOf1_ONE(-5));
        System.out.println(numberOf1_TWO(-5));
    }
}
