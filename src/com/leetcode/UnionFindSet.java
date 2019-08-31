package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-08-30. <br>
 * 给定编号从 0 到 n-1 的 n 个节点和一个无向边列表（每条边都是一对节点），请编写一个函数来计算无向图中连通分量的数目。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * n = 5
 * 和
 * edges = [[0, 1], [1, 2], [3, 4]]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入:
 * n = 5
 * 和
 * edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 * 输出:  1
 **/
public class UnionFindSet {
    public static int count;

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
        int[][] edges = new int[size / 2][2];
        int index = 0;
        while (index < size) {
            edges[index / 2][0] = tempInput.get(index);
            index++;
            edges[index / 2][1] = tempInput.get(index);
            index++;
        }
        int[] roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
        count = n;

        int[] rank = new int[n];
        for (int i = 0; i < edges.length; i++) {
            int[] now = edges[i];
            union(roots, rank, now[0], now[1]);

        }
        System.out.println(count);

    }

    public static int find(int[] roots, int member) {
        List<Integer> temp = new ArrayList<>();
        while (member != roots[member]) {
            temp.add(roots[member]);
            member = roots[member];

        }
        for (int tmp : temp)
            roots[tmp] = member;
        return member;
    }

    public static void union(int[] roots, int[] rank, int p, int q) {
        int parentP = find(roots, p);
        int parentQ = find(roots, q);
        if (parentP != parentQ) {
            if (rank[parentP] > rank[parentQ]) {
                roots[parentQ] = parentP;
            } else if (rank[parentP] < rank[parentQ]) {
                roots[parentP] = parentQ;
            } else {
                rank[parentQ] = parentP;
                rank[parentP]--;
            }
            count--;
        }

    }


}