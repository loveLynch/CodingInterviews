package com.lynch;

/**
 * Created by lynch on 2019-03-28. <br>
 * 46.把数字翻译成字符串
 * <p>
 * 给定一个数字，按照如下规则翻译成字符串：0翻译成“a”，1翻译成“b”...25翻译成“z”。一个数字有多种翻译可能，
 * 如:12258一共有5种，分别是bccfi，bwfi，bczi，mcfi，mzi。
 * 实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 **/
public class TranslateNumbersCount_46 {

    private static int getTranslationCount(int number) {
        if (number < 0)
            return 0;
        if (String.valueOf(number).length() == 1)
            return 1;
        return getTranslationCount(Integer.toString(number));
    }

    //动态规划，从右到左计算。
    //f(r-2) = f(r-1)+g(r-2,r-1)*f(r);
    //如果r-2，r-1能够翻译成字符，则g(r-2,r-1)=1，否则为0
    private static int getTranslationCount(String number) {
        int f1 = 0, f2 = 1, g = 0;
        int temp;
        for (int i = number.length() - 2; i >= 0; i--) {
            if (Integer.parseInt(number.charAt(i) + "" + number.charAt(i + 1)) < 26)
                g = 1;
            else {
                g = 0;
            }
            temp = f2;
            f2 = f2 + g * f1;
            f1 = temp;
        }
        return f2;
    }

    public static void main(String[] args) {
        System.out.println(getTranslationCount(-10));  //0
        System.out.println(getTranslationCount(1234));  //3
        System.out.println(getTranslationCount(12258)); //5
    }
}
