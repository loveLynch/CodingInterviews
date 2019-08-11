package com.bytedance.dynamicandgreedy;

import java.util.*;

/**
 * Created by lynch on 2019-08-11. <br>
 * 俄罗斯套娃信封问题
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 说明:
 * 不允许旋转信封。
 * <p>
 * 示例:
 * <p>
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 **/
public class MaxEnvelopes {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int M = input.nextInt();
        int[][] envelopes = new int[M][2];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 2; j++)
                envelopes[i][j] = input.nextInt();

        }
        System.out.println(maxEnvelopes(envelopes));

    }

    /**
     * 可以对信封按照宽度从小到大进行排序，比如针对信封[[3,2],[2, 4],[4,3],[5, 6],[6,5]排序后变为
     * w: 2 -> 3 -> 4 -> 5 -> 6
     * h: 4 -> 2 -> 3 -> 6 -> 5
     * 因此就转化成一个求递增子序列的问题了，求解最大的递增子序列长度
     *
     * @param envelopes
     * @return
     */
    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0)
            return 0;
        //按区间第一个元素对二维数组排序
        Arrays.sort(envelopes,new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0])return o2[1] - o1[1];
                return o1[0] - o2[0];
            }
        });
        int[] height = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++)
            height[i] = envelopes[i][1];

        return getMaxLengthHeight(height);
    }

    /**
     * 最长的递增子序列长度
     *
     * @param height
     * @return
     */
    public static int getMaxLengthHeight(int height[]) {
        int max;
        int res = 0;
        for (int i = 1; i < height.length; i++) {
            max = 0;
            for (int j = res; j >= 0; j--) {
                if (height[i] > height[j]) {
                    max = j + 1;
                    res = Math.max(res, max);
                    break;
                }
            }
            if (max == res || height[i] < height[max])
                height[max] = height[i];
        }
        return res + 1;

    }
}
