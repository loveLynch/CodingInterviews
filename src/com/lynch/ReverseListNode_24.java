package com.lynch;

import com.lynch.structure.ListNode;

/**
 * Created by lynch on 2019/3/14. <br>
 * 24.反转链表
 * 输入一个链表，反转该链表并输出反转后的头节点
 * 注意：反转链表指向时，节点间出现断裂
 **/
public class ReverseListNode_24 {
    //剑指Offer
    private static ListNode<Integer> reverseList_ONE(ListNode<Integer> pHead) {
        ListNode<Integer> pReverseHead = null;
        ListNode<Integer> pNode = pHead;
        ListNode<Integer> pPrev = null;
        //注意空链表和一个节点链表
        while (pNode != null) {
            ListNode<Integer> pNext = pNode.next;//保存当前节点的后一个节点
            if (pNext == null) {
                pReverseHead = pNode;
            }
            pNode.next = pPrev;

            pPrev = pNode;
            pNode = pNext;

        }
        return pReverseHead;

    }
    //博客
    public static ListNode<Integer> reverseList_TWO(ListNode<Integer> head) {
        if (head == null || head.next == null)
            return head;
        ListNode<Integer> pre = null;
        ListNode<Integer> cur = head;
        ListNode<Integer> post = head.next;
        while (true) {
            cur.next = pre;
            pre = cur;
            cur = post;
            if (post != null)
                post = post.next;
            else
                return pre;
        }
    }

    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        ListNode<Integer> node4 = new ListNode<>(4);
        ListNode<Integer> node5 = new ListNode<>(5);
        ListNode<Integer> node6 = new ListNode<>(6);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        System.out.println("反转前：" + head);
        System.out.println("反转后：" + reverseList_TWO(head));

        ListNode<Integer> headNull = null;
        System.out.println("反转前：" + headNull);
        System.out.println("反转后：" + reverseList_TWO(headNull));

        ListNode<Integer> headOne = new ListNode<>(1);
        System.out.println("反转前：" + headOne);
        System.out.println("反转后：" + reverseList_TWO(headOne));

    }
}
