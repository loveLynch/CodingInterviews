package com.lynch;

import java.util.*;

/**
 * Created by lynch on 2019-03-21. <br>
 * 38.字符串的排列 ****
 * <p>
 * 输入一个字符串，打印出该字符串中字符的所有排列。如输入abc，则打印abc，acb，bac，bca，cab，cba。
 **/
public class StringPermutation_38 {
    /**
     * 把字符串分为两部分，一部分是字符串的第一个字符，另一部分是第一个字符之后的所有字符
     * 让第一个字符和后面所有字符逐个交互。交互完后，此时后面部分的字符串再分为两部分
     * 同上，递归
     *
     * @param strs
     */
    public static List<char[]> permutation_ONE(char[] strs) {
        if (strs == null || strs.length == 0)
            return null;
        List<char[]> ret = new LinkedList<>();
        permutationCore_ONE(strs, ret, 0);
        return ret;
    }
    //下标为bound的字符依次与[bound,length)的字符交换，如果相同不交换，直到最后一个元素为止。
    //如a,b,c
    //0与0交换,得a,b,c => 1与1交换,得a,b,c =>2与2交换,得a,b,c(存入)
    //                => 1与2交换，得a,c,b =>2与2交换,得a,c.b(存入)
    //0与1交换,得b,a,c => 1与1交换,得b,a,c =>2与2交换,得b,a,c(存入)
    //                => 1与2交换，得b,c,a =>2与2交换,得b,c,a(存入)
    //0与2交换,得c,b,a => 1与1交换,得c,b,a =>2与2交换,得c,b,a(存入)
    //                => 1与2交换，得c,a,b =>2与2交换,得c,a.b(存入)

    //如a,a,b
    //0与0交换,得a,a,b => 1与1交换,得a,a,b =>2与2交换,得a,a,b(存入)
    //                => 1与2交换，得a,b,a =>2与2交换,得a,b,a(存入)
    //0与1相同,跳过
    //0与2交换,得b,a,a =>2与2交换，得b,a,a(存入)
    public static void permutationCore_ONE(char[] strs, List<char[]> ret, int bound) {
        if (bound == strs.length)
            ret.add(Arrays.copyOf(strs, strs.length));
        Set<Character> set = new HashSet<>();
        for (int i = bound; i < strs.length; i++) {
            if (set.add(strs[i])) {
                swap(strs, bound, i);
                permutationCore_ONE(strs, ret, bound + 1);
                swap(strs, bound, i);
            }
        }
    }

    public static void swap(char[] strs, int x, int y) {
        char temp = strs[x];
        strs[x] = strs[y];
        strs[y] = temp;
    }

    private static void permutation_TWO(char[] s) {
        permutationCore_Two(s, 0, s.length - 1);

    }

    private static void permutationCore_Two(char[] s, int from, int to) {
        if (to <= 1)
            return;
        if (from == to) {
            System.out.println(s);
        } else {
            for (int i = from; i <= to; i++) {
                if (isNeedSwap(s, from, i)) {
                    swap(s, i, from); //交换前缀，使其产生下一个前缀
                    permutationCore_Two(s, from + 1, to);
                    swap(s, from, i); //将前缀换回，继续做上一个前缀的排列
                }
            }
        }
    }


    /*
     * 由于全排列就是从第一个数字起，每个数分别与它后面的数字交换，我们先尝试加个这样的判断——如果一个数与后面的数字相同那么这两个数就不交换 了。
     * 例如abb，第一个数与后面两个数交换得bab，bba。然后abb中第二个数和第三个数相同，就不用交换了。但是对bab，第二个数和第三个数不 同，则需要交换，
     * 得到bba。由于这里的bba和开始第一个数与第三个数交换的结果相同了，因此这个方法不行。
     * 换种思维，对abb，第一个数a与第二个数b交换得到bab，然后考虑第一个数与第三个数交换，此时由于第三个数等于第二个数，
     * 所以第一个数就不再用与第三个数交换了。再考虑bab，它的第二个数与第三个数交换可以解决bba。此时全排列生成完毕！
     */
    private static boolean isNeedSwap(char[] s, int from, int k) {
        boolean flag = true;
        for (int i = from; i < k; i++) {
            if (s[i] == s[k]) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        System.out.println("method ONE");
        char[] strs = {'a', 'b', 'c'};
        List<char[]> ret = permutation_ONE(strs);
        for (char[] item : ret) {
            for (int i = 0; i < item.length; i++)
                System.out.print(item[i]);
            System.out.println();
        }
        System.out.println();
        char[] strs2 = {'a', 'a', 'b', 'b'};
        List<char[]> ret2 = permutation_ONE(strs2);
        for (char[] item : ret2) {
            for (int i = 0; i < item.length; i++)
                System.out.print(item[i]);
            System.out.println();
        }

        System.out.println();
        System.out.println("method TWO");
        permutation_TWO(strs);
        System.out.println();
        permutation_TWO(strs2);
    }
}
