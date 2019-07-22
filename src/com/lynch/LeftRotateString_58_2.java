package com.lynch;

/**
 * Created by lynch on 2019-04-02. <br>
 * 58.2.左旋转字符串
 * <p>
 * 实现一个函数完成字符串的左旋转功能。
 * 如:输入abcdefg和数字2，输出为cdefgab。
 * 思路：
 * 类似于58.翻转单词顺序。首先对于字符串“abcdefg”整体翻转，
 * 得到“gfedcba”；然后对于后2个字符“ba”进行翻转，
 * 对于剩下的字符“gfedc”进行翻转，得到“cdefgab”。
 **/
public class LeftRotateString_58_2 {

    /**
     * 左旋转字符串
     *
     * @param str
     * @param i
     * @return
     */
    public static String leftRotateString(String str, int i) {
        if (str == null || str.length() == 0 || i <= 0 || i >= str.length())
            return str;
        StringBuilder stringBuilder = new StringBuilder(str);
        //整体字符串翻转
        reverseSubString(stringBuilder, 0, stringBuilder.length() - 1);
        //对翻转后的后i字符串还原翻转
        reverseSubString(stringBuilder,stringBuilder.length()-i,stringBuilder.length() - 1);
        //对翻转后的前str.length-i还原翻转
        reverseSubString(stringBuilder,0,stringBuilder.length() - i-1);
        return stringBuilder.toString();
    }

    /**
     * 对整个字符串进行翻转
     *
     * @param stringBuilder
     * @param start
     * @param end
     */
    public static void reverseSubString(StringBuilder stringBuilder, int start, int end) {
        for (int i = start; i <= start + (end - start) / 2; i++) {
            char temp = stringBuilder.charAt(i);
            stringBuilder.setCharAt(i, stringBuilder.charAt(end - i + start));
            stringBuilder.setCharAt(end - i + start, temp);
        }
    }

    public static void main(String[] args) {
        System.out.println(leftRotateString("abcdefg",2));
    }
}
