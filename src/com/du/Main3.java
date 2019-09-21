package com.du;

import java.util.*;

/**
 * Created by lynch on 2019-09-21. <br>
 **/
public class Main3 {
    public static int count = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        int m = input.nextInt();
        input.nextLine();
        String from = input.nextLine();
        String to = input.nextLine();
        List<String> original = new ArrayList<>();
        original.add(from);
        if (from.equals(to)) {
            System.out.println(0);
        } else {
            getCountSolution(original, n, k, m, to, 0);
            System.out.println(count);
        }

    }

    /**
     * BFS
     *
     * @param original
     * @param n
     * @param k
     * @param m
     * @param to
     * @param level
     */
    private static void getCountSolution(List<String> original, int n, int k, int m, String to, int level) {
        if (level == k) {
            count = Collections.frequency(original, to);
            return;
        }
        List<String> next = new ArrayList<>(); //用于存储下一层数据
        Iterator it = original.iterator();

        while (it.hasNext()) {
            String start = (String) it.next();
            List<String> reverseList = reverseNum(n, m, start);
            if (level < k - 1) {
                reverseList.remove(to);
            }
            next.addAll(reverseList);


        }
        System.out.println(next);
        getCountSolution(next, n, k, m, to, level + 1);

    }

    public static List<String> reverseNum(int n, int m, String numStr) {
        List<String> reverseList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i + m <= n) {
                String temp = numStr;
                String first = temp.substring(0, i);
                String middle = temp.substring(i, i + m);
                StringBuilder reverseMiddle = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    int num = middle.charAt(j) - '0';
                    reverseMiddle.append(Math.abs(num - 1));
                }
                String last = temp.substring(i + m, n);
                String reverseNum = first + reverseMiddle.toString() + last;
                reverseList.add(reverseNum);
            } else {
                break;
            }

        }
        return reverseList;

    }

}
