package com.leetcode;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-07. <br>
 * 给定一个正整数 n，返回长度为 n 的所有可被视为可奖励的出勤记录的数量。 答案可能非常大，你只需返回结果mod 109 + 7的值。
 * <p>
 * 学生出勤记录是只包含以下三个字符的字符串：
 * <p>
 * 'A' : Absent，缺勤
 * 'L' : Late，迟到
 * 'P' : Present，到场
 * 如果记录不包含多于一个'A'（缺勤）或超过两个连续的'L'（迟到），则该记录被视为可奖励的。
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 2
 * 输出: 8
 * 解释：
 * 有8个长度为2的记录将被视为可奖励：
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * 只有"AA"不会被视为可奖励，因为缺勤次数超过一次。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/student-attendance-record-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class StudentAttendance2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println(checkRecord(n));
    }

    /**
     * 满足奖励条件的情况可以分解为如下6种不重叠情况个数的和：
     * <p>
     * a 不含A的LL结尾;
     * b 不含A的L结尾, 且倒数第二位不为L;
     * c 不含A的非L结尾;
     * d 含A的LL结尾;
     * e 含A的L结尾，且倒数第二位不为L;
     * f 含A的非L结尾;
     *
     * @param n
     * @return
     */
    public static int checkRecord(int n) {
        long a = 0, b = 1, c = 1, d = 0, e = 0, f = 1;
        for (int i = 0; i < n; i++) {
            long countA = b;
            long CountB = c;
            long CountC = (a + b + c) % 1000000007;
            long CountD = e;
            long CountE = f;
            long CountF = (d + e + f + CountC) % 1000000007;
            a = countA;
            b = CountB;
            c = CountC;
            d = CountD;
            e = CountE;
            f = CountF;
        }

        return (int) f;
    }
}
