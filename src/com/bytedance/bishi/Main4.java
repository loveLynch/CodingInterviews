package com.bytedance.bishi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-08-11. <br>
 **/
public class Main4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int[][] location = new int[N - 1][2];
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < 2; j++)
                location[i][j] = input.nextInt();
        }
        List<Integer> result = getCount(location);
        for (int yu : result)
            System.out.print(yu + " ");

    }

    public static List<Integer> getCount(int[][] location) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        result.add(2);
        result.add(2);
        return result;
    }
}
