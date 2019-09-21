package com.tencent.bishi920;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by lynch on 2019-09-20. <br>
 **/
public class Main2 {
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        int n = input.nextInt();
//        List<Integer> delayList = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            int worker = input.nextInt();
//            int delay = input.nextInt();
//            for (int j = 0; j < worker; j++) {
//                delayList.add(delay);
//            }
//        }
//        System.out.println(getMinTimes(delayList));
//    }
//
//    private static int getMinTimes(List<Integer> delayList) {
//        Collections.sort(delayList);
//        int start = 0;
//        int end = delayList.size() - 1;
//        int result = delayList.get(start) + delayList.get(end);
//        while (start < end) {
//            start++;
//            end--;
//            result = Math.max(delayList.get(start) + delayList.get(end), result);
//        }
//        return result;
//    }
    private static class Helper {
        int cnt;
        int value;

        public Helper(int cnt, int value) {
            this.cnt = cnt;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int cnt;
        int t;
        ArrayList<Helper> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cnt = scanner.nextInt();
            t = scanner.nextInt();
            list.add(new Helper(cnt, t));
        }
        Collections.sort(list, (o1, o2)->{return o1.value - o2.value;});
        System.out.println(getMin(list));
    }

    private static int getMin(ArrayList<Helper> list) {
        int max = 0;
        int cur;
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            if (list.get(i).cnt <= 0) {
                i++;
                continue;
            }
            if (list.get(j).cnt <= 0) {
                j--;
                continue;
            }

            cur = list.get(i).value + list.get(j).value;
            list.get(i).cnt--;
            list.get(j).cnt--;
            if (max < cur) {
                max = cur;
            }
        }

        return max;
    }
}
