package com.bytedance.bishi;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-11. <br>
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int[][] clocks = new int[N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++)
                clocks[i][j] = input.nextInt();
        }
        int time = input.nextInt();
        int studyHour = input.nextInt();
        int studyMin = input.nextInt();
        int studyTime = studyHour * 60 + studyMin;
        int[] clockTime = new int[N];
        for (int i = 0; i < N; i++) {
            clockTime[i] = clocks[i][0] * 60 + clocks[i][1];
        }
        int[] clockHM = getClockTime(clockTime, time, studyTime);
        System.out.println(clockHM[0] + " " + clockHM[1]);

    }

    public static int[] getClockTime(int[] clockTime, int time, int studyTime) {
        int[] clockHM = new int[2];
        int clock = 0;
        for (int i = 0; i < clockTime.length; i++) {
            if (clockTime[i] + time <= studyTime)
                clock = Math.max(clock, clockTime[i]);

        }
        clockHM[0] = clock / 60;
        clockHM[1] = clock % 60;
        return clockHM;


    }
}
