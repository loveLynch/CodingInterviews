package com.tencent.bishi;

import java.util.*;

/**
 * Created by lynch on 2019-08-17. <br>
 **/
public class Main3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int l = input.nextInt();
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> single = new ArrayList<>();
            single.add(input.nextInt());
            single.add(input.nextInt());
            lists.add(single);
        }
        Collections.sort(lists, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if (o1.get(0) < o2.get(0))
                    return -1;
                else if (o1.get(0) > o2.get(0))
                    return 1;
                else
                    return 0;
            }
        });
        int result = 0;
        if (lists.get(0).get(0) > 0) {
            System.out.println(-1);
        }
        int i = 0, j = 0;
        for (; i < l && j < lists.size(); ) {
            if (lists.get(j).get(0) <= i) {
                int temp = lists.get(j).get(1);
                int x = j;
                while ((j < lists.size()) && (lists.get(j).get(0) <= i)) {
                    if (lists.get(j).get(1) > temp) {
                        temp = lists.get(j).get(1);
                        x = j;
                    }
                    j++;
                }
                result++;
                i = temp;
                j = x + 1;
            } else {
                break;
            }
        }
        if (i < l) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }
}
