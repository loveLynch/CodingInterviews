package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-08-31. <br>
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class Combine {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        System.out.println(combine(n, k));


    }

    /**
     * 排列组合的性质C(m,n)=C(m-1,n)+C(m-1,n-1)
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < k || k == 0)
            return result;
        if (k == 1) {
            for (int i = 1; i <= n; i++) {
                List<Integer> single = new ArrayList<>();
                single.add(i);
                result.add(single);
            }
            return result;
        }
        if (k == n) {
            List<Integer> single = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                single.add(i);
            }
            result.add(single);
            return result;
        }
        result = combine(n - 1, k);
        for (List<Integer> item : combine(n - 1, k - 1)) {
            item.add(n);
            result.add(item);

        }

        return result;

    }
}
