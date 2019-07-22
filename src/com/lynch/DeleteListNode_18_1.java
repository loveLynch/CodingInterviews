package com.lynch;

import com.lynch.structure.ListNode;

/**
 * Created by lynch on 2019/3/13. <br>
 * 18.1删除链表的节点
 * 一、在O(1)时间内删除链表节点
 * 给定单向链表的头指针和一个节点指针，定义一个函数在O(1)
 * 时间内删除该节点。
 **/
public class DeleteListNode_18_1 {
    private static ListNode<Integer> deleteNode(ListNode<Integer> listNode, ListNode<Integer> toBeDeleted) {
        //删除头节点 O(1)
        if (listNode == toBeDeleted) {
            return listNode.next;
        }
        //不是尾节点 O(1)
        else if (toBeDeleted.next != null) {
            toBeDeleted.val = toBeDeleted.next.val;
            toBeDeleted.next = toBeDeleted.next.next;
            return listNode;
            //链表中多个节点，删除尾节点
        } else {
            ListNode<Integer> tempNode = listNode;
            //顺序遍历查找尾节点 O(n)
            while (tempNode.next != toBeDeleted) {
                tempNode = tempNode.next;
            }
            tempNode.next = null;
            return listNode;
        }
        //平均时间复杂度 [(n-1)*O(1)+O(n)]/n = O(1)

    }

    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        head.next = node2;
        node2.next = node3;
        System.out.println(head);
        head = deleteNode(head, head);
        System.out.println(head);
        head = deleteNode(head, node3);
        System.out.println(head);
        head = deleteNode(head, head);
        System.out.println(head);
    }
}
