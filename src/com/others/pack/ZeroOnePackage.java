package com.others.pack;

/**
 * Created by lynch on 2019-06-04. <br>
 * 回溯法
 * 0/1背包问题
 **/
public class ZeroOnePackage {
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


    public static void main(String[] args) {
        //测试
        int[] w1 = {2, 1, 3, 2, 4, 5, 3, 1};
        int[] p1 = {13, 10, 24, 15, 28, 33, 20, 8};
        int c1 = 12;
        Loading(w1, p1, c1);
        System.out.println("最优装载为：" + bestp);
        System.out.println("选取的背包：");
        for (int i = 0; i <= count; i++) {
//            System.out.print(bestx[i] + " ");
            if (bestx[i]==1)
                System.out.print("【weight "+w1[i]+" value "+p1[i]+"】  ");
        }


    }
}
