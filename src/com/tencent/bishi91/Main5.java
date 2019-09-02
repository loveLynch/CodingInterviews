package com.tencent.bishi91;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-09-01. <br>
 * 3 2
 * 1 3
 * 2 3
 * 4 4
 * <p>
 * <p>
 * 6
 * 5
 * 5
 **/
public class Main5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        int k = input.nextInt();
        int[][] locationLength = new int[t][];
        for (int i = 0; i < t; i++) {
            locationLength[i][0] = input.nextInt();
            locationLength[i][1] = input.nextInt();

        }

        List<Integer> results = getSolution(locationLength, k);
        for (int result : results)
            System.out.println(result);

    }

    private static List<Integer> getSolution(int[][] locationLength, int k) {
        List<Integer> results = new ArrayList<>();
        return results;
    }
}
