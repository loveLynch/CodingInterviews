package com.lynch;

import java.util.Stack;

/**
 * Created by lynch on 2019/3/17. <br>
 * 包含min函数的栈
 * 定义栈的数据结构，在该类型中实现一个能够得到栈的最小元素的min元素。
 * 在该栈中调用min、push和pop的时间复杂度都是O(1)
 **/
public class MinStack_30 {

    static class StackWithMin<T extends Comparable> {
        private Stack<T> stackData = new Stack<>();//数据栈
        private Stack<T> stackMin = new Stack<>();//最小值栈

        public void push(T data) {
            stackData.push(data);
            if (stackMin.isEmpty())
                stackMin.push(data);
            else {
                //peek()查看栈定元素而不移出它
                T temp = stackMin.peek();
                if (temp.compareTo(data) < 0)
                    stackMin.push(temp);
                else
                    stackMin.push(data);
            }
        }

        public T pop() {
            if (stackData.isEmpty())
                return null;
            stackMin.pop();
            return stackData.pop();
        }

        public T min() {
            if (stackMin.isEmpty())
                return null;
            return stackMin.peek();
        }
    }

    public static void main(String[] args) {
        StackWithMin<Integer> stack = new StackWithMin<>();
        stack.push(3);
        stack.push(4);
        stack.push(2);
        stack.push(1);
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
    }
}
