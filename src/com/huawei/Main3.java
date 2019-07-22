package com.huawei;

import java.util.*;

/**
 * Created by lynch on 2019-04-24. <br>
 **/
public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        solve(str);

    }

    public static void solve(String str) {
        Map<Character, String> map = new HashMap<>();
        map.put('z', "zero");
        map.put('o', "one");
        map.put('w', "two");
        map.put('r', "three");
        map.put('u', "four");
        map.put('v', "five");
        map.put('x', "six");
        map.put('s', "seven");
        map.put('g', "eight");
        map.put('i', "nine");

        Map<Character, Integer> map2Num = new HashMap<>();
        map2Num.put('z', 0);
        map2Num.put('o', 1);
        map2Num.put('w', 2);
        map2Num.put('r', 3);
        map2Num.put('u', 4);
        map2Num.put('v', 5);
        map2Num.put('x', 6);
        map2Num.put('s', 7);
        map2Num.put('g', 8);
        map2Num.put('i', 9);

        str = str.toLowerCase();
        int[] nums = new int[26];
        for (char c : str.toCharArray()) {
            nums[c - 'a']++;

        }
        char[] chars = new char[]{'z', 'w', 'u', 'x', 'g', 'o', 'v', 'i', 'r', 's'};
        List<Integer> list = new ArrayList<>();
        int index = 0;
        while (index < 10) {
            char c = chars[index];
            while (nums[c - 'a'] > 0) {
                for (char t : map.get(c).toCharArray()) {
                    nums[t - 'a']--;
                }
                list.add(map2Num.get(c));
            }
            index++;

        }
        int res[] = new int[list.size()];
        for (int j = 0; j < list.size(); j++) {
            res[j] = list.get(j);
        }
        Arrays.sort(res);
        for (int num : res) {
            System.out.print(num);
        }


    }
}
