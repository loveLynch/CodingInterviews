package com.others.pack;

/**
 * Created by lynch on 2019-08-17. <br>
 **/
public class OneZeroPack {
    /**
     * 0-1背包问题
     *
     * @param V      背包容量
     * @param N      物品种类
     * @param weight 物品重量
     * @param value  物品价值
     * @return
     */
    public static String ZeroOnePack(int V, int N, int[] weight, int[] value) {

        //初始化动态规划数组
        int[][] dp = new int[N + 1][V + 1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if (weight[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
            }
        }
        //则容量为V的背包能够装入物品的最大值为
        int maxValue = dp[N][V];
        System.out.println("最优装载 " + maxValue);
        //逆推找出装入背包的所有商品的编号
        int j = V;
        String numStr = "";
        for (int i = N; i > 0; i--) {
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            if (dp[i][j] > dp[i - 1][j]) {
                numStr = i + " " + numStr;
                j = j - weight[i - 1];
            }
            if (j == 0)
                break;
        }
        return numStr;
    }

    /**
     * 0-1背包的优化解法
     * 思路：
     * 只用一个一维数组记录状态，dp[i]表示容量为i的背包所能装入物品的最大价值
     * 用逆序来实现
     */
    public static int ZeroOnePack2(int V, int N, int[] weight, int[] value) {
        //动态规划
        int[] dp = new int[V + 1];
        for (int i = 1; i < N + 1; i++) {
            //逆序实现
            for (int j = V; j >= weight[i - 1]; j--) {
                dp[j] = Math.max(dp[j - weight[i - 1]] + value[i - 1], dp[j]);
            }
        }
        return dp[V];
    }

    public static void main(String[] args) {
        //测试
        int[] w1 = {2, 1, 3, 2, 4, 5, 3, 1};
        int[] p1 = {13, 10, 24, 15, 28, 33, 20, 8};
        int c1 = 12;
        System.out.println(ZeroOnePack(c1, w1.length, w1, p1));
        System.out.println(ZeroOnePack2(c1, w1.length, w1, p1));


    }
}
