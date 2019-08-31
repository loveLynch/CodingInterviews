package com.leetcode;

import java.util.*;

/**
 * Created by lynch on 2019-08-30. <br>
 * 有 N 个花园，按从 1 到 N 标记。在每个花园中，你打算种下四种花之一。
 * <p>
 * paths[i] = [x, y] 描述了花园 x 到花园 y 的双向路径。
 * <p>
 * 另外，没有花园有 3 条以上的路径可以进入或者离开。
 * <p>
 * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
 * <p>
 * 以数组形式返回选择的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。花的种类用  1, 2, 3, 4 表示。保证存在答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：N = 3, paths = [[1,2],[2,3],[3,1]]
 * 输出：[1,2,3]
 * 示例 2：
 * <p>
 * 输入：N = 4, paths = [[1,2],[3,4]]
 * 输出：[1,2,1,2]
 * 示例 3：
 * <p>
 * 输入：N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * 输出：[1,2,3,4]
 *  
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flower-planting-with-no-adjacent
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class PlantNoAdj {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        List<Integer> tempInput = new ArrayList<>();
        while (input.hasNextInt()) {
            int num = input.nextInt();
            if (num == -1)//以-1结束输入
                break;
            else {
                tempInput.add(num);
            }
        }
        int size = tempInput.size();
        int[][] paths = new int[size / 2][2];
        int index = 0;
        while (index < size) {
            paths[index / 2][0] = tempInput.get(index++);
            paths[index / 2][1] = tempInput.get(index++);
        }
        for (int i = 0; i < paths.length; i++) {
            System.out.println(paths[i][0] + "  " + paths[i][1]);
        }
        int[] answer = gardenNoAdj(N, paths);
        for (int a : answer)
            System.out.print(a + " ");
    }

    public static int[] gardenNoAdj(int N, int[][] paths) {
        int[] answer = new int[N];
        List<Integer>[] graph = new List[N];
        ////初始化图
        for (int i = 0; i < paths.length; i++) {
            int a = paths[i][0];
            int b = paths[i][1];
            if (graph[a - 1] != null) {
                graph[a - 1].add(b - 1);
            } else {
                graph[a - 1] = new ArrayList<>();
                graph[a - 1].add(b - 1);
            }
            if (graph[b - 1] != null) {
                graph[b - 1].add(a - 1);
            } else {
                graph[b - 1] = new ArrayList<>();
                graph[b - 1].add(a - 1);
            }

        }
        //遍历节点
        for (int i = 0; i < N; i++) {
            TreeSet<Integer> flower = new TreeSet<>();
            flower.add(1);
            flower.add(2);
            flower.add(3);
            flower.add(4);
            //找出当前节点的邻接节点没用到的色
            //8 7 4 3 7 1 5 5 4 7 1 3 1 4 3 6 5 -1
            if (graph[i] != null) {
                for (int j = 0; j < graph[i].size(); j++) {
                    //移除连接节点已选颜色
                    if (answer[graph[i].get(j)] != 0 && flower.contains(answer[graph[i].get(j)])) {
                        flower.remove(answer[graph[i].get(j)]);
                    }
                }
            }
            //选择邻接节点没有的颜色
            answer[i] = flower.first();

        }
        return answer;

    }
}
