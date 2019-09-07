package com.zynetwork;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-06. <br>
 * Sunday算法的核心在于通过跳过尽可能多的字符来提高匹配效率，此处如何跳过尽可能多的字符串是本算法的核心思想。
 **/
public class Sunday {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String source = input.nextLine();
        String pattern = input.nextLine();
        System.out.println(sunday(source, pattern));

    }

    public static String sunday(String sourse, String target) {
        String retVal = "";
        char[] tempS = sourse.toCharArray();
        char[] tempT = target.toCharArray();
        int k = 0;
        int j = 0;
        if (compare(tempS, tempT, k, j)) {
            retVal = "查找成功";
        } else {
            retVal = "查找完成，未找到";
        }
        return retVal;
    }

    /**
     * 对比主过程(递归调用)
     *
     * @param tempS 源字符串
     * @param tempT 目标字符串
     * @param j     本次比较源字符串初始下标
     * @param k     同j
     * @return
     */
    public static boolean compare(char[] tempS, char[] tempT, int j, int k) {
        System.out.println("比较开始下标：" + k + "  " + j);
        for (int i = j; i < j + tempT.length; i++) {
            if (tempT[i - j] == tempS[i]) {
                k++;
                continue;
            } else {
                break;
            }
        }
        //k-j代表本次比较的次数，如果和目标字符串的长度相等，则说明每个字符都对比成功，即在源字符串中找到了目标字符串
        if (k - j == tempT.length) {
            System.out.println("匹配成功");
            return true;
        }
        k = j + tempT.length;
        if (k < (tempS.length - 1)) {
            int value = check(tempS[k], tempT);
            int step = -value;
            j = k + step;
            return compare(tempS, tempT, j, j);
        } else {
            return false;
        }
    }

    /**
     * 检查目标字符串tempT是否包含c
     *
     * @param c
     * @param tempT
     * @return
     */
    public static int check(char c, char[] tempT) {
        for (int i = tempT.length - 1; i >= -1; i--) {
            if (i == -1 || tempT[i] == c) {
                return i;
            } else {
                continue;
            }
        }
        return 0;
    }
}
