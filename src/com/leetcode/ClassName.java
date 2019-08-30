package com.leetcode;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

/**
 * Created by lynch on 2019-08-29. <br>
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * <p>
 * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 * <p>
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class ClassName {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        List<Integer> tempInput = new ArrayList<>();
        while (input.hasNextInt()) {
            int num = input.nextInt();
            if (num == -1)
                break;
            else {
                tempInput.add(num);
            }
        }
        int size = tempInput.size();
        int[][] prerequisites = new int[size / 2][2];
        int index = 0;
        while (index < size) {
            prerequisites[index / 2][0] = tempInput.get(index);
            index++;
            prerequisites[index / 2][1] = tempInput.get(index);
            index++;
        }
        System.out.println(canFinish(n, prerequisites));

    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0)
            //没有课程，当然可以完成课程的学习
            return true;
        //入度数组，一开始全部为 0
        int[] in_degrees = new int[numCourses];
        //邻接表
        List<Integer>[] adj = new List[prerequisites.length];
        //想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
        //[0,1] 表示 1 在先，0 在后
        //注意：邻接表存放的是后继 successor 结点的集合
        for (int i = 0; i < prerequisites.length; i++) {
            in_degrees[prerequisites[i][0]]++;
            adj[i] = new ArrayList<>();
            adj[i].add(prerequisites[i][0]);
            adj[i].add(prerequisites[i][1]);

        }
        //首先遍历一遍，把所有入度为 0 的结点加入队列
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < in_degrees.length; i++) {
            if (in_degrees[i] == 0)
                queue.add(i);
        }
        int counter = 0;
        while (!queue.isEmpty()) {
            int front = queue.peekFirst();
            queue.pop();
            counter++;
            for (int i = 0; i < prerequisites.length; i++) {
                if (adj[i].get(1) == front) {
                    in_degrees[adj[i].get(0)]--;
                    if (in_degrees[adj[i].get(0)] == 0) {   //step3
                        queue.push(adj[i].get(0));
                    }
                }
            }
        }
        return counter == numCourses;
    }
}
