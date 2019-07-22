package com.lynch;

/**
 * Created by lynch on 2019-04-02. <br>
 * 57.2.和为s的连续正数序列
 * <p>
 * 输入一个整数s，打印所有和为s的连续正数序列(至少两个)。
 * 如，输入15，由于1+2+3+4+5=4+5+6=7+8=15，所以打印出三个连续序列1~5,4~6,7~8。
 **/
public class TwoNumbersWithSum_57_2 {

    /**
     * 使用两个指针small，big，值分别为1，2。
     * 如果从small加到big的和等于s，即找到了一组解，然后让big后移，继续求解。
     * 如果和小于s，big后移，如果和大于s，small前移。直到small大于s/2停止
     *
     * @param sum
     */
    public static void findContinuousSequence(int sum) {
        if (sum < 3)
            return;
        int small = 1, big = 2, middle = sum >> 1;
        int curSum = small + big;
        while (small <= middle) {
            if (curSum == sum) {
                printContinousSequence(small, big);
                big++;
                curSum += big;
            } else if (curSum < sum) {
                big++;
                curSum += big;
            } else {
                curSum -= small;
                small++;
            }
        }
    }

    public static void printContinousSequence(int small, int big) {
        for (int i = small; i <= big; i++) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        findContinuousSequence(15);
    }
}
