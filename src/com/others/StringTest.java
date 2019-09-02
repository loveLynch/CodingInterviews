package com.others;

/**
 * Created by lynch on 2019-09-02. <br>
 **/
public class StringTest {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = new String("abc");
        StringBuilder s3 = new StringBuilder("abc");
        StringBuilder s4 = new StringBuilder();
        s4.append("a");
        s4.append("b");
        s4.append("c");
        String s5 = "ab" + "c";
        char[] c = new char[]{'a', 'b', 'c'};
        String s6 = new String(c);
        String s70 = "ab";
        String s7 = s70 + "c";
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s3.toString());
        System.out.println(s1.equals(s3.toString()));
        System.out.println(s1 == s4.toString());
        System.out.println(s1.equals(s4.toString()));
        System.out.println(s1 == s5);
        System.out.println(s1.equals(s5));
        System.out.println(s1 == s6);
        System.out.println(s1.equals(s6));
        System.out.println(s1 == s7);
        System.out.println(s1.equals(s7));

    }
}
