package com.leetcode;

import java.util.*;

/**
 * Created by lynch on 2019-08-30. <br>
 * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。现在，假设您获得了城市风光照片（图A）上显示的所有建筑物的位置和高度，请编写一个程序以输出由这些建筑物形成的天际线（图B）。
 * <p>
 * 每个建筑物的几何信息用三元组 [Li，Ri，Hi] 表示，其中 Li 和 Ri 分别是第 i 座建筑物左右边缘的 x 坐标，Hi 是其高度。可以保证 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX 和 Ri - Li > 0。您可以假设所有建筑物都是在绝对平坦且高度为 0 的表面上的完美矩形。
 * <p>
 * 例如，图A中所有建筑物的尺寸记录为：[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] 。
 * <p>
 * 输出是以 [ [x1,y1], [x2, y2], [x3, y3], ... ] 格式的“关键点”（图B中的红点）的列表，它们唯一地定义了天际线。关键点是水平线段的左端点。请注意，最右侧建筑物的最后一个关键点仅用于标记天际线的终点，并始终为零高度。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 * <p>
 * 例如，图B中的天际线应该表示为：[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-skyline-problem
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class SkyLine {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();//行 建筑的数量
        int buildings[][] = new int[num][3];//三元组[Li，Ri，Hi]
        for (int i = 0; i < num; i++) {
            buildings[i][0] = input.nextInt();//Li
            buildings[i][1] = input.nextInt();//Ri
            buildings[i][2] = input.nextInt();//Hi
        }
        System.out.println(getSkyline(buildings));

    }

    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        if (buildings.length == 0 || buildings == null)
            return result;
        // 将建筑物坐标化成[左端点，高度],[右端点，-高度],用高度的正负来判断是左端点还是右端点。
        int[][] buildLHRH = new int[2 * buildings.length][2];
        for (int i = 0; i < buildings.length; i++) {
            buildLHRH[2 * i][0] = buildings[i][0];
            buildLHRH[2 * i][1] = buildings[i][2];
            buildLHRH[2 * i + 1][0] = buildings[i][1];
            buildLHRH[2 * i + 1][1] = -buildings[i][2];

        }
        //整体按升序;若左节点相同，按降序排
        Arrays.sort(buildLHRH, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o2[1] - o1[1];
                return o1[0] - o2[0];
            }
        });
        // list1维护堆内的有序
        List<Integer> temp = new ArrayList<>();
        temp.add(0);
        int index;

        for (int i = 0; i < buildLHRH.length; i++) {
            if (buildLHRH[i][1] > 0) {// 左端点
                if (buildLHRH[i][1] > temp.get(temp.size() - 1)) {// 如果左端点的高度比堆的最大值还大，说明是一个转折点
                    result.add(Arrays.asList(buildLHRH[i][0], buildLHRH[i][1]));
                }
                index = findIndex(temp, buildLHRH[i][1]);
                temp.add(index, buildLHRH[i][1]);// 将该点入堆
            } else {//右端点
                index = findIndex(temp, -buildLHRH[i][1]);
                temp.remove(index);//先将该点从堆中移除
                if (-buildLHRH[i][1] > temp.get(temp.size() - 1)) {//如果右端点的高度是堆内最大，说明是一个转折点
                    result.add(Arrays.asList(buildLHRH[i][0], temp.get(temp.size() - 1)));
                }
            }
        }
        return result;
    }

    // 二分查找辅助函数
    public static int findIndex(List<Integer> list, int n) {
        int low = 0, high = list.size(), middle;
        while (low < high) {
            middle = (low + high) >>> 1;
            if (list.get(middle) < n) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return low;
    }

    public static List<List<Integer>> getSkyline1(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        if (buildings.length == 0 || buildings == null)
            return result;
        //升序
        Arrays.sort(buildings, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < buildings.length - 1; i++) {
            if (buildings[i + 1][2] > buildings[i][2]) {
                if (buildings[i + 1][1] < buildings[i][1]) {
                    List<Integer>[] keyPoint = new List[3];
                    keyPoint[0] = new ArrayList<>();
                    keyPoint[0].add(buildings[i][0]);
                    keyPoint[0].add(buildings[i][2]);
                    keyPoint[1] = new ArrayList<>();
                    keyPoint[1].add(buildings[i + 1][0]);
                    keyPoint[1].add(buildings[i + 1][2]);
                    keyPoint[2] = new ArrayList<>();
                    keyPoint[2].add(buildings[i + 1][1]);
                    keyPoint[2].add(buildings[i][2]);
                    for (List temp : keyPoint)
                        if (!result.contains(temp))
                            result.add(temp);

                } else {
                    List<Integer>[] keyPoint = new List[2];
                    keyPoint[0] = new ArrayList<>();
                    keyPoint[0].add(buildings[i][0]);
                    keyPoint[0].add(buildings[i][2]);
                    keyPoint[1] = new ArrayList<>();
                    keyPoint[1].add(buildings[i + 1][0]);
                    keyPoint[1].add(buildings[i + 1][2]);
                    for (List temp : keyPoint)
                        if (!result.contains(temp))
                            result.add(temp);
                }
            } else if (buildings[i + 1][2] < buildings[i][2]) {
                if (buildings[i + 1][1] > buildings[i][1]) {
                    List<Integer>[] keyPoint = new List[2];
                    keyPoint[0] = new ArrayList<>();
                    keyPoint[0].add(buildings[i][0]);
                    keyPoint[0].add(buildings[i][2]);
                    keyPoint[1] = new ArrayList<>();
                    keyPoint[1].add(buildings[i][1]);
                    keyPoint[1].add(buildings[i + 1][2]);
                    for (List temp : keyPoint)
                        if (!result.contains(temp))
                            result.add(temp);
                } else {
                    List<Integer> keyPoint = new ArrayList<>();
                    keyPoint.add(buildings[i][0]);
                    keyPoint.add(buildings[i][2]);
                    if (!result.contains(keyPoint))
                        result.add(keyPoint);
                }

            } else {
                List<Integer> keyPoint = new ArrayList<>();
                keyPoint.add(buildings[i][0]);
                keyPoint.add(buildings[i][2]);
                if (!result.contains(keyPoint))
                    result.add(keyPoint);
            }
        }
        List<Integer> keyPoint = new ArrayList<>();
        keyPoint.add(buildings[buildings.length - 1][1]);
        keyPoint.add(0);
        result.add(keyPoint);

        return result;

    }
}
