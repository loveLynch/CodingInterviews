package com.lynch;

/**
 * Created by lynch on 2019-03-28. <br>
 * 44.数字序列中某一位的数字
 * 数字以0123456789101112131415...的格式序列化到一个字符序列中。
 * 在这个序列中，第五位（从0计数)是5...
 * 写一个函数求任意第n位的数字
 **/
public class DigitSequenceAtIndex_44 {
    private static int digitAtIndex(int index) {
        if (index < 0)
            return -1;
        //从0开始计数
        if (index < 10)
            return index;
        int curIndex = 10, length = 2;
        int boundNum = 10;
        while (curIndex + lengthSum(length) < index) {
            curIndex += lengthSum(length);
            boundNum *= 10;
            length++;
        }
        int addNum = (index - curIndex) / length;
        int curNum = boundNum + addNum;
        int curNumIndex = index - curIndex - addNum * length;
        return Integer.toString(curNum).charAt(curNumIndex) - '0';
    }

    /**
     * 几位位数的总数
     * 2位：90*2=180
     * 3位：900*3=2700
     * ...
     *
     * @param length
     * @return
     */
    private static int lengthSum(int length) {
        int count = 9;
        for (int i = 1; i < length; i++)
            count *= 10;
        return count * length;
    }

    public static void main(String[] args) {
        System.out.println(digitAtIndex(1001));
        System.out.println(digitAtIndex(44));

    }
}
