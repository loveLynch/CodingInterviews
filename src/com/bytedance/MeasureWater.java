package com.bytedance;

import java.util.*;

/**
 * Created by lynch on 2019-09-08. <br>
 **/
public class MeasureWater {
    public static int count = 1;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] bucket = new int[3];
        Queue<Integer> original = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            bucket[i] = input.nextInt();
        }
        original.add(0);
        original.add(0);
        original.add(0);
        Arrays.sort(bucket);
        Set<Integer> storeNum = new HashSet<>();
        int measure = input.nextInt();
//        System.out.println(getMinCount(bucket, original, storeNum, measure));
        Queue<int[]> cur = new LinkedList<>();
        int[] root = new int[]{0, 0, 0};
        cur.add(root);
        int length = 1;
        getMinCount1(bucket, cur, measure, length);
        System.out.println(count);
    }

    public static void getMinCount1(int[] bucket, Queue<int[]> cur, int measure, int length) {
        Queue<int[]> next = new LinkedList<>();
        while (!cur.isEmpty()) {
            int nowBucket[] = cur.poll();
            for (int i = 0; i < 3; i++) {
                int[] newState = new int[3];
                newState[i] = nowBucket[i] + bucket[i];
                next.add(newState);

            }

        }
        while (!next.isEmpty()) {
            int[] temp = next.poll();
            for (int j = 0; j < temp.length; j++)
                if (temp[j] == measure)
                    return;
                else {
                    getMinCount1(bucket, next, measure, length++);
                }
            System.out.println();
        }


    }

    private static int getMinCount(int[] bucket, Queue<Integer> original, Set<Integer> storeNum, int measure) {
        Queue<Integer> next = new LinkedList<>();
        while (!original.isEmpty()) {
            int start = original.poll();
            System.out.println("start " + start);
            if (start + bucket[0] == bucket[0] || start + bucket[1] == bucket[1] || start + bucket[2] == bucket[2])
                count++;
            if (start - bucket[0] == 0 || start - bucket[1] == 0 || start - bucket[2] == 0)
                count++;
            if (!storeNum.contains(start + bucket[0]) && start + bucket[0] <= bucket[2]) {
                next.add(start + bucket[0]);
                storeNum.add(start + bucket[0]);
            }

            if (!storeNum.contains(start - bucket[0]) && start - bucket[0] > 0) {
                next.add(start - bucket[0]);
                storeNum.add(start - bucket[0]);
            }
            if (!storeNum.contains(start + bucket[1]) && start + bucket[1] <= bucket[2]) {
                next.add(start + bucket[1]);
                storeNum.add(start + bucket[1]);
            }

            if (!storeNum.contains(start - bucket[1]) && start - bucket[1] > 0) {
                next.add(start - bucket[1]);
                storeNum.add(start - bucket[1]);
            }
            if (!storeNum.contains(start + bucket[2]) && start + bucket[2] <= bucket[2]) {
                next.add(start + bucket[2]);
                storeNum.add(start + bucket[2]);
            }

            if (!storeNum.contains(start - bucket[2]) && start - bucket[2] > 0) {
                next.add(start - bucket[2]);
                storeNum.add(start - bucket[2]);
            }
            System.out.println(storeNum);

        }
        if (next.contains(measure))
            return count;
        else {
            getMinCount(bucket, next, storeNum, measure);
            count++;
        }
        return count;

    }
}
