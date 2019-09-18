package com.zynetwork;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by lynch on 2019-09-17. <br>
 * 使用两个栈进行排序
 **/
public class StackSort {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            stack.push(input.nextInt());
        }
    }

    private static Stack<Integer> getSortStack(Stack<Integer> stack) {
        Stack<Integer> tempStack = new Stack<>();
        int count = 0;
        while (!stack.isEmpty()) {
            int temp = stack.pop();
            if (!tempStack.isEmpty()) {
                while (!tempStack.isEmpty() && temp < tempStack.peek()) {
                    stack.push(tempStack.pop());
                    count++;//记录出栈次数
                }

                tempStack.push(temp);
                while (count > 0) {
                    tempStack.push(stack.pop());
                    count--;//根据出栈次数重新压栈
                }
            } else {
                tempStack.push(temp);
            }
        }
        return tempStack;
    }

}
