package com.tencent.bishi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-08-17. <br>
 * input
 * 6
 * 5 3 8 3 2 5
 * output
 * 3 3 5 4 4 4
 **/
public class Main4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int high[] = new int[n];
        for (int i = 0; i < n; i++) {
            high[i] = input.nextInt();
        }
        List<Integer> result = getNumOfFloor(high);
        for (int r : result)
            System.out.print(r + " ");
    }

    private static List<Integer> getNumOfFloor(int[] high) {
        List<Integer> result = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < high.length; i++) {
            int count = 1;
            if (i + 1 < high.length) {
                count++;
                max = high[i + 1];
            }
            for (int j = i + 1; j < high.length; j++) {
                int right = high[i + 1];
                if (max < high[j]) {
                    max = high[j];
                }
                if (j > i + 1 && high[j] >= right && high[j] >= max)
                    count++;
            }
            if (i - 1 >= 0) {
                count++;
                max = high[i - 1];
            }
            for (int j = i - 1; j >= 0; j--) {
                if (high[j] > max) {
                    max = high[j];
                }
                int left = high[i - 1];
                if (j < i - 1 && high[j] >= left && high[j] >= max)
                    count++;
            }
            result.add(count);
        }
        return result;
    }
}
