package com.tencent.bishi91;


import java.util.*;

/**
 * @author JayZ
 * @date 2019/9/1 20:15
 */
public class Main {

    private static class CTemp {
        int diff;
        int index;
        int a;
        int b;

        CTemp(int diff, int index, int a, int b) {
            this.diff = diff;
            this.index = index;
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int A;
        int B;
        ArrayList<CTemp> C = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A = scanner.nextInt();
            B = scanner.nextInt();
            CTemp temp = new CTemp(A - B, i, A, B);
            C.add(temp);
        }

        Collections.sort(C, new Comparator<CTemp>() {
            @Override
            public int compare(CTemp o1, CTemp o2) {
                return o2.diff - o1.diff;
            }
        });

        long sum = 0;
        int cnt = 1;
        for (CTemp temp: C) {
            sum += (long)temp.a*(cnt - 1) + (long)temp.b*(n - cnt);
            cnt++;
        }
        System.out.println(sum);
    }

}