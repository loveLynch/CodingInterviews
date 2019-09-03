package com.tencent.bishi91;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-01. <br>
 * 4 100
 * 3 4 5 4
 * <p>
 * <p>
 * 5
 * 如果我们只有一个人的话，那么需要sum(A)+n的时间完成任务。所以我们可以遍历数组A计算前缀和cnt，如果cnt+i>=x（i表示遍历到的A中元素位置），说明此时人手不够了，我们需要增加人手。当我们增加一个人手后，我们相应的消耗时间就要减少x-i（其中x表示我们的目标时间，在位置i，每个人有x-i时间搬物品）。当最后所需人手个数小于m或者人手恰好是m且前缀合cnt小于等于0的时候，说明在x时间内可行，否则不可行。
 * <p>
 * 接着就是二分法的常规套路，当我们发现当前的mid可行的时候，我们的r=mid，否则l=mid+1。
 * ————————————————
 * 版权声明：本文为CSDN博主「coordinate_blog」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_17550379/article/details/100355642
 **/
public class Main3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();//office
        int m = input.nextInt();//people
        int[] boxNum = new int[n];
        for (int i = 0; i < n; i++) {
            boxNum[i] = input.nextInt();

        }
        System.out.println(getMinTime(m, n, boxNum));
    }

    private static int getMinTime(int m, int n, int[] boxNum) {
        int minTime = 0;
        int sumBox = 0;
        for (int box : boxNum)
            sumBox += box;
        if (m > sumBox)
            return (n + 1);
        else {
            int left = 2;
            int right = n + sumBox;
            while (left < right) {
                int mid = (left + right) / 2;
                if (checkAdd(mid, n, m, boxNum)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            minTime = right;
        }
        return minTime;
    }

    private static boolean checkAdd(int mid, int n, int m, int[] boxNum) {
        int count = 0, people = 0;
        for (int i = 1; i < n + 1; i++) {
            count += boxNum[i - 1];
            if (i >= mid)
                return false;
            while (i + count > mid) {
                count -= mid - i;
                people++;
                if (people > m)
                    return false;
            }
        }
        if (people == m)
            return count <= 0;
        return true;

    }

}
