package com.tencent.bishi;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-17. <br>
 **/
public class Main5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int work[] = new int[n];
        int sport[] = new int[n];
        for (int i = 0; i < n; i++) {
            work[i] = input.nextInt();
        }
        for (int i = 0; i < n; i++) {
            sport[i] = input.nextInt();
        }
        System.out.println(getRelaxDay(work, sport));
    }

    private static int getRelaxDay(int[] work, int[] sport) {
        int result = 0;
        for (int i = 0; i < work.length;) {
            if (work[i] == 0 && sport[i] == 0) {
                result++;
                i++;
            } else if (work[i] == 0 && sport[i] == 1) {
                if (i < work.length - 1)
                    sport[++i] = 0;
            } else if (work[i] == 1 && sport[i] == 0) {
                if (i < work.length - 1)
                    work[++i] = 0;
            } else {
                int j = -1;
                if (i > 0 && work[i - 1] == 1)
                    j = 0;
                if (i > 0 && sport[i - 1] == 1)
                    j = 1;
                while (i < work.length && work[i] == 1 && sport[i] == 1)
                    i++;
                if (i < work.length && work[i] == 1 && j == 0)
                    work[i] = 0;
                else if (i < work.length && sport[i] == 1 && j == 1)
                    sport[i] = 0;
            }
        }
        return result;

    }
}
