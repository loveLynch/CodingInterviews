package com.lynch;

/**
 * Created by lynch on 2019/3/13. <br>
 * 19.正则表达式匹配
 * 实现一个函数用来匹配包含'.'和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
 * 本题中，匹配是指字符串的所有字符匹配整个模式。
 * 如：字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配
 **/
public class RegularEXpression_19 {
    private static boolean match(String str, String pattern) {
        if (str == null || pattern == null)
            return false;
        return matchCore(str, 0, pattern, 0);
    }

    private static boolean matchCore(String str, Integer strIndex, String pattern, Integer patternIndex) {
        StringBuilder strBuilder = new StringBuilder(str);
        StringBuilder patternBuilder = new StringBuilder(pattern);
        //如果匹配串和模式串都匹配结束
        if (strBuilder.length() == strIndex && patternBuilder.length() == patternIndex)
            return true;
        //匹配串未结束，模式串结束不匹配
        if (strBuilder.length() != strIndex && patternBuilder.length() == patternIndex)
            return false;
        //匹配串结束，模式串未结束进行递归匹配
        if (strBuilder.length() == strIndex && patternBuilder.length() != patternIndex) {
            if (patternIndex + 1 < pattern.length() && pattern.charAt(patternIndex + 1) == '*')
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            else {
                return false;
            }
        }
        //当模式中第二个字符不是'*'时或者只剩一个字符
        //都一起向后继续移动进行匹配
        if (patternIndex == pattern.length() - 1 || pattern.charAt(patternIndex + 1) != '*') {
            if (pattern.charAt(patternIndex) == '.' || pattern.charAt(patternIndex) == str.charAt(strIndex))
                return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
            else {
                return false;
            }
        }
        //当模式中第二个字符是'*'时,
        //1。模式中第一个字符和字符串第一个字符相匹配，字符串向后移动一个字符，在模式上向后移动两个字符或保持模式不变
        //2。直接在模式上向后移动两个字符(忽略'*'及前面的字符)
        else {
            if (pattern.charAt(patternIndex) == '.' || pattern.charAt(patternIndex) == str.charAt(strIndex))
                return matchCore(str, strIndex + 1, pattern, patternIndex)
                        || matchCore(str, strIndex + 1, pattern, patternIndex + 2)
                        || matchCore(str, strIndex, pattern, patternIndex + 2);
            else {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(match("aaa", "a.a"));//true
        System.out.println(match("aaa", "ab*ac*a"));//true
        System.out.println(match("aaa", "aa.a"));//false
        System.out.println(match("aaa", "ab*a"));//false
        System.out.println(match("aaa", "a*"));//true
    }
}
