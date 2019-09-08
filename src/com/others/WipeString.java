package com.others;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-09-08. <br>
 **/
public class WipeString {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        List<Character> result = wipeStringLocation(str);
    }

    private static List<Character> wipeStringLocation(String str) {
        List<Character> result = new ArrayList<>();
        char pre = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            int count = 1;
            if (str.charAt(i) == pre) {
                count++;
            } else {
                pre = str.charAt(i);
            }
            if (count == 2) {
                char letter = str.charAt(i);
                result.add(letter);
                result.add((char) (i + 1));
                if (i > 2 && i < str.length())
                    str = str.substring(0, i - 2) + str.substring(i);

            }

        }
        return null;
    }
}
