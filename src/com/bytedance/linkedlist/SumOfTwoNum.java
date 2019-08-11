package com.bytedance.linkedlist;

/**
 * @author Lynch
 * @date 2019/8/8 10:24
 * 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class SumOfTwoNum {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        System.out.println(addTwoNumbers(l1, l2).toString());
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sum = new ListNode(0);
        ListNode temp = sum;
        int carry = 0;//进位标志，不进位为0，进位为1
        //只有当两个链表当前位都为空且没有进位产生时才退出
        while (l1 != null || l2 != null || carry == 1) {
            //next为空时，val设置为0，否则为当前值
            int lie = ((l1 != null) ? l1.val : 0) + ((l2 != null) ? l2.val : 0) + carry;
            carry = lie / 10;
            temp.next = new ListNode(lie % 10);
            temp = temp.next;
            l1 = (l1 != null) ? l1.next : l1;
            l2 = (l2 != null) ? l2.next : l2;
        }
        return sum.next;//去掉首位的0
    }

}
