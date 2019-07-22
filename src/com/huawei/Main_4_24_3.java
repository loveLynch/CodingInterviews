package com.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by lynch on 2019-04-24. <br>
 **/
public class Main_4_24_3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String english = in.nextLine().toLowerCase();
        String number = stringTONumber(english);
        System.out.println(number);
    }

    public static String stringTONumber(String str) {
        Map<String, String> map = new HashMap<>();
        map.put("zero", "0");
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");
        map.put("eight", "8");
        map.put("nine", "9");
        String result = "";
        while (!result.equals(str)) {
            if (str.startsWith("on") || str.startsWith("tw") || str.startsWith("si")) {
                result += map.get(str.substring(0, 3));

            }
            if (str.startsWith("ze") || str.startsWith("fo") || str.startsWith("fi") || str.startsWith("ni")) {
                result += map.get(str.substring(0, 4));
                str = str.substring(4, str.length());


            }
            if (str.startsWith("th") || str.startsWith("se") || str.startsWith("ei")) {
                result += map.get(str.substring(0, 5));
                str = str.substring(5, str.length());


            }
        }
        return result;
    }
}
