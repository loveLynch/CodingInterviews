package com.lynch;

/**
 * Created by lynch on 2019-04-02. <br>
 * 58.翻转单词顺序
 * <p>
 * 输入一个英文句子，翻转单词顺序，单词内字符不翻转，标点符号和普通字母一样处理。例如输入输入“I am a student.”，则输出“student. a am I”。
 * 思路：
 * 首先字符串整体翻转，得到“.tneduts a ma I”；
 * 然后以空格作为分隔符进行切分，对于切分下来的每一部分再进行翻转，
 * 得到“student. a am I”。
 **/
public class ReverseWordsInSentence_58 {
    /**
     * 对翻转后的字符串中的每个单词进行还原翻转
     *
     * @param str
     * @return
     */
    private static String reverse(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        reverseSubString(stringBuilder, 0, stringBuilder.length() - 1);
        int start = 0, end = stringBuilder.indexOf(" ");
        while (start < stringBuilder.length() && end != -1) {
            reverseSubString(stringBuilder, start, end - 1);
            start = end + 1;
            end = stringBuilder.indexOf(" ", start);
        }
        //对最后一个（原字符串中的第一个单词）或只有一个单词的进行还原翻转
        if (start < stringBuilder.length())
            reverseSubString(stringBuilder, start, stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    /**
     * 翻转整个字符串
     *
     * @param stringBuilder
     * @param start
     * @param end
     */
    private static void reverseSubString(StringBuilder stringBuilder, int start, int end) {
        for (int i = start; i <= start + (end - start) / 2; i++) {
            char temp = stringBuilder.charAt(i);
            stringBuilder.setCharAt(i, stringBuilder.charAt(end - i + start));
            stringBuilder.setCharAt(end - i + start, temp);
        }
    }

    public static void main(String[] args) {
        System.out.println(reverse("I am a student."));
        System.out.println(reverse("student."));
    }
}
