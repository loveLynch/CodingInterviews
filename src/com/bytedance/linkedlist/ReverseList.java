package com.bytedance.linkedlist;

import java.util.List;

/**
 * @author Lynch
 * @date 2019/8/8 9:38
 * 反转链表
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class ReverseList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println("original " + head.toString());
        System.out.println("reverse1 " + reverseList1(head).toString());
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        System.out.println("reverse2 " + reverseList2(head2).toString());
    }

    /**
     * 迭代
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null)
            return head;
        ListNode pre = null;
        ListNode cur = head;
        ListNode post = head.next;
        while (true) {
            cur.next = pre;
            pre = cur;
            cur = post;
            if (post != null) {
                post = post.next;
            } else {
                return pre;
            }
        }

    }

    /**
     * 迭代
     *
     * @param head
     * @return
     */
    public static ListNode reverseList1(ListNode head) {
        ListNode reverse = null;
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode post = cur.next;//保存当前节点的下一个节点
            if (post == null)
                reverse = cur;
            //保存完post，就可以让cur从指向npost变成指向pre了
            cur.next = pre;
            //cur指向pre后，就继续依次反转下一个节点
            //让pre，cur，next依次向后移动一个节点，继续下一次的指针反转
            pre = cur;
            cur = post;

        }
        return reverse;
    }

    /**
     * 递归
     *
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        //如果链表为空或者链表中只有一个元素
        if (head == null || head.next == null) return head;
        //先递归找到到链表的末端结点，从后依次反转整个链表
        ListNode reverseHead = reverseList2(head.next);
        //再将当前节点设置为后面节点的后续节点
        head.next.next = head;
        head.next = null;
        return reverseHead;
    }

}
