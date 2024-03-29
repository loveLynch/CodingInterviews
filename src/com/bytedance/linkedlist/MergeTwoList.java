package com.bytedance.linkedlist;

/**
 * @author Lynch
 * @date 2019/8/8 9:23
 *  合并两个有序链表
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class MergeTwoList {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        System.out.println(mergeTwoLists(l1, l2).toString());

    }

    /**
     * 递归
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode merge = null;
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if (l1.val > l2.val) {
            merge = l2;
            merge.next = mergeTwoLists(l1, l2.next);
        } else {
            merge = l1;
            merge.next = mergeTwoLists(l1.next, l2);

        }

        return merge;
    }
}
