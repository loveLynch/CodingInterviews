package com.lynch;

import java.util.HashMap;

/**
 * Created by lynch on 2019/3/13. <br>
 * 20.表示数值的字符串
 * 实现一个函数用来判断字符串是否表示数值（包含整数和小数）
 * 如：字符串"+100"、"5e2"、"-123"、"3.1416"及"-1E-16"都表示数值，
 * 但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是
 * <p>
 * 说明：表示数值的字符串遵循模式A[.[B]]{e|EC]或.B[e|EC]
 * 其中A为数值的整数部分，B(无符号整数)紧跟着小数点为数值的小数部分，C紧跟着'e'或'E'为数值的指数部分
 **/
public class NumericByString_20 {

    private static boolean isNumeric1(String str) {
        if (str == null || str.length() == 0)
            return false;
        int index;
        //不是直接是小数
        if (str.charAt(0) != '.') {
            index = scanInteger(str, 0);
            if (index == -1)
                return false;
            if (index == str.length())
                return true;
            //扫描到小数位置
            if (str.charAt(index) == '.') {
                if (index == str.length() - 1)
                    return true;
                index = scanInteger(str, index + 1);
                if (index == str.length())
                    return true;
            }
            //扫描到指数部分
            if (str.charAt(index) == 'e' || str.charAt(index) == 'E') {
                index = scanInteger(str, index + 1);
                if (index == str.length())
                    return true;
                else {
                    return false;
                }
            }
            return false;
            //直接是小数
        } else {
            index = scanInteger(str, 1);
            if (index == -1)
                return false;
            if (index == str.length())
                return true;
            if (str.charAt(index) == 'e' || str.charAt(index) == 'E') {
                index = scanInteger(str, index + 1);
                if (index == str.length())
                    return true;
            }
            return false;
        }

    }

    /**
     * 用来匹配A、C
     *
     * @param str
     * @param index
     * @return
     */
    private static int scanInteger(String str, Integer index) {
        if (index >= str.length())
            return -1;
        if ((str.charAt(index) == '+' || str.charAt(index) == '-') && str.length() > 1)
            return scanUnsignedInteger(str, index + 1);
        else {
            return scanUnsignedInteger(str, index);
        }
    }

    /**
     * 扫描字符串0～9的数位
     *
     * @param str
     * @param index
     * @return
     */
    private static int scanUnsignedInteger(String str, int index) {
        int origin = index;
        while (str.charAt(index) >= '0' && str.charAt(index) <= '9') {
            index++;
            if (index == str.length())
                return index;
        }
        if (origin == index)
            index = -1;
        return index;
    }


    //剑指Offer
    private static int inx;

    private static boolean isNumeric2(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        inx = 0;
        boolean flag = scanInteger(str);
        //判断小数部分
        if (inx < str.length && str[inx] == '.') {
            inx = inx + 1;
            //小数可以没有整数部分
            //小数点之后可以没有数字
            //小数点前后都可以有数字
            flag = scanUnInteger(str) || flag;
        }
        //判断指数部分
        if (inx < str.length && (str[inx] == 'e' || str[inx] == 'E')) {
            inx = inx + 1;
            flag = flag && scanInteger(str);
        }
        //e或E前面没有数字时，整个字符串不能表示数字
        //e或E后面没有数字时，整个字符串不能表示数字
        return flag && inx >= str.length;
    }

    //判断是否是整数
    private static boolean scanInteger(char[] str) {
        if (inx < str.length && (str[inx] == '+' || str[inx] == '-')) {
            inx = inx + 1;
        }
        return scanUnInteger(str);
    }

    //判断是否是无符号整数
    private static boolean scanUnInteger(char[] str) {
        int inx1 = inx;
        while (inx < str.length && str[inx] >= '0' && str[inx] <= '9') {
            inx = inx + 1;
        }
        return inx > inx1;
    }

    private static boolean isNumeric3(String str) {
        String string = String.valueOf(str);
        if (str.length() == 1 && (str.substring(0, 1).equals("+") || str.substring(0, 1).equals("-") || str.substring(0, 1).equals(".")))
            return false;
        return string.matches("[\\+-]?[0-9]*(\\.[0-9]*)?([eE][\\+-]?[0-9]+)?");
    }

    private static boolean isNumeric4(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        char strChar[] = str.toCharArray();
        boolean sign = false, decimal = false, hasE = false;
        for (int i = 0; i < strChar.length; i++) {
            if (strChar[i] == 'e' || strChar[i] == 'E') {
                if (i == strChar.length - 1)
                    return false; // e后面一定要接数字
                if (hasE)
                    return false;  // 不能同时存在两个e
                hasE = true;
            } else if (strChar[i] == '+' || strChar[i] == '-') {
                // 第二次出现+-符号，则必须紧接在e之后
                if (sign && strChar[i - 1] != 'e' && strChar[i - 1] != 'E')
                    return false;
                // 第一次出现+-符号，且不是在字符串开头，则也必须紧接在e之后
                if (!sign && i > 0 && strChar[i - 1] != 'e' && strChar[i - 1] != 'E')
                    return false;
                sign = true;
            } else if (strChar[i] == '.') {
                // e后面不能接小数点，小数点不能出现两次
                if (hasE || decimal)
                    return false;
                decimal = true;
            } else if (strChar[i] < '0' || strChar[i] > '9') // 不合法字符
                return false;
            if (1 == strChar.length && (strChar[0] == '+' || strChar[0] == '-' || strChar[0] == '.'))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isNumeric1("+100"));//true
        System.out.println(isNumeric1("5e2"));//true
        System.out.println(isNumeric1("-123"));//true
        System.out.println(isNumeric1("3.1416"));//true
        System.out.println(isNumeric1("-1E-16"));//true
        System.out.println(isNumeric1("12e"));//false
        System.out.println(isNumeric1("1a3.14"));//false
        System.out.println(isNumeric1("1.2.3"));//false
        System.out.println(isNumeric1("+-5"));//false
        System.out.println(isNumeric1("12e+5.4"));//false
        System.out.println(isNumeric1("."));//false
        System.out.println(isNumeric1("+"));//false
        System.out.println(isNumeric1("-"));//false

        System.out.println();

        System.out.println(isNumeric2("+100".toCharArray()));//true
        System.out.println(isNumeric2("5e2".toCharArray()));//true
        System.out.println(isNumeric2("-123".toCharArray()));//true
        System.out.println(isNumeric2("3.1416".toCharArray()));//true
        System.out.println(isNumeric2("-1E-16".toCharArray()));//true
        System.out.println(isNumeric2("12e".toCharArray()));//false
        System.out.println(isNumeric2("1a3.14".toCharArray()));//false
        System.out.println(isNumeric2("1.2.3".toCharArray()));//false
        System.out.println(isNumeric2("+-5".toCharArray()));//false
        System.out.println(isNumeric2("12e+5.4".toCharArray()));//false
        System.out.println(isNumeric2(".".toCharArray()));//false
        System.out.println(isNumeric2("+".toCharArray()));//false
        System.out.println(isNumeric2("-".toCharArray()));//false

        System.out.println();


        System.out.println(isNumeric3("+100"));//true
        System.out.println(isNumeric3("5e2"));//true
        System.out.println(isNumeric3("-123"));//true
        System.out.println(isNumeric3("3.1416"));//true
        System.out.println(isNumeric3("-1E-16"));//true
        System.out.println(isNumeric3("12e"));//false
        System.out.println(isNumeric3("1a3.14"));//false
        System.out.println(isNumeric3("1.2.3"));//false
        System.out.println(isNumeric3("+-5"));//false
        System.out.println(isNumeric3("12e+5.4"));//false
        System.out.println(isNumeric3("."));//false
        System.out.println(isNumeric3("+"));//false
        System.out.println(isNumeric3("-"));//false

        System.out.println();

        System.out.println(isNumeric4("+100"));//true
        System.out.println(isNumeric4("5e2"));//true
        System.out.println(isNumeric4("-123"));//true
        System.out.println(isNumeric4("3.1416"));//true
        System.out.println(isNumeric4("-1E-16"));//true
        System.out.println(isNumeric4("12e"));//false
        System.out.println(isNumeric4("1a3.14"));//false
        System.out.println(isNumeric4("1.2.3"));//false
        System.out.println(isNumeric4("+-5"));//false
        System.out.println(isNumeric4("12e+5.4"));//false
        System.out.println(isNumeric4("."));//false
        System.out.println(isNumeric4("+"));//false
        System.out.println(isNumeric4("-"));//false
    }
}
