package com.lynch;

/**
 * Created by lynch on 2019/2/25. <br>
 * 5.替换空格
 * 把字符串中的每一个空格都替换成%20，其实是字符数组
 * 如：We are happy. 替换成We%20are%20happy.
 **/
public class ReplaceBlank_5 {
    public static void main(String[] args) {
        System.out.println("No 1,The String API:");
        String originalStr = "We are happy.";
        String newStr = originalStr.replaceAll(" ", "%20");
        System.out.println(newStr);
        System.out.println("No 2,The Function:");
        char[] preStr = "We are happy.".toCharArray();
        char[] str = new char[18];
        for(int i=0;i<preStr.length;i++)
            str[i] = preStr[i];
        replaceBlank(str,13);
        System.out.println(str);




    }

    /**
     * 由于java的字符数组没有结束符，所以需要多传入个原始长度
     * 先计算好替换后的位置，从后向前替换，时间复杂度o(n)
     * @param str
     * @param length
     */
    public static void replaceBlank(char[] str, int length) {
        if (str == null || str.length < 0)
            return;
        int newLegth = length;
        for (int i = 0; i < length; i++) {
            if (str[i] == ' ') {
                newLegth += 2;
            }
        }
        int indexOfOriginal = length;
        int indexOfNew = newLegth;
        while (indexOfOriginal >= 0 && indexOfNew > indexOfOriginal) {
            if (str[indexOfOriginal] == ' ') {
                str[indexOfNew--] = '0';
                str[indexOfNew--] = '2';
                str[indexOfNew--] = '%';

            } else {
                str[indexOfNew--] = str[indexOfOriginal];
            }
            --indexOfOriginal;
        }

    }
}
