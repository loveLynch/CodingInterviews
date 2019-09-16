package com.kuaishou;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-09-16. <br>
 **/
public class Main2 {
    private static List<String> resultList = new ArrayList<>();
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String nums = input.nextLine();
        getCombinationOfLetter(nums, 0);
        System.out.println(resultList);
    }

    private static void getCombinationOfLetter(String nums, int index) {
        if (index == nums.length()) {
            if (!resultList.contains(sb.toString())) {
                resultList.add(sb.toString());
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            sb.append(numToLetterDict(nums.charAt(index)).get(i));
            getCombinationOfLetter(nums, index + 1);
            sb.delete(index, sb.length());
        }

        if (nums.charAt(index) == '7' || nums.charAt(index) == '9') {
            sb.append(numToLetterDict(nums.charAt(index)).get(3));
            getCombinationOfLetter(nums, index + 1);
            sb.delete(index, sb.length());
        }

        return;
    }


    private static List<Character> numToLetterDict(char num) {
        List<Character> letterList = new ArrayList<>();
        switch (num) {
            case '2':
                letterList.add('a');
                letterList.add('b');
                letterList.add('c');
                break;
            case '3':
                letterList.add('d');
                letterList.add('e');
                letterList.add('f');
                break;
            case '4':
                letterList.add('g');
                letterList.add('h');
                letterList.add('i');
                break;
            case '5':
                letterList.add('j');
                letterList.add('k');
                letterList.add('l');
                break;
            case '6':
                letterList.add('m');
                letterList.add('n');
                letterList.add('o');
                break;
            case '7':
                letterList.add('p');
                letterList.add('q');
                letterList.add('r');
                letterList.add('s');
                break;
            case '8':
                letterList.add('t');
                letterList.add('u');
                letterList.add('v');
                break;
            case '9':
                letterList.add('w');
                letterList.add('x');
                letterList.add('y');
                letterList.add('z');
                break;
            default:
                break;


        }
        return letterList;

    }
    //    private static char myGetChar(char ch, int i) {
//
//        if ('2' <= ch && ch <= '6') {
//            return (char) ('a' + (ch - '2') * 3 + i);
//        } else if (ch == '7') {
//            return (char) ('q' + i);
//        } else if (ch == '8') {
//            return (char) ('t' + i);
//        } else {
//            return (char) ('w' + i);
//        }
//    }

}
