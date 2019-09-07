package com.zynetwork;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-06. <br>
 **/
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println(getCountReward1(n));
        System.out.println(getCountReward2(n));
    }

    private static int getCountReward1(int n) {
        int noendB1C = 1;//统计前i-1个月，合格且有1个C,不以B结尾
        int noCenb1B = 1; //统计前i-1个月，合格且一个B结尾没有C
        int noCenb2B = 0; //统计前i-1个月，合格且两个B结尾没有C
        int hasCenb1B = 0; //统计前i-1个月，合格且一个B结尾有C
        int hasCenb2B = 0; //统计前i-1个月，合格且两个B结尾有C
        int noCNoendB = 1;//统计前i-1个月，合格且不以B没有C
        for (int i = 1; i < n; i++) {
            int countNoendB1C = noendB1C + noCenb1B + noCenb2B + noCNoendB;//统计i个月，合格且有1个C,不以B结尾
            int countHasCenb1B = noendB1C; //统计i个月，合格且一个B结尾
            int countHasCenb2B = hasCenb1B; //统计i个月，合格且两个B结尾
            int countNoCNoendB = noCenb1B + noCenb2B + noCNoendB; //统计i个月，合格且不以B结尾无C
            int countNoCenb1B = noCNoendB;
            int countNoCenb2B = noCenb1B;

            noendB1C = countNoendB1C;
            noCenb1B = countNoCenb1B;
            noCenb2B = countNoCenb2B;
            hasCenb1B = countHasCenb1B;
            hasCenb2B = countHasCenb2B;
            noCNoendB = countNoCNoendB;
        }
        return noendB1C + noCenb1B + noCenb2B + hasCenb1B + hasCenb2B + noCNoendB;
    }

    /**
     * 满足奖励条件的情况可以分解为如下6种不重叠情况个数的和：
     * <p>
     * a 不含C的BB结尾;
     * b 不含C的B结尾, 且倒数第二位不为B;
     * c 不含C的非B结尾;
     * d 含C的BB结尾;
     * e 含C的B结尾，且倒数第二位不为B;
     * f 含C的非B结尾;
     * <p>
     * 作者：lycao
     * 链接：https://leetcode-cn.com/problems/student-attendance-record-ii/solution/jian-ming-jie-fa-by-lycao/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    private static int getCountReward2(int n) {
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
