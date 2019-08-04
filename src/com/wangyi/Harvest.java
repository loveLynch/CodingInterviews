package com.wangyi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Lynch
 * @date 2019/8/2 14:41
 * [编程题]丰收
 * 时间限制：1秒
 * <p>
 * 空间限制：262144K
 * <p>
 * 又到了丰收的季节，恰逢小易去牛牛的果园里游玩。
 * 牛牛常说他对整个果园的每个地方都了如指掌，小易不太相信，所以他想考考牛牛。
 * 在果园里有N堆苹果，每堆苹果的数量为ai，小易希望知道从左往右数第x个苹果是属于哪一堆的。
 * 牛牛觉得这个问题太简单，所以希望你来替他回答。
 * <p>
 * 输入描述:
 * 第一行一个数n(1 <= n <= 105)。
 * 第二行n个数ai(1 <= ai <= 1000)，表示从左往右数第i堆有多少苹果
 * 第三行一个数m(1 <= m <= 105)，表示有m次询问。
 * 第四行m个数qi，表示小易希望知道第qi个苹果属于哪一堆。
 * <p>
 * 输出描述:
 * m行，第i行输出第qi个苹果属于哪一堆。
 */
public class Harvest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = Integer.parseInt(input.nextLine());
        int[] leap = new int[N];
        for (int i = 0; i < N; i++) {
            leap[i] = input.nextInt();
        }
        input.nextLine();
        int M = Integer.parseInt(input.nextLine());
        int[] ask = new int[M];
        for (int j = 0; j < M; j++) {
            ask[j] = input.nextInt();
        }

        List<Integer> locationList = getLocation2(leap, ask);
        for (int location : locationList)
            System.out.println(location);
    }

    /**
     * 时间复杂度过高
     *
     * @param leap
     * @param ask
     * @return
     */
    private static List<Integer> getLocation1(int[] leap, int[] ask) {
        List<Integer> locationList = new ArrayList<>();
        int[] leapEdge = new int[leap.length];
        leapEdge[0] = leap[0];
        int askIndex = 0;
        int leapIndex = 1;
        while (askIndex < ask.length) {
            if (ask[askIndex] <= leapEdge[0]) {
                locationList.add(1);
                askIndex++;
            } else {
                leapEdge[leapIndex] = leapEdge[leapIndex - 1] + leap[leapIndex]; //注意先算一次，leapIndex从1开始的
                if (ask[askIndex] > leapEdge[leapIndex]) {
                    leapIndex++;
                } else if (ask[askIndex] <= leapEdge[leapIndex - 1]) {
                    leapIndex--;
                } else {
                    locationList.add(leapIndex + 1);
                    askIndex++;
                }
            }
        }

        return locationList;
    }

    /**
     * 二分法
     *
     * @param leap
     * @param asks
     * @return
     */
    private static List<Integer> getLocation2(int[] leap, int[] asks) {
        List<Integer> locationList = new ArrayList<>();
        int[] leapEdge = new int[leap.length];
        leapEdge[0] = leap[0];
        for (int i = 1; i < leap.length; i++) {
            leapEdge[i] = leapEdge[i - 1] + leap[i];
        }
        for (int ask : asks) {
            int left = 0, right = leap.length - 1;
            while (left < right) {
                int mid = (left + right) >> 1; //二分法除2注意使用位运算，能大幅度降低时间复杂度
                if (leapEdge[mid] < ask) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            locationList.add(right + 1);
        }

        return locationList;
    }
}
