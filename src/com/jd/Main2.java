package com.jd;

import java.util.*;

/**
 * Created by lynch on 2019-08-24. <br>
 * 测试样例1：
 * input
 * 2 2
 * 1 3
 * 1 4
 * output
 * 1
 * 1
 * 测试样例2：
 * input
 * 2 4
 * 1 3
 * 1 4
 * 2 3
 * 2 4
 * output
 * 2
 * 1 2
 * 测试样例3：
 * input
 * 4 4
 * 1 5
 * 2 5
 * 3 5
 * 1 6
 * output
 * 2
 * 1 5
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[][] friends = new int[m][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 2; j++) {
                friends[i][j] = input.nextInt();
            }
            int temp = friends[i][0];
            if (temp > friends[i][1]) {
                friends[i][0] = friends[i][1];
                friends[i][1] = temp;
            }
        }
        TreeSet<Integer> result = removeMinStudent(n, friends);
        System.out.println(result.size());
        for (int r : result)
            System.out.print(r + " ");
    }

    private static TreeSet<Integer> removeMinStudent(int n, int[][] friends) {
        TreeSet<Integer> result = new TreeSet<>();
        TreeMap<Integer, TreeSet<Integer>> boys = new TreeMap<>();
        TreeMap<Integer, TreeSet<Integer>> girls = new TreeMap<>();
        for (int i = 0; i < friends.length; i++) {
            if (!boys.containsKey(friends[i][0])) {
                TreeSet<Integer> girl = new TreeSet<>();
                girl.add(friends[i][1]);
                boys.put(friends[i][0], girl);
            } else {
                TreeSet<Integer> girl = boys.get(friends[i][0]);
                girl.add(friends[i][1]);
                boys.put(friends[i][0], girl);
            }
            if (!girls.containsKey(friends[i][1])) {
                TreeSet<Integer> boy = new TreeSet<>();
                boy.add(friends[i][0]);
                girls.put(friends[i][1], boy);
            } else {
                TreeSet<Integer> boy = girls.get(friends[i][1]);
                boy.add(friends[i][0]);
                girls.put(friends[i][1], boy);
            }
        }
        int count = 0; //统计异性对数
        while (count < friends.length) {
            int max = 0;
            Integer thisKey = 0;

            //先男后女，男的字典序小
            for (Map.Entry<Integer, TreeSet<Integer>> entry : boys.entrySet()) {
                if (entry.getValue().size() > max) {
                    max = entry.getValue().size();
                    thisKey = entry.getKey();

                }
            }


            for (Map.Entry<Integer, TreeSet<Integer>> entry : girls.entrySet()) {
                if (entry.getValue().size() > max) {
                    max = entry.getValue().size();
                    thisKey = entry.getKey();
                }
            }
            if (boys.containsKey(thisKey)) {
                count += boys.get(thisKey).size();
                boys.remove(thisKey);
            }
            if (girls.containsKey(thisKey)) {
                count += girls.get(thisKey).size();
                girls.remove(thisKey);

            }

            result.add(thisKey);
        }

        return result;
    }
}
