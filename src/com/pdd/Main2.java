package com.pdd;

import java.util.*;

/**
 * Created by lynch on 2019-07-28. <br>
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String wordStr = input.nextLine();
        String[] wordArray = wordStr.split(" ");
        System.out.println(getCircle(wordArray, wordArray.length));
        System.out.println(getCircle(wordArray));

    }

    /**
     * 有问题，该方法只能判断顺序不变的单词
     *
     * @param wordArray
     * @param length
     * @return
     */
    public static boolean getCircle(String[] wordArray, int length) {
        char first = wordArray[0].charAt(0);
        char end = wordArray[length - 1].charAt(wordArray[length - 1].length() - 1);
        if (first != end)
            return false;
        for (int i = 0; i < length - 1; i++) {
            if (wordArray[i].charAt(wordArray[i].length() - 1) != wordArray[i + 1].charAt(0)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 集合相互全包含
     *
     * @param wordArray
     * @return
     */
    public static boolean getCircle(String[] wordArray) {
        Set<Character> start = new HashSet<>();
        Set<Character> end = new HashSet<>();
        for (String word : wordArray) {
            start.add(word.charAt(0));
            end.add(word.charAt(word.length() - 1));
        }
        if (start.containsAll(end) && end.containsAll(start))
            return true;
        return false;
    }
}
