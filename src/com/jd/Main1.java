package com.jd;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by lynch on 2019-08-24. <br>
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int[] students = new int[N];
        for (int i = 0; i < N; i++) {
            students[i] = input.nextInt();
        }
        System.out.println(getMaxGroup(students));

    }
    private static int getMaxGroup(int[] students) {
        Stack<Integer> stack = new Stack<>();
        stack.push(students[students.length - 1]);
        for (int i = students.length - 2; i >= 0; i--) {
            if (students[i] <= stack.peek())
                stack.push(students[i]);
            else {
                int min = stack.peek();
                while (!stack.empty() && students[i] > stack.peek())
                    stack.pop();
                stack.push(min);
            }
        }
        return stack.size();
    }
}
