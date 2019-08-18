package com.tencent;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by lynch on 2019-08-16. <br>
 * [编程题]安排机器
 * 时间限制：1秒
 * <p>
 * 空间限制：32768K
 * <p>
 * 小Q的公司最近接到m个任务, 第i个任务需要xi的时间去完成, 难度等级为yi。
 * 小Q拥有n台机器, 每台机器最长工作时间zi, 机器等级wi。
 * 对于一个任务,它只能交由一台机器来完成, 如果安排给它的机器的最长工作时间小于任务需要的时间, 则不能完成,如果完成这个任务将获得200 * xi + 3 * yi收益。
 * <p>
 * 对于一台机器,它一天只能完成一个任务, 如果它的机器等级小于安排给它的任务难度等级, 则不能完成。
 * <p>
 * 小Q想在今天尽可能的去完成任务, 即完成的任务数量最大。如果有多种安排方案,小Q还想找到收益最大的那个方案。小Q需要你来帮助他计算一下。
 * <p>
 * <p>
 * 输入描述:
 * 输入包括N + M + 1行,
 * 输入的第一行为两个正整数n和m(1 <= n, m <= 100000), 表示机器的数量和任务的数量。
 * 接下来n行,每行两个整数zi和wi(0 < zi < 1000, 0 <= wi <= 100), 表示每台机器的最大工作时间和机器等级。
 * 接下来的m行,每行两个整数xi和yi(0 < xi < 1000, 0 <= yi<= 100), 表示每个任务需要的完成时间和任务的难度等级。
 * <p>
 * 输出描述:
 * 输出两个整数, 分别表示最大能完成的任务数量和获取的收益。
 * <p>
 * 输入例子1:
 * 1 2
 * 100 3
 * 100 2
 * 100 1
 * <p>
 * 输出例子1:
 * 1 20006
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
