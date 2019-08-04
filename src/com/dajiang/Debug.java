package com.dajiang;


import java.util.Scanner;

/**
 * Created by lynch on 2019-08-04. <br>
 **/
public class Debug {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt(); //bug总数
        int A = input.nextInt(); //喝咖啡的增益
        int X = input.nextInt(); //喝咖啡的次数
        input.nextLine();
        int[] debugs = new int[N];
        for (int i = 0; i < N; i++)
            debugs[i] = input.nextInt();

        int time = 0;
        for (int debug : debugs)
            time += debug;
        if (time <= X * 60 * A) {
            int temp = time % A;
            time = time / A + (temp == 0 ? 0 : 1);
        } else {
            time = time - X * 60 * A + X * 60;
        }

        if (time <= 60 * 8)
            System.out.println(time);
        else {
            System.out.println(0);
        }
    }

//    private static void getDebugTime(int[] debugs, int debugIndex, int a, int x, int count) {
//        Arrays.sort(debugs);
//        if (x == 8) {
//            for (int i = debugs.length - 1; i >= debugs.length - 8; i--)
//                debugs[i] = debugs[i] / a;
//            return;
//        }
//        if (count == x)
//            return;
//        debugs[debugIndex] = debugs[debugIndex] / a;
//        if (debugs[debugIndex] < 60)
//            debugIndex--;
//        getDebugTime(debugs, debugIndex, a, x, count + 1);
//
//    }
}
