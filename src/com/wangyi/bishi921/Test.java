package com.wangyi.bishi921;


/**
 * Created by lynch on 2019-09-21. <br>
 **/
public class Test {
    public static void main(String[] args) {
        for (int i = 1; i < 10; i += 2)
            System.out.println(i+" I " + (i & (-i)));
        for (int j = 0; j <= 10; j += 2)
            System.out.println(j+" O " + (j & (-j)));
        int a = 4;
        int b = 36;
        for (int k = 0; k < 32; k++) {
            int t = (b & 0x80000000 >>> k) >>> (31 - k);
            System.out.print(t);
        }
        System.out.println();
        int c = -36;
        for (int k = 0; k < 32; k++) {
            int t = (c & 0x80000000 >>> k) >>> (31 - k);
            System.out.print(t);
        }
        System.out.println();
        System.out.println(a & (-a));
        System.out.println(b & (-b));


    }
}
