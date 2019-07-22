package com.lynch;

import com.lynch.structure.ListNode;

/**
 * Created by lynch on 2019/3/13. <br>
 * 18.2删除链表中重复的节点
 * 在一个排序的链表中，删除重复的节点
 **/
public class DeleteDuplicationNode_18_2 {
    /**
     * 剑指Offer
     * 删除重复节点
     *
     * @param pHead
     * @return
     */
    private static ListNode<Integer> deleteDuplication_ONE(ListNode<Integer> pHead) {
//        if (pHead==null)
        if (pHead == null && pHead.next == null)
            return pHead;
        ListNode<Integer> pPreNode = null; //当前节点的前一个节点
        ListNode<Integer> pNode = pHead; //当前节点
        while (pNode != null) {
            ListNode<Integer> pNext = pNode.next; //下一个节点
            boolean needDelete = false;
            if (pNext != null && pNext.val == pNode.val)
                needDelete = true;
            if (!needDelete) { //不需要删除继续向后遍历
                pPreNode = pNode;
                pNode = pNode.next;
            } else {
                int value = pNode.val;
                ListNode<Integer> pToBeDel = pNode;
                while (pToBeDel != null && pToBeDel.val == value) {
                    pNext = pToBeDel.next;
                    pToBeDel = pNext;
                }
                if (pPreNode == null)
                    pHead = pNext;
                else {
                    pPreNode.next = pNext;
                }
                pNode = pNext;
            }
        }
        return pHead;

    }

    /**
     * 简书博客
     *
     * @param head
     * @return
     */
    public static ListNode<Integer> deleteDuplication_TWO(ListNode<Integer> head) {
        if (head == null || head.next == null)
            return head;
        ListNode<Integer> pre = null;
        ListNode<Integer> cur = head;
        ListNode<Integer> post = head.next;
        boolean needDelete = false;
        while (post != null) {
            if (cur.val.equals(post.val)) {
                needDelete = true;
                post = post.next;
            } else if (needDelete && !cur.val.equals(post.val)) {
                if (pre == null)
                    head = post;
                else
                    pre.next = post;
                cur = post;
                post = post.next;
                needDelete = false;
            } else {
                pre = cur;
                cur = post;
                post = post.next;
            }
        }
        if (needDelete && pre != null)
            pre.next = null;
        else if (needDelete && pre == null)
            head = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        ListNode<Integer> node4 = new ListNode<>(3);
        ListNode<Integer> node5 = new ListNode<>(4);
        ListNode<Integer> node6 = new ListNode<>(4);
        ListNode<Integer> node7 = new ListNode<>(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        System.out.println(head);

//        head = deleteDuplication_ONE(head);
//        System.out.println(head);

        head = deleteDuplication_TWO(head);
        System.out.println(head);

    }
}
