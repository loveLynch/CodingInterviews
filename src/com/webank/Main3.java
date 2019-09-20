package com.webank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-09-19. <br>
 **/
public class Main3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();//景点
        int m = input.nextInt();//路
        int[][] load = new int[m][2];
        for (int i = 0; i < m; i++) {
            load[i][0] = input.nextInt();
            load[i][1] = input.nextInt();
        }
        int in = input.nextInt();
        int out = input.nextInt();
        List<Integer> result = unGetTour(n, load, in, out);
        for (int t : result) {
            System.out.print(t + " ");
        }
    }

    private static List<Integer> unGetTour(int n, int[][] load, int in, int out) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = i + 1;
            result.add(num);
        }
        List<Integer>[] tour = new List[n + 1];
        for (int i = 0; i < load.length; i++) {
            int s = load[i][0];
            int t = load[i][1];
            if (tour[s] == null) {
                tour[s] = new ArrayList<>();
                tour[s].add(t);
            } else {
                tour[s].add(t);
            }
            if (tour[t] == null) {
                tour[t] = new ArrayList<>();
                tour[t].add(s);
            } else {
                tour[t].add(s);
            }
        }
        List<Integer> tourIn = tour[in];
        List<Integer> tourOut = tour[out];
        if (tourIn.contains(out) && tourOut.contains(in)) {
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i) == in) {
                    result.remove(in);
                }
                if (result.get(i) == out) {
                    result.remove(out);
                }
            }
        } else {
            tourIn.retainAll(tourOut);
            for (int i = 0; i < tourIn.size(); i++) {
                int num = tourIn.get(i);
                for (int j = 0; j < result.size(); j++) {
                    if (result.get(j) == num) {
                        result.remove(num);
                    }
                }
            }
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i) == in) {
                    result.remove(in);
                }
                if (result.get(i) == out) {
                    result.remove(out);
                }
            }


        }

        return result;
    }
}
