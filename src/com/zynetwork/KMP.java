package com.zynetwork;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-06. <br>
 * KMP字符串模式匹配算法
 * MP算法是当遇到不匹配字符时，不是简单的向后移一位字符，而是根据前面已匹配的字符数和模式串前缀和后缀的最大相同字符串长度数组next的元素来确定向后移动的位数，
 * 所以KMP算法的时间复杂度比朴素算法的要少，并且是线性时间复杂度，即预处理时间复杂度是O(m)，匹配时间复杂度是O(n)。
 * <p>
 * next数组含义：代表在模式串P中，当前下标对应的字符之前的字符串中，有多大长度的相同前缀后缀。
 * 例如如果next [j] = k，代表在模式串P中，下标为j的字符之前的字符串中有最大长度为k 的相同前缀后缀。
 * KMP算法的核心就是求next数组，在字符串匹配的过程中，一旦某个字符匹配不成功，
 * next数组就会指导模式串P到底该相对于S右移多少位再进行下一次匹配，从而避免无效的匹配。
 * <p>
 * next数组求解方法：
 * <p>
 * next[0] = -1。
 * 如果已知next[j] = k,如何求出next[j+1]呢?具体算法如下:
 * 1. 如果p[j] = p[k], 则next[j+1] = next[k] + 1;
 * 2.如果p[j] != p[k], 则令k=next[k],如果此时p[j]==p[k],则next[j+1]=k+1,
 * 如果不相等,则继续递归前缀索引,令 k=next[k],继续判断,直至k=-1(即k=next[0])或者p[j]=p[k]为止
 **/
public class KMP {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String source = input.nextLine();
        String pattern = input.nextLine();
        System.out.println(indexOf(source, pattern));

    }

    /**
     * 获取KMP算法中pattern字符串对应的next数组
     *
     * @param p 模式字符串对应的字符数组
     * @return
     */
    public static int[] getNext(char[] p) {
        // 已知next[j] = k,利用递归的思想求出next[j+1]的值
        // 如果已知next[j] = k,如何求出next[j+1]呢?具体算法如下:
        // 1. 如果p[j] = p[k], 则next[j+1] = next[k] + 1;
        // 2. 如果p[j] != p[k], 则令k=next[k],如果此时p[j]==p[k],则next[j+1]=k+1,
        // 如果不相等,则继续递归前缀索引,令 k=next[k],继续判断,直至k=-1(即k=next[0])或者p[j]=p[k]为止
        int pLen = p.length;
        int[] next = new int[pLen];
        int k = -1;
        int j = 0;
        next[0] = -1; // next数组中next[0]为-1
        while (j < pLen - 1) {
            if (k == -1 || p[j] == p[k]) {
                k++;
                j++;
                // 修改next数组求法
                if (p[j] != p[k]) {
                    next[j] = k;// KMPStringMatcher中只有这一行
                } else {
                    // 不能出现p[j] = p[next[j]],所以如果出现这种情况则继续递归,如 k = next[k],
                    // k = next[[next[k]]
                    next[j] = next[k];
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * 从原字符串中查找模式字符串的位置,如果模式字符串存在,则返回模式字符串第一次出现的位置,否则返回-1
     *
     * @param source  原字符串
     * @param pattern 模式字符串
     * @param source
     * @param pattern
     * @return
     */
    public static int indexOf(String source, String pattern) {
        int i = 0, j = 0;
        char[] src = source.toCharArray();
        char[] ptn = pattern.toCharArray();
        int sLen = src.length;
        int pLen = ptn.length;
        int[] next = getNext(ptn);
        for (int s = 0; s < next.length; s++)
            System.out.print(next[s] + " ");
        System.out.println();
        while (i < sLen && j < pLen) {
            // 如果j = -1,或者当前字符匹配成功(src[i] = ptn[j]),都让i++,j++
            if (j == -1 || src[i] == ptn[j]) {
                i++;
                j++;
            } else {
                // 如果j!=-1且当前字符匹配失败,则令i不变,j=next[j],即让pattern模式串右移j-next[j]个单位
                j = next[j];
            }
        }
        if (j == pLen)
            return i - j;
        return -1;
    }
}
