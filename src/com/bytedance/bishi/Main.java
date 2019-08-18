package com.bytedance.bishi;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-18. <br>
 * 数字变换
 * 给定一个8位整数x（这个8位整数高位可以是0），对于x我们允许如下3种变换操作
 * （1）翻转操作，比如12345678 -> 87654321，00123456 -> 65432100
 * （2）加一操作，比如12345678 -> 12345679
 * （3）减一操作，比如12345678 -> 12345677
 * 问题：
 * 输入两个8位整数start和end，输出通过最少多少次上述变换操作，可以将start变换为end
 * 输入描述
 * 输入两个8位整数start和end
 * 输出描述
 * 输出通过最少多少次上述变换操作，可以将start变换为end
 * 示例1
 * 输入
 * 12345678 87654322
 * 输出
 * 2
 * 说明
 * 12345678可以通过一次翻转 再加上一两次变换，就可以得到87654322
 **/
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int start = input.nextInt();
        int end = input.nextInt();
        System.out.println(reverseNum(start));
        System.out.println(reverseNum(end));
        System.out.println(getCount(start, end));


    }

    private static int getCount(int start, int end) {
        int count = 0;
        int length = Math.abs(end - start) + 1;
        if (length == 1)
            return count;
        int[] dp = new int[length];


        return count;

    }

    public static int reverseNum(int start) {
        StringBuilder str = new StringBuilder(String.valueOf(start));
        String reverse = str.reverse().toString();
        int count = 0;
        for (int i = 0; i < reverse.length(); i++) {
            if (reverse.charAt(i) == '0')
                count++;
            else {
                break;
            }

        }
        return Integer.parseInt(reverse.substring(count));
    }
}
