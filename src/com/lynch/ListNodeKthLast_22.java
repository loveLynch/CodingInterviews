package com.lynch;

import com.lynch.structure.ListNode;

/**
 * Created by lynch on 2019/3/14. <br>
 * 22.链表中倒数第k个节点
 * <p>
 * 求链表中倒数第k个节点。链表的尾节点定义为倒数第1个节点。
 * 注意：程序的鲁棒性
 **/
public class ListNodeKthLast_22 {
    private static ListNode<Integer> findKthToTail(ListNode<Integer> listHead, int k) {
        //考虑鲁棒性
        if (listHead == null || k == 0)
            return null;
        ListNode<Integer> pAhead = listHead;
        ListNode<Integer> pBehind = null;
        //先让一个节点指针移动k-1步
        for (int i = 0; i < k - 1; i++) {
            if (pAhead.next != null) {
                pAhead = pAhead.next;
            } else {
                return null;
            }
        }
        pBehind = listHead;
        //此时两个同时移动，当pHead到达尾节点时，pBehind刚好到达倒数第k个节点
        while (pAhead.next != null) {
            pAhead = pAhead.next;
            pBehind = pBehind.next;
        }
        return pBehind;
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
        System.out.println("基本链表：" + head);
        for (int i = 1; i <= 10; i++) {
            Integer value;
            if (findKthToTail(head, i) != null)
                value = findKthToTail(head, i).val;
            else {
                value = null;
            }
            System.out.println("倒数第" + i + "个节点：" + value);
        }
    }
}
