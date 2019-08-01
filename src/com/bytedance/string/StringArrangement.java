package com.bytedance.string;

import java.util.*;

/**
 * @author Lynch
 * @date 2019/8/1 9:40
 * <p>
 * 字符串的排列
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * 滑动窗口
 */
public class StringArrangement {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] strings = input.nextLine().split(" ");
        System.out.println(checkInclusion(strings[0], strings[1]));
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        //26个字母
        int[] c1 = new int[26];
        int[] c2 = new int[26];
        for (char c : s1.toCharArray())
            c1[c - 'a']++;
        for (int i = 0; i < s2.length(); i++) {
            if (i >= s1.length())
                --c2[s2.charAt(i - s1.length()) - 'a'];
            c2[s2.charAt(i) - 'a']++;

            if (Arrays.equals(c1, c2))
                return true;

        }
        return false;
    }


}
