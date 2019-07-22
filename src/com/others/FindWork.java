package com.others;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by lynch on 2019-06-28. <br>
 * floorKey(K key)
 * * 返回小于等于给定键的最大键；如果不存在这样的键，则返回 null。
 **/

public class FindWork {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int workNum = sc.nextInt();
        int manNum = sc.nextInt();
        int[][] arr = new int[workNum][2];
        int[] manPower = new int[manNum];
        for (int i = 0; i < workNum; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        for (int i = 0; i < manNum; i++) {
            manPower[i] = sc.nextInt();
        }
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int i = 1; i < workNum; i++) {
            arr[i][1] = Math.max(arr[i - 1][1], arr[i][1]);
        }
        TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
        for (int i = 0; i < workNum; i++) {
            tm.put(arr[i][0], arr[i][1]);
        }
        for (int i = 0; i < manNum; i++) {
            if (tm.floorKey(manPower[i]) != null) {
                System.out.println(tm.get(tm.floorKey(manPower[i])));
            } else {
                System.out.println(0);
            }
        }
    }
}