package com.lynch;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lynch on 2019/3/6. <br>
 * 9.用两个栈实现队列
 * 用两个栈实现一个队列。
 * 队列声明如下：两个函数appendTail和deleteHead分别完成在队列尾部插入节点和在队列头部删除节点的功能
 **/
public class QueueWithTwoStack_9<T> {
    private Stack<T> stack1 = new Stack<>();
    private Stack<T> stack2 = new Stack<>();

    /**
     * 将数据插入栈stack1，先插入则在下面
     *
     * @param data
     */
    public void appendTail(T data) {
        stack1.push(data);

    }

    /**
     * 1。将数据从stack1中弹出压入到stack2中
     * 2。若stack2不为空，则刚好是队列的顺序，直接删除最上面就行
     * 3。若stack1不为空，循环1，2。
     *
     * @return
     */
    public T deleteHead() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        } else if (!stack1.isEmpty()) {
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
            return stack2.pop();
        } else
            return null;
    }

    public static void main(String[] args) {
        System.out.println("the original Queue:");
        Queue<Integer> queue = new LinkedBlockingQueue<>();
        System.out.println(queue.poll());
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        queue.offer(4);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println("the achieving queue with two stack:");
        QueueWithTwoStack_9<Integer> queueWithTwoStack = new QueueWithTwoStack_9<>();
        System.out.println(queueWithTwoStack.deleteHead());
        queueWithTwoStack.appendTail(1);
        queueWithTwoStack.appendTail(2);
        queueWithTwoStack.appendTail(3);
        System.out.println(queueWithTwoStack.deleteHead());
        System.out.println(queueWithTwoStack.deleteHead());
        queueWithTwoStack.appendTail(4);
        System.out.println(queueWithTwoStack.deleteHead());
        System.out.println(queueWithTwoStack.deleteHead());
        System.out.println(queueWithTwoStack.deleteHead());
    }
}
