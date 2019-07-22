package com.lynch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lynch on 2019-03-29. <br>
 * 50.第一个只出现一次的字符
 * <p>
 * 字符串中第一个只出现一次的字符。在字符串中找出第一个只出现一次的字符。
 * 如:输入abaccdeff，则输出b。
 **/
public class FirstNotRepeatingChar_50 {
    /**
     * 暴力求解
     * 从前到后依次判断每个字符是否只出现一次
     * 时间复杂度o(n^2)，空间复杂度o(1)
     *
     * @param str
     * @return
     */
    private static char firstNotRepeatingChar(String str) {
        //此处，\77表示ascii为77的字符(即?),用于表征没有只出现一次的字符
        if (str == null || str.length() == 0)
            return '\77';
        for (int i = 0; i < str.length() - 1; i++) {
            char temp = str.charAt(i);
            for (int j = 0; j <= str.length(); j++) {
                if (j == i)
                    continue;
                if (j == str.length())
                    return temp;
                if (temp == str.charAt(j))
                    break;
            }
        }
        return '\77';
    }

    /**
     * 引入哈希表，用空间换时间。时间复杂度o(n),空间占用1kB
     * java中，char是unicode编码，2字节（16bit）。但本题中，假设所有字符都可用ascii表示（0-255）。
     * 在上述假设下，可以申请一个长度为256的int数组作为哈希表，占用空间1kB
     *
     * @param str
     * @return
     */
    private static char firstNotRepeatingChar2(String str) {
        //使用这个数组记录字符出现次数
        int[] times = new int[256];
        for (int i = 0; i < str.length(); i++)
            times[str.charAt(i)]++;
        for (int i = 0; i < str.length(); i++) {
            if (times[str.charAt(i)] == 1)
                return str.charAt(i);
        }
        return '\77';
    }

    //Insert one char from stringstream
    static HashMap<Character, Integer> map = new HashMap<>();
    static ArrayList<Character> list = new ArrayList<>();


    public static void Insert(char ch) {
        int chCount = 0;
        if (map.get(ch) == null) {
            chCount = 1;
        } else {
            chCount = map.get(ch);
            chCount++;

        }
        map.put(ch, chCount);
        list.add(ch);

    }

    //return the first appearence once char in current stringstream
    public static char FirstAppearingOnce() {
        char c = '\0';
        //map无序，不可行
//        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
//            if (entry.getValue() == 1) {
//                c = entry.getKey();
//            }
//        }
        for (char key : list) {
            if (map.get(key) == 1) {
                c = key;
                break;
            }
        }
        if (c == '\0')
            return '#';
        return c;

    }

    public static void main(String[] args) {
//        String str = "abaccdeff";
//        System.out.println(firstNotRepeatingChar(str));
//        System.out.println(firstNotRepeatingChar2(str));
        //字符流
        Insert('g');
        System.out.println(FirstAppearingOnce());
        Insert('o');
        System.out.println(FirstAppearingOnce());
        Insert('o');
        System.out.println(FirstAppearingOnce());
        Insert('g');
        System.out.println(FirstAppearingOnce());
        Insert('l');
        System.out.println(FirstAppearingOnce());
        Insert('e');
        System.out.println(FirstAppearingOnce());


    }
}
