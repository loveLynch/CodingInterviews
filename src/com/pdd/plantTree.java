package com.pdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 小多想在美化一下自己的庄园。他的庄园毗邻一条小河，他希望在河边种一排树，共 M 棵。小多采购了 N 个品种的树，
 * 每个品种的数量是 Ai (树的总数量恰好为 M)。但是他希望任意两棵相邻的树不是同一品种的。小多请你帮忙设计一种满足要求的种树方案。
 * Created by lynch on 2019-07-22. <br>
 **/
public class plantTree {
    static int n, m;
    static int[] tree;
    static List<String> ans;

    /**
     * 检查是否可以种树
     * 如果是奇数，如果某种树大于（M+1）的一半则不能种，
     * 如果是偶数，则大于M的一半不能种树
     * left/2 和(left + 1)/2的值是相等的，所以奇偶数一起判断
     *
     * @param left
     * @return
     */
    public static boolean check(int left) {
        for (int i = 1; i <= n; i++) {
            if (tree[i] > (left + 1) / 2) return false;
        }
        return true;
    }

    /**
     * 每次种树前都要判断是不是某种树大于一半，如果是，本次种该树。
     * 如果不是再从下往上遍历数组，优先种序号小的树（要保证该序号的树还有的剩，且不等于之前种的树）
     *
     * @param idx
     * @return
     */
    static boolean dfs(int idx) {
        if (!check(m - idx)) return false;
        if (idx == m) {
            return true;
        } else {
            for (int i = 1; i <= n; i++) {
                if (idx == 0 || (tree[i] != 0 && i != Integer.valueOf(ans.get(idx - 1)))) {
                    tree[i]--;
                    ans.add(i + "");
                    if (dfs(idx + 1)) return true;
                    ans.remove(ans.size() - 1);
                    tree[i]++;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            n = sc.nextInt();
            tree = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                tree[i] = sc.nextInt();
                m += tree[i];
            }
            ans = new ArrayList<>();
            if (dfs(0)) {
                System.out.println(String.join(" ", ans));
            } else {
                System.out.println("-");
            }
        }
    }
}
