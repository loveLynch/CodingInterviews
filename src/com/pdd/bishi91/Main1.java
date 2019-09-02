package com.pdd.bishi91;

import java.util.*;

/**
 * Created by lynch on 2019-09-01. <br>
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        String[] lineArray = line.split(";");
        String numString = lineArray[0];
        int N = Integer.parseInt(lineArray[1]);
        String[] numArray = numString.split(",");
        int[] nums = new int[numArray.length];
        for (int i = 0; i < numArray.length; i++) {
            nums[i] = Integer.parseInt(numArray[i]);
        }
        System.out.println(getNOfNums(nums, N));

    }

    private static String getNOfNums(int[] nums, int n) {
        StringBuilder result = new StringBuilder();
        Stack<Integer> Ostack = new Stack<>();
        Stack<Integer> Jstack = new Stack<>();
        Stack<Integer> resultStack = new Stack<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 0)
                Ostack.add(nums[i]);
            else {
                Jstack.add(nums[i]);
            }
        }
        if (Ostack.size() >= n) {
            while (resultStack.size() < n)
                resultStack.add(Ostack.pop());
        } else {
            while (!Ostack.isEmpty())
                resultStack.add(Ostack.pop());
            while (resultStack.size() < n)
                resultStack.add(Jstack.pop());
        }
        for (int i = 0; i < resultStack.size() - 1; i++) {
            result.append(resultStack.get(i));
            result.append(",");

        }
        result.append(resultStack.get(resultStack.size() - 1));

        return result.toString();
    }
}
