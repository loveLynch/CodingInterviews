package com.leetcode;


import java.util.Scanner;

/**
 * Created by lynch on 2019-08-30. <br>
 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
 * <p>
 * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
 * <p>
 * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
 * <p>
 * 示例 1:
 * <p>
 * 输入: row = [0, 2, 1, 3]
 * 输出: 1
 * 解释: 我们只需要交换row[1]和row[2]的位置即可。
 * 示例 2:
 * <p>
 * 输入: row = [3, 2, 0, 1]
 * 输出: 0
 * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/couples-holding-hands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class CoupleHoldingHands {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] couples = new int[n];
        for (int i = 0; i < n; i++) {
            couples[i] = input.nextInt();
        }
        System.out.println(minSwapsCouples1(couples.clone()));
        System.out.println(minSwapsCouples2(couples.clone()));
    }

    /**
     * 常规逻辑求解
     *
     * @param row
     * @return
     */
    public static int minSwapsCouples2(int[] row) {
        int swap = 0;
        for (int i = 0; i < row.length - 1; i = i + 2) {
            if (((row[i] & 1) == 0 && row[i + 1] != row[i] + 1) || ((row[i] & 1) != 0 && row[i + 1] != row[i] - 1)) {
                if ((row[i] & 1) == 0) {
                    for (int j = i + 2; j < row.length; j++) {
                        if (row[j] == row[i] + 1) {
                            int t = row[i + 1];
                            row[i + 1] = row[j];
                            row[j] = t;
                            swap++;
                            break;
                        }
                    }
                } else {
                    for (int j = i + 2; j < row.length; j++) {
                        if (row[j] == row[i] - 1) {
                            int t = row[i + 1];
                            row[i + 1] = row[j];
                            row[j] = t;
                            swap++;
                            break;
                        }
                    }
                }

            }
        }
        return swap;
    }

    public static int minSwapsCouples1(int[] row) {
        int count = 0;
        for (int i = 0; i < row.length; i = i + 2) {
            int p1 = row[i];
            int p2 = findAnother(p1);

            if (row[i + 1] != p2) {
                int j = getIndexByValue(row,p2);
                int temp = row[i + 1];
                row[i + 1] = row[j];
                row[j] = temp;
                count++;
            }
        }
        return count;
    }

    //每两个座位成一对，假定左边的人都是合法的不变，如果TA右边的人与TA匹配则
    // 跳过，不匹配则找到TA的匹配对象的与TA右边的人交换。
    public static int findAnother(int n) {
        if (n % 2 == 0)
            return n + 1;
        else {
            return n - 1;
        }
    }

    public static int getIndexByValue(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;//当if条件不成立时，默认返回一个负数值-1
    }

}
