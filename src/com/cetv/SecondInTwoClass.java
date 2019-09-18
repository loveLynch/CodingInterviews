package com.cetv;


import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by lynch on 2019-09-05. <br>
 **/
public class SecondInTwoClass {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int c1 = input.nextInt();
        int c2 = input.nextInt();
        int[] class1 = new int[c1];
        int[] class2 = new int[c2];
        for (int i = 0; i < c1; i++) {
            class1[i] = input.nextInt();
        }
        for (int i = 0; i < c2; i++) {
            class2[i] = input.nextInt();
        }
        System.out.println(getSecondStu(class1, class2));

    }

    private static int getSecondStu(int[] class1, int[] class2) {
        TreeSet<Integer> class1Set = new TreeSet<>();
        TreeSet<Integer> class2Set = new TreeSet<>();
        for (int c1 : class1)
            class1Set.add(c1);
        for (int c2 : class2)
            class2Set.add(c2);
        int c1First = class1Set.pollLast();
        int c2First = class2Set.pollLast();
        if (c1First == c2First) {
            if (class1Set.size() == 0 && class2Set.size() == 0) {
                return -1;
            } else if (class1Set.size() > 0 && class2Set.size() == 0) {
                return class2Set.pollLast();
            } else if (class1Set.size() == 0 && class2Set.size() > 0) {
                return class1Set.pollLast();
            } else {
                int c1Second = class1Set.pollLast();
                int c2Second = class2Set.pollLast();
                return c1Second > c1Second ? c2Second : c1Second;
            }
        } else {
            return c1First > c2First ? c2First : c1First;
        }


    }
}
