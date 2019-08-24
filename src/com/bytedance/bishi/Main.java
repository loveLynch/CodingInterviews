package com.bytedance.bishi;

import java.util.*;

/**
 * @author Lynch
 * @date 2019/8/19 10:56
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
    public static int count = 1;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int start = input.nextInt();
        int end = input.nextInt();
        Queue<Integer> original = new LinkedList<>();
        Set<Integer> storeNum = new HashSet<>();
        original.add(start);
        storeNum.add(start);
        if (start == end) {
            System.out.println(0);
        } else {

            System.out.println(getCount(original, storeNum, end));
        }


    }

    /**
     * BFS+剪枝
     *
     * @param original 当前层数据
     * @param storeNum 存储已经访问过的数据，用于剪枝
     * @param end      目标数据
     * @return
     */
    private static int getCount(Queue<Integer> original, Set<Integer> storeNum, int end) {
        Queue<Integer> next = new LinkedList<>(); //用于存储下一层数据
        while (!original.isEmpty()) {
            int start = original.poll();
            int reverseNum = reverseNum(start);
            if (!storeNum.contains(start - 1) && start - 1 > 0) {
                next.add(start - 1);
                storeNum.add(start - 1);

            }
            if (!storeNum.contains(start + 1)) {
                next.add(start + 1);
                storeNum.add(start + 1);


            }
            if (!storeNum.contains(reverseNum)) {
                next.add(reverseNum);
                storeNum.add(reverseNum);


            }
        }
        if (next.contains(end))
            return count;
        else {
            getCount(next, storeNum, end);
            count++;
        }
        return count;

    }


    /**
     * 翻转数据，正数
     *
     * @param start
     * @return
     */
    public static int reverseNum(int start) {
        if (start / 10 == 0)
            return start;
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