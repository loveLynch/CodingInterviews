package com.wangyi.bishi921;

/**
 * Created by lynch on 2019-09-21. <br>
 **/
public class Test {
    public static void main(String[] args) {
        int i = 3;
        i -= i & (-i);
        System.out.println(i);
        int j = 4;
        j += j & (-j);
        System.out.println(j);
        int a=1;
        int b=2;
        System.out.println(a&(-a));
        System.out.println(b&(-b));

    }
}
