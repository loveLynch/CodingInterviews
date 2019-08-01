package com.pdd;


import java.util.Scanner;

/**
 * Created by lynch on 2019-07-28. <br>
 **/
public class Main4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Integer N = Integer.valueOf(input.nextLine());
        String lengthLine = input.nextLine();
        String weightLine = input.nextLine();
        String[] lengthArrayStr = lengthLine.split(" ");
        String[] weightArrayStr = weightLine.split(" ");
        int[] lengthArray = new int[N];
        int[] weightArray = new int[N];
        for (int i = 0; i < N; i++) {
            lengthArray[i] = Integer.valueOf(lengthArrayStr[i]);
            weightArray[i] = Integer.valueOf(weightArrayStr[i]);
        }
//        List<Integer> lengthList = new ArrayList<>();
//        List<Integer> weightList = new ArrayList<>();
//        for (int i = 0; i < N; i++) {
//            lengthList.add(Integer.valueOf(lengthArrayStr[i]));
//            weightList.add(Integer.valueOf(weightArrayStr[i]));
//        }
        System.out.println(getMaxTower(lengthArray, weightArray));
    }

    private static int[] p;//物品的价值数组
    private static int[] w;//物品的重量数组
    private static int c;//最大可以拿的重量
    private static int count;//物品的个数

    private static int cw;//当前的重量
    private static int cp;//当前的价值
    static int bestp;//目前最优装载的价值
    private static int r;//剩余物品的价值

    private static int[] cx;//存放当前解
    private static int[] bestx;//存放最终解


    public static int Loading(int[] ww, int[] pp, int cc) {
        //初始化数据成员，数组下标从1开始
        count = ww.length - 1;
        w = ww;
        p = pp;
        c = cc;
        cw = 0;
        bestp = 0;
        cx = new int[count + 1];
        bestx = new int[count + 1];

        //初始化r，即剩余最大价格
        for (int i = 1; i <= count; i++) {
            r += p[i];
        }

        //调用回溯法计算
        BackTrack(1);
        return bestp;
    }

    /**
     * 回溯
     *
     * @param t
     */
    public static void BackTrack(int t) {
        if (t > count) {//到达叶结点
            if (cp > bestp) {
                for (int i = 1; i <= count; i++) {
                    bestx[i] = cx[i];
                }

                bestp = cp;
            }
            return;
        }

        r -= p[t];
        if (cw + w[t] <= c) {//搜索左子树
            cx[t] = 1;
            cp += p[t];
            cw += w[t];
            BackTrack(t + 1);
            cp -= p[t];//恢复现场
            cw -= w[t];//恢复现场

        }

        if (cp + r > bestp) {//剪枝操作
            cx[t] = 0;//搜索右子树
            BackTrack(t + 1);
        }
        r += p[t];//恢复现场
    }

    public static int getMaxTower(int[] lengthArray, int[] weightArray) {

        Loading(lengthArray, weightArray, 7);
        return bestx.length;
    }
}
