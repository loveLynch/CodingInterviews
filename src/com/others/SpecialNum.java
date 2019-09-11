package com.others;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by lynch on 2019-09-11. <br>
 * input
 * 5
 * 38
 * 141
 * 10
 * 99
 * 737
 * output
 * GREAT
 * GOOD
 * NONE
 * GOOD
 * PERFECT
 **/
public class SpecialNum {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = input.nextInt();
        }
        ArrayList<String> resultList = getResult(nums);
        for (String result : resultList) {
            System.out.println(result);
        }
    }

    private static ArrayList<String> getResult(int[] nums) {
        ArrayList<String> resultList = new ArrayList<>();
        for (int num : nums) {
            String original = String.valueOf(num);
            char[] single = original.toCharArray();
            int sum = 0;
            for (int i = 0; i < single.length; i++) {
                sum += (single[i] - '0');
            }
            String reverse = new StringBuilder(original).reverse().toString();
            if (original.equals(reverse) && !isPrime(sum)) {
                resultList.add("GOOD");
            } else if (!original.equals(reverse) && isPrime(sum)) {
                resultList.add("GREAT");
            } else if (original.equals(reverse) && isPrime(sum)) {
                resultList.add("PERFECT");
            } else {
                resultList.add("NONE");
            }

        }
        return resultList;

    }

    /**
     * 是否是质数
     *
     * @param num
     * @return
     */
    public static boolean isPrime(int num) {
        boolean flag = true;
        if (num < 2) {
            return false;
        } else {
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
}
