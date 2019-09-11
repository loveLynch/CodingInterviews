package com.baidu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by lynch on 2019-09-10. <br>
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int overM = 0;
        ArrayList<Integer> moneyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int money = input.nextInt();
            int num = input.nextInt();
            if (money >= m) {
                overM += num;
            } else {
                for (int j = 0; j < num; j++) {
                    moneyList.add(money);
                }
            }
        }
        if (moneyList.size() == 0) {
            System.out.println(overM);
        } else {
            System.out.println(overM + getSolution(moneyList, m));
        }
    }

    private static int getSolution(ArrayList<Integer> moneyList, int m) {
        int count = 0;
        Collections.sort(moneyList);
        int left = 0;
        int right = moneyList.size() - 1;
        while (left < right) {
            if (moneyList.get(left) + moneyList.get(right) < m) {
                int temp = moneyList.get(left);
                //先用小的
                while (left < right && temp + moneyList.get(right) < m) {
                    left++;
                    temp += moneyList.get(left);
                    if (temp + moneyList.get(right) >= m) {
                        count++;
                        right--;
                    }
                }
            } else {
                count++;
                left++;
                right--;
            }
        }
        return count;
    }
}
