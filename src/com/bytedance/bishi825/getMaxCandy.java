package com.bytedance.bishi825;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by lynch on 2019-08-25. <br>
 **/
public class getMaxCandy {
    public static ArrayList<Integer> preNode = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] candy = new int[n];
        for (int i = 0; i < n; i++) {
            candy[i] = input.nextInt();
            preNode.add(i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (maxCommonDivisor(candy[i], candy[j]) > 1)
                    join(i, j);
            }
        }
        int result = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(i);
            if (!cnt.containsKey(root)) {
                cnt.put(root, 1);
            } else {
                cnt.put(root, cnt.get(root) + 1);
            }
            result = Math.max(result, cnt.get(root));

        }
        System.out.println(result);


    }

    public static void join(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            if (fa < fb) {
                preNode.set(fb, fa);
            } else {
                preNode.set(fa, fb);

            }
        }
    }

    private static int find(int x) {
        int root = x;
        while (preNode.get(root) != root) {
            root = preNode.get(root);
        }
        int cur;
        while (x != root) {
            cur = preNode.get(x);
            preNode.set(x, root);
            x = cur;
        }
        return root;
    }

    private static int getMaxCandyNum(int[] candy) {
        int maxCandy = 0;
        for (int i = 0; i < candy.length; i++) {
            int count = 0;
            for (int j = 0; j < candy.length; j++) {
                if (maxCommonDivisor(candy[i], candy[j]) > 1)
                    count++;

            }
            maxCandy = Math.max(count, maxCandy);
        }
        return maxCandy;
    }

    public static int maxCommonDivisor(int m, int n) {
        if (m < n) {
            int temp = m;
            m = n;
            n = temp;
        }
        if (m % n == 0) {
            return n;
        } else {
            return maxCommonDivisor(n, m % n);
        }
    }


}
