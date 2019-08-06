package com.bytedance.arrayandsort;


import java.util.*;

/**
 * Created by lynch on 2019-08-06. <br>
 * 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 **/
public class MergeInterval {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int M = input.nextInt();
        int[][] intervals = new int[M][2];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 2; j++)
                intervals[i][j] = input.nextInt();

        }
        int[][] merge = merge(intervals);
        for (int i = 0; i < merge.length; i++) {
            System.out.print(merge[i][0] + " " + merge[i][1]);
            System.out.println();
        }

    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length <=1 || intervals == null)
            return intervals;
        //按区间第一个元素对二维数组排序
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                final Integer left1 = o1[0];
                final Integer left2 = o2[0];
                return left1.compareTo(left2);
            }
        });
        int min = intervals[0][0];
        int max = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            //重叠即合并区间
            if (intervals[i][0] <= max) {
                max = intervals[i][1] > max ? intervals[i][1] : max;
            } else {
                list.add(new int[]{min, max});
                min = intervals[i][0];
                max = intervals[i][1];
            }
        }
        list.add(new int[]{min, max});
        int[][] merge = new int[list.size()][2];
        for (
                int i = 0; i < list.size(); i++) {
            merge[i][0] = list.get(i)[0];
            merge[i][1] = list.get(i)[1];
        }
        return merge;
    }
}