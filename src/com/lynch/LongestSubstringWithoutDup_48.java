package com.lynch;

/**
 * Created by lynch on 2019-03-29. <br>
 * 48.最长不含重复字符的子字符串
 * <p>
 * 输入一个字符串（只包含a~z的字符），求其最长不含重复字符的子字符串的长度。
 * 如:对于arabcacfr，最长不含重复字符的子字符串为acfr，长度为4。
 **/
public class LongestSubstringWithoutDup_48 {
    /**
     * 动态规划。用dp[]记录状态，dp[i]表示以下标为i的字符结尾不包含重复字符的最长子字符串长度。
     * 初始化dp[0] = 1，求maxdp。每次可以根据dp的前一个状态推导出后一个状态，因此可以省略dp数组，
     * 使用一个变量记录dp值，使用maxdp记录最大的dp值。
     *
     * @param str
     * @return
     */
    private static int longestSubstringWithoutDup(String str) {
        if (str == null || str.length() == 0)
            return 0;
        //dp数组可以省略，因为只需记录前一位置的dp值即可
        int[] dp = new int[str.length()];
        dp[0] = 1;
        int maxdp = 1;
        for (int dpIndex = 1; dpIndex < dp.length; dpIndex++) {
            //i最终会停在重复字符或者-1的位置,要注意i的结束条件
            int i = dpIndex - 1;
            for (; i >= dpIndex - dp[dpIndex - 1]; i--) {
                if (str.charAt(dpIndex) == str.charAt(i))
                    break;
            }
            dp[dpIndex] = dpIndex - i;
            if (dp[dpIndex] > maxdp)
                maxdp = dp[dpIndex];
        }
        return maxdp;
    }

    /**
     * 1）若当前字符第一次出现，则最长非重复子字符串长度f(i) = f(i-1)+1。
     * 2）若当前字符不是第一次出现，则首先计算当前字符与它上次出现位置之间的距离d。若d大于f(i-1)，
     * 即说明前一个非重复子字符串中没有包含当前字符，则可以添加当前字符到前一个非重复子字符串中，
     * 所以，f(i) = f(i-1)+1。若d小于或等于f(i-1)，即说明前一个非重复子字符串中已经包含当前字符，
     * 则不可以添加当前字符，所以，f(i) = d。
     *
     * @param string
     * @return
     */
    private static int findLongestSubstringLength(String string) {
        if (string == null || string.length() == 0)
            return 0;
        int maxLength = 0;
        int curLength = 0;
        int[] positions = new int[26];
        for (int i = 0; i < positions.length; i++) {
            positions[i] = -1; //初始化为-1，负数表示没出现过
        }
        for (int i = 0; i < string.length(); i++) {
            int curChar = string.charAt(i) - 'a';
            int prePosition = positions[curChar];
            //当前字符与它上次出现位置之间的距离
            int distance = i - prePosition;
            //当前字符第一次出现，或者前一个非重复子字符串中没有包含当前字符
            if (prePosition < 0 || distance > curLength) {
                curLength++;
            } else {
                //更新最长非重复子字符串的长度
                if (curLength > maxLength) {
                    maxLength = curLength;
                }
                curLength = distance;
            }
            positions[curChar] = i; //更新字符出现的位置
        }
        if (curLength > maxLength) {
            maxLength = curLength;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(longestSubstringWithoutDup("arabcacfr"));
        System.out.println(longestSubstringWithoutDup("abcdefaaa"));
        System.out.println(findLongestSubstringLength("arabcacfr"));
        System.out.println(findLongestSubstringLength("abcdefaaa"));

    }
}
