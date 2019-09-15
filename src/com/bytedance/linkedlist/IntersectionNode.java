package com.bytedance.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Lynch
 * @date 2019/8/8 17:38
 * 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * 如下面的两个链表：
 * <p>
 * <p>
 * <p>
 * 在节点 c1 开始相交。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 * <p>
 * <p>
 * 注意：
 * <p>
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class IntersectionNode {
    public static void main(String[] args) {
        ListNode inter = new ListNode(8);

        ListNode headA = new ListNode(4);
        ListNode a1 = new ListNode(1);
        ListNode a3 = new ListNode(4);
        ListNode a4 = new ListNode(5);
        headA.next = a1;
        a1.next = inter;
        inter.next = a3;
        a3.next = a4;
        ListNode headB = new ListNode(5);
        ListNode b1 = new ListNode(0);
        ListNode b2 = new ListNode(1);
        ListNode b4 = new ListNode(4);
        ListNode b5 = new ListNode(5);
        headB.next = b1;
        b1.next = b2;
        b2.next = inter;
        inter.next = b4;
        b4.next = b5;
        System.out.println(getIntersectionNode1(headA, headB).val);
        System.out.println(getIntersectionNode2(headA, headB).val);

    }

    /**
     * 利用set集合存储a链表的地址，再遍历b链表每个节点，碰到相等的
     * 就返回
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        Set<ListNode> headASet = new HashSet<>();
        while (headA != null) {
            headASet.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (headASet.contains(headB))
                return headB;
            headB = headB.next;
        }
        return null;
    }

    /**
     * 利用两个指针分别指向a，b的头，分别遍历a，b中所有不重复的节点，那么，两个指针走的长度肯定是相等的
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if(headA==null||headB==null) {
            return null;
        }
        ListNode a = headA,b = headB;
        while(a!=b) {
            a = a==null?headB:a.next;
            b = b==null?headA:b.next;
        }
        return a;
    }
}