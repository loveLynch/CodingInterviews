package com.xiecheng;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main1 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /*请完成下面这个函数，实现题目要求的功能
     ******************************开始写代码******************************/
    static ListNode partition(ListNode head, int m) {
        if (head == null || head.next == null)
            return head;
        ListNode pre = new ListNode(0);
        ListNode preSm = pre;
        ListNode next = new ListNode(0);
        ListNode nextLg = next;
        ListNode temp = head;

        while (temp != null) {
            if (temp.val <= m) {
                preSm.next = new ListNode(temp.val);
                preSm = preSm.next;
            } else {
                nextLg.next = new ListNode(temp.val);
                nextLg = nextLg.next;
            }
            temp = temp.next;
        }
        preSm.next = next.next;


        return pre.next;

    }

    static ListNode partition1(ListNode head, int m) {

        ListNode tmp = head;//当前遍历的节点
        ListNode cur = new ListNode(0);//当前的最小节点
        cur.next = head;
        ListNode mhead = cur;
        ListNode pre = new ListNode(1); //tmp的前一个节点
        pre.next = head;
        ListNode tmp1 = head; //第一个大于m的节点
        while (tmp1 != null) {
            if (tmp1.val > m)
                break;
            else
                tmp1 = tmp1.next;
        }
        if (tmp1 == null)
            return head;
        while (tmp != null) {
            if (tmp.val <= m) {
                cur.next = tmp;
                cur = cur.next;
                pre.next = tmp.next;
                cur.next = tmp1;
                tmp = pre.next;
            } else {
                pre = pre.next;
                tmp = tmp.next;
            }
        }
        return mhead.next;

    }


    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ListNode head = null;
        ListNode node = null;
        int m = in.nextInt();
        while (in.hasNextInt()) {
            int v = in.nextInt();
            if (head == null) {
                node = new ListNode(v);
                head = node;
            } else {
                node.next = new ListNode(v);
                node = node.next;
            }

        }
        head = partition1(head, m);
        if (head != null) {
            System.out.print(head.val);
            head = head.next;
            while (head != null) {
                System.out.print(",");
                System.out.print(head.val);
                head = head.next;
            }
        }
        System.out.println();
    }
}
