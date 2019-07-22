package com.lynch;


import com.lynch.structure.ListNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by lynch on 2019/2/28. <br>
 * 6.从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来打印出每个节点的值
 **/
public class ListNodeLastToFirst_6 {
    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<Integer>(1);
        head.next = new ListNode<Integer>(2);
        head.next.next = new ListNode<Integer>(3);
        printReversinglyRecursively(head);
        System.out.println();
        printReversinglyIteratively(head);
    }

    //递归版
    public static void printReversinglyRecursively(ListNode<Integer> node) {
        if (node == null)
            return;
        else {
            printReversinglyRecursively(node.next);
            System.out.println(node.val);
        }
    }

    //非递归版
    public static void printReversinglyIteratively(ListNode<Integer> node) {
        Stack<Integer> stack = new Stack<>();
        for (ListNode<Integer> temp = node; temp != null; temp = temp.next)
            stack.add(temp.val);
        while (!stack.isEmpty())
            System.out.println(stack.pop());
    }
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode==null)
            return null;
        Stack stack = new Stack();
        ArrayList<Integer> list = new ArrayList<>();
        while(listNode.next!=null){
            stack.push(listNode.val);
        }
        while(!stack.isEmpty()){
            list.add((Integer) stack.pop());
        }
        return list;

    }

}
