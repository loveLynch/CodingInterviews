package com.tencent;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by lynch on 2019-08-16. <br>
 **/
public class MaxTaskAndProfit {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();//机器数量
        int m = input.nextInt();//任务数量
        //机器时间和等级
        int[][] machine = new int[n][2];
        for (int i = 0; i < n; i++) {
            machine[i][0] = input.nextInt();
            machine[i][1] = input.nextInt();
        }
        //任务完成时间和等级
        int[][] task = new int[m][2];
        for (int i = 0; i < m; i++) {
            task[i][0] = input.nextInt();
            task[i][1] = input.nextInt();
        }


//对于这两个二维数组进行排序，降序排列，先按时间排序，如果时间相同的话就按等级排序
        Arrays.sort(machine, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] - o2[0] == 0) {
                    return o2[1] - o1[1];
                }
                return o2[0] - o1[0];
            }
        });
        Arrays.sort(task, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] - o2[0] == 0) {
                    return o2[1] - o1[1];
                }
                return o2[0] - o1[0];
            }
        });
        long sum = 0;
        int count = 0;
        int j = 0;
        int[] dp = new int[101];
        for (int i = 0; i < task.length; i++) {
            while (j < machine.length && machine[j][0] >= task[i][0]) {
                dp[machine[j][1]]++;
                j++;
            }
            for (int k = task[i][1]; k < 101; k++) {
                if (dp[k] != 0) {
                    dp[k]--;
                    sum += 200 * task[i][0] + 3 * task[i][1];
                    count++;
                    break;
                }
            }
        }
        System.out.println(count + " " + sum);
    }
}
