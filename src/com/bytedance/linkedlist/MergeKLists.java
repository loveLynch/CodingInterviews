package com.bytedance.linkedlist;

/**
 * @author Lynch
 * @date 2019/8/9 11:57
 * 合并K个排序链表
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class MergeKLists {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);
        ListNode[] lists = new ListNode[3];
        lists[0] = l1;
        lists[1] = l2;
        lists[2] = l3;

        ListNode mergeK = mergeKLists(lists);
        System.out.println(mergeK.toString());

    }

    /**
     * 将返回的结果与每一个链表排序
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0 || lists == null)
            return null;
        ListNode result = lists[0];
        for (int i = 1; i < lists.length; i++) {
            result = mergeTwoLists(result, lists[i]);
        }
        return result;
    }

    /**
     * 递归两个链表排序
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
