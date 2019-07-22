package com.lynch;

import com.lynch.structure.ListNode;


/**
 * Created by lynch on 2019/3/16. <br>
 * 25.合并两个排序的链表
 * 输入两个递增排序链表，合并这两个链表并使新链表中的节点仍然是递增的
 **/
public class MergeTwoSortList_25 {
    private static ListNode<Integer> merge(ListNode<Integer> pHead1, ListNode<Integer> pHead2) {
        //注意鲁棒性，空链表检查
        if (pHead1 == null) {
            return pHead2;
        } else if (pHead2 == null) {
            return pHead1;
        }
        ListNode<Integer> pMergeHead = null;
        //递归
        // 合并后链表的头节点，剩余节点的头节点判断
        if (pHead1.val < pHead2.val) {
            pMergeHead = pHead1;
            pMergeHead.next = merge(pHead1.next, pHead2);
        } else {
            pMergeHead = pHead2;
            pMergeHead.next = merge(pHead1, pHead2.next);

        }


        return pMergeHead;
    }

    public static void main(String[] args) {
        ListNode<Integer> head11 = new ListNode<>(1);
        ListNode<Integer> node12 = new ListNode<>(3);
        ListNode<Integer> node13 = new ListNode<>(5);
        ListNode<Integer> node14 = new ListNode<>(7);
        ListNode<Integer> node15 = new ListNode<>(9);
        head11.next = node12;
        node12.next = node13;
        node13.next = node14;
        node14.next = node15;

        ListNode<Integer> head21 = new ListNode<>(2);
        ListNode<Integer> node22 = new ListNode<>(3);
        ListNode<Integer> node23 = new ListNode<>(4);
        ListNode<Integer> node24 = new ListNode<>(8);
        head21.next = node22;
        node22.next = node23;
        node23.next = node24;

        System.out.println("合并前");
        System.out.println(head11);
        System.out.println(head21);
        System.out.println("合并后");
        System.out.println(merge(head11,head21));

        System.out.println();

        ListNode<Integer> head11NULL = null;

        System.out.println("合并前");
        System.out.println(head11NULL);
        System.out.println(head21);
        System.out.println("合并后");
        System.out.println(merge(head11NULL,head21));

        System.out.println();

        ListNode<Integer> head21NULL = null;


        System.out.println("合并前");
        System.out.println(head11NULL);
        System.out.println(head21NULL);
        System.out.println("合并后");
        System.out.println(merge(head11NULL,head21NULL));


    }
}
