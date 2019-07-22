package com.lynch;

import com.lynch.structure.ListNode;

import java.util.Stack;

/**
 * Created by lynch on 2019-04-01. <br>
 * 52.两个链表的第一个公共节点
 * <p>
 * 输入两个单链表，找出它们的第一个公共节点。以下图为例，对一个公共节点为6所在的节点。
 * 1 -> 2 -> 3 -> 6 -> 7
 * 4 -> 5 ↗
 **/
public class CommonNodesInList_52 {
    /**
     * 思路一：
     * 暴力解决，时间复杂度o(mn)，空间复杂度o(1)
     *
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode<Integer> findFirstCommonNode(ListNode<Integer> head1, ListNode<Integer> head2) {
        for (ListNode<Integer> node1 = head1; node1 != null; node1 = node1.next) {
            for (ListNode<Integer> node2 = head2; node2 != null; node2 = node2.next) {
                if (node1 == node2)
                    return node1;
            }
        }
        return null;
    }

    /**
     * 思路二：
     * 借助于两个栈，时间复杂度o(m+n)，空间复杂度o(m+n)
     *
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode<Integer> findFirstCommonNode2(ListNode<Integer> head1, ListNode<Integer> head2) {
        Stack<ListNode<Integer>> stack1 = new Stack<>();
        Stack<ListNode<Integer>> stack2 = new Stack<>();
        for (ListNode<Integer> node1 = head1; node1 != null; node1 = node1.next)
            stack1.push(node1);
        for (ListNode<Integer> node2 = head2; node2 != null; node2 = node2.next)
            stack2.push(node2);
        ListNode<Integer> commonNode = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            if (stack1.peek() == stack2.peek()) {
                commonNode = stack1.pop();
                stack2.pop();
            } else
                break;
        }
        return commonNode;
    }

    /**
     * 思路三：
     * 转化为等长链表，时间复杂度o(m+n)，空间复杂度o(1)
     *
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode<Integer> findFirstCommonNode3(ListNode<Integer> head1, ListNode<Integer> head2) {
        ListNode<Integer> node1 = head1, node2 = head2;
        int size1 = 0, size2 = 0;
        for (; node1 != null; node1 = node1.next)
            size1++;
        for (; node2 != null; node2 = node2.next)
            size2++;
        node1 = head1;
        node2 = head2;
        //让两个链表等长
        while (size1 > size2) {
            node1 = node1.next;
            size1--;
        }
        while (size2 > size1) {
            node2 = node2.next;
            size2--;
        }
        //同时向后遍历，查找共同节点
        while (node1 != null) {
            if (node1 != node2) {
                node1 = node1.next;
                node2 = node2.next;
            } else
                break;
        }
        return node1;
    }

    public static void main(String[] args) {
        // 1->2->3->6->7
        //    4->5↗
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        ListNode<Integer> node4 = new ListNode<>(4);
        ListNode<Integer> node5 = new ListNode<>(5);
        ListNode<Integer> node6 = new ListNode<>(6);
        ListNode<Integer> node7 = new ListNode<>(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node6;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        ListNode<Integer> commonNode = findFirstCommonNode(node1, node4);
        System.out.println(commonNode.val);
        ListNode<Integer> commonNode2 = findFirstCommonNode2(node1, node4);
        System.out.println(commonNode2.val);
        ListNode<Integer> commonNode3 = findFirstCommonNode3(node1, node4);
        System.out.println(commonNode3.val);
    }
}
