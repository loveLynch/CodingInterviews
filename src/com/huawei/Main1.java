package com.huawei;

import java.util.*;

/**
 * Created by lynch on 2019-04-10. <br>
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        String str = in.nextLine();
        String[] strArray = str.split(" ");
        int N = Integer.parseInt(strArray[0]);
        for (int i = 1; i <= N; i++)
            list.add(strArray[i]);
        List<String> outList = splitCombine(list);
        List<String> addZeroList = addAndZero(outList);
        Collections.sort(addZeroList);
        for (String otstr : addZeroList) {
            System.out.print(otstr.trim() + "\t");
        }

//        print(addZeroList);
    }

    public static List<String> splitCombine(List<String> list) {
        List<String> outList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int length = list.get(i).length();
            if (length > 100)
                return null;
            if (length > 8) {
                int count = length / 8;
                String theStr = list.get(i);
                int strLength = theStr.length();
                for (int j = 0; j < count * 8; j += 8) {
                    outList.add(theStr.substring(j, j + 8));
                }
                outList.add(theStr.substring(count * 8, strLength));
            } else {
                outList.add(list.get(i));
            }
        }
        return outList;

    }

    public static List<String> addAndZero(List<String> list) {
        List<String> outList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder(list.get(i));
            if (list.get(i).length() < 8 && list.get(i).length() > 0) {
                for (int j = 0; j < 8 - list.get(i).length(); j++) {
                    stringBuilder.append("0");
                }
                outList.add(String.valueOf(stringBuilder));
            } else {
                outList.add(list.get(i));
            }


        }
        return outList;

    }

    public static void print(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                String temp = list.get(i);
                list.set(i, list.get(i + 1));
                list.set(i + 1, temp);
            }
            System.out.print(list.get(i) + " ");
        }
        System.out.print(list.get(list.size() - 1));


    }

}
