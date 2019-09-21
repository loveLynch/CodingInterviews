package com.wangyi.bishi921;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-21. <br>
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int[] num = new int[4];
            for (int j = 0; j < 4; j++) {
                num[j] = input.nextInt();
            }
            System.out.println(getResult(num));
        }

    }

    /**
     * 每次操作可以进行
     * A->A+p或p->p*q
     * 经过几次操作使得B<=A
     *
     * @param num
     * @return
     */
    private static int getResult(int[] num) {
        int A = num[0];
        int B = num[1];
        int p = num[2];
        int q = num[3];
        int C = B - A;
        int result = (C + p - 1) / p;
        for (int i = 1; i < result; i++) {
            if (p + A >= B) {
                result = i;
                break;
            }
            if ((C + p - 1) / p <= q) {
                p = C;
            } else {
                p = p * q;
            }
        }
        return result;
    }
}
