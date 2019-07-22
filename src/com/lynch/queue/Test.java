package com.lynch.queue;

import java.util.ArrayList;

class ListNode {
    int val;
    ListNode next = null;
    public ListNode(int val) {
        this.val = val;
    }
}

public class Test {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        Test test = new Test();
        ListNode head = n1;
        test.delNode(head, n1);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public void delNode(ListNode head, ListNode delNode) {
        if (head == null || delNode == null) {
            return;
        }
        if (head == delNode) {// 头节点就是要删除的节点，直接将头结点置空
            head = null;
        } else if (delNode.next == null) {// 如果要删除的是尾节点，需要遍历找到上一个节点
            ListNode node = head;
            while (node.next.next != null) {// 找到尾节点的上一个节点
                node = node.next;
            }
            node.next = null;
        } else {// 常规情况下（既不是尾节点，也不是头节点）
            delNode.val = delNode.next.val;// 将要删除节点的下一个节点的内容拷贝
            delNode.next = delNode.next.next;// 删除下一个节点即可
        }
    }
}
