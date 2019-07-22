package com.niuke.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-06-29. <br>
 **/
public class FindMin {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String N = input.nextLine();
        List<String> numList = new ArrayList<>();
        for (int i = 0; i < Integer.valueOf(N); i++) {
            String nextLine = input.nextLine();
            numList.add(nextLine);
        }

        for (String singleNum : numList) {
            String[] single = singleNum.split(" ");
            List<Integer> integers = getNum(single, Integer.parseInt(single[0]));
            System.out.println(integers.get(0) + " " + integers.get(1));
        }
    }

    public static List<Integer> getNum(String[] numArray, int k) {
        List<Integer> result = new ArrayList<>();
        if (numArray == null || numArray.length == 0)
            return result;
        List<Integer> moleculeList = new ArrayList<>();
        List<Integer> denominatorList = new ArrayList<>();
        int length = numArray.length;

        while (length != 0) {
            for (int i = 1; i < length - 1; i++) {
                moleculeList.add(i);
                denominatorList.add(Integer.valueOf(numArray[length - 1]));

            }
            length--;

        }

        result.add(moleculeList.get(k));
        result.add(denominatorList.get(k));

        return result;


    }


}
