package com.bytedance.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-07-31. <br>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例1
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例2
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 **/
public class CommonPrefix {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] strs = input.nextLine().split(" ");
        System.out.println(longestCommonPrefix(strs));

    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null)
            return "";
        List<char[]> charList = new ArrayList<>();
        for (String str : strs) {
            charList.add(str.toCharArray());
        }
        for (int i = 0; i < charList.get(0).length; i++)
            for (int j = 1; j < charList.size(); j++) {
                if (i >= charList.get(j).length || charList.get(j)[i] != charList.get(0)[i])
                    return new String(charList.get(0)).substring(0, i);

            }
        return strs[0];


    }
}
