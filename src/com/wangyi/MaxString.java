package com.wangyi;


import java.util.Scanner;

/**
 * Created by lynch on 2019-08-11. <br>
 **/
public class MaxString {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        System.out.println(characterReplacement(s));
    }

    public static int characterReplacement(String s) {
        int max = 0, start = 0, end = 0, cur = 0;
        int[] count = new int[26];
        while (end < s.length()) {
            cur = Math.max(cur, ++count[s.charAt(end) - 'A']);
            while (end - start + 1 - cur > 2)
                count[s.charAt(start++) - 'A']--;
            max = Math.max(max, end - start + 1);
            end++;
        }
        return max;
    }
}
