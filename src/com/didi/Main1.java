package com.didi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-09-19. <br>
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[][] bags = new int[m][2];
        for (int i = 0; i < m; i++) {
            bags[i][0] = input.nextInt();
            bags[i][1] = input.nextInt();
        }
        System.out.println(getSolution(bags, n));
    }

    private static int getSolution(int[][] bags, int n) {
        List<Integer>[] bagList = new List[n];
        for (int i = 0; i < bags.length; i++) {
            int bag1 = bags[i][0] - 1;
            int bag2 = bags[i][1] - 1;
            if (bagList[bag1] == null) {
                bagList[bag1] = new ArrayList<>();
                bagList[bag1].add(bag2);
            } else {
                bagList[bag1].add(bag2);
            }
            if (bagList[bag2] == null) {
                bagList[bag2] = new ArrayList<>();
                bagList[bag2].add(bag1);
            } else {
                bagList[bag2].add(bag1);
            }
        }
        int result = 0;
        int size = 0;
        for (int i = 0; i < n; i++) {
            if (bagList[i] != null) {
                size = Math.max(size, bagList[i].size());
                if (size == n - 1) {
                    result = 2;
                    break;
                } else {
                    result = ((n - size) / 2 + 1) * 2;
                }
            }

        }
        return result;
    }
}
