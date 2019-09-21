package com.tencent.bishi920;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-09-20. <br>
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = Integer.parseInt(input.nextLine());
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            int len = Integer.parseInt(input.nextLine());
            String str = input.nextLine();
            stringList.add(str);
        }
        List<String> results = getResult(stringList);
        for (String result : results) {
            System.out.println(result);
        }

    }

    private static List<String> getResult(List<String> stringList) {
        List<String> results = new ArrayList<>();
        for (String str : stringList) {
            if (str.length() < 11) {
                results.add("NO");
            } else if (!str.contains("8")) {
                results.add("NO");
            } else {
                int first8 = 0;
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == '8') {
                        first8 = i;
                        break;
                    }

                }
                if (str.substring(first8).length() < 11) {
                    results.add("NO");
                } else {
                    results.add("YES");
                }
            }
        }
        return results;
    }

}
