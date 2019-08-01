package com.bytedance;

import java.util.ArrayList;

/**
 * Created by lynch on 2019-06-29. <br>
 **/
public class RepairWord {

    public static String repair(String word) {
        if (word == null)
            return null;
        char[] singWord = word.toCharArray();
        ArrayList<Character> wordCharList = new ArrayList<>();
        for (int i = 0; i < singWord.length; i++) {
            wordCharList.add(singWord[i]);
        }
        //去掉第三个
        int wordLength = wordCharList.size();
        for (int i = 2; i < wordLength; i++) {
            while (i >= 2 && wordCharList.get(i - 2) == wordCharList.get(i - 1) && wordCharList.get(i - 1) == wordCharList.get(i)) {
                wordCharList.remove(i);
                wordLength--;
                i--;

            }

        }
        //去掉重复AABB -> AAB
        for (int i = 3; i < wordLength; i++) {
            while (i >= 3 && wordCharList.get(i - 3) == wordCharList.get(i - 2) && wordCharList.get(i - 1) == wordCharList.get(i)) {
                wordCharList.remove(i);
                wordLength--;
                i--;

            }
        }
        StringBuffer newWord = new StringBuffer();
        for (Character c : wordCharList)
            newWord.append(c);
        return newWord.toString();

    }


}
