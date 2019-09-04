package com.xiecheng;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main2 {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static String resolve(String expr) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            stack.push(c);
            if (c == ')') {
                stack.pop();
                StringBuilder temp = new StringBuilder();
                while (stack.peek() != '(') {
                    temp.append(stack.pop());
                }
                stack.pop();
                String tempStr = temp.toString();
                for (int j = 0; j < tempStr.length(); j++)
                    stack.push(tempStr.charAt(j));
            }
        }
        while (!stack.isEmpty())
            result.append(stack.pop());
        if (result.toString().contains("(") || result.toString().contains(")"))
            return "";
        return result.reverse().toString();

    }

    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String res;

        String _expr;
        try {
            _expr = in.nextLine();
        } catch (Exception e) {
            _expr = null;
        }

        res = resolve(_expr);
        System.out.println(res);
    }
}
