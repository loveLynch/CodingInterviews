package com.bytedance.string;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-01. <br>
 * 翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 示例 1：
 * <p>
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 * <p>
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * <p>
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 **/
public class ReverseWord {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        System.out.println(reverseWords(line));

    }

    public static String reverseWords(String s) {
        String[] strArray = s.split(" ");
        StringBuilder result = new StringBuilder();
        for (String str : strArray) {
            str = str.replace(" ", "");
            if (str.length() != 0) {
                result.append(new StringBuilder(str.trim()).reverse());
                result.append(" ");
            }
        }
        return result.reverse().toString().trim();

    }
}
