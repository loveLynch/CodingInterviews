package com.tencent.bishi91;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by lynch on 2019-09-01. <br>
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[][] people = new int[n][2];
        for (int i = 0; i < n; i++) {
            people[i][0] = input.nextInt();
            people[i][1] = input.nextInt();
        }
        System.out.println(getUnsatifiy(people, n));
    }

    private static long getUnsatifiy(int[][] people, int n) {
        int[][] nums = new int[people.length][4];
        for (int i = 0; i < people.length; i++) {
            nums[i][0] = people[i][0] - people[i][1];
            nums[i][1] = i;
            nums[i][2] = people[i][0];
            nums[i][3] = people[i][1];
        }
        long result = 0;
        Arrays.sort(nums, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        for (int i = 0; i < people.length; i++) {
            result += (long)nums[i][2] * i + (long)nums[i][3] * (n - i - 1);
        }
        return result;

    }
}
