package com.bytedance.linkedlist;

/**
 * @author Lynch
 * @date 2019/8/8 15:41
 *  环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 说明：不允许修改给定的链表。
 *
 *
 *
 * 示例 1：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 *
 * 示例 2：
 *
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 *
 * 示例 3：
 *
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 */
public class FindCycleList {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = head;
        System.out.println(detectCycle(head).val);

    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slowIndex = head; //慢指针，一步走
        ListNode fastIndex = head; //快指针，两步走

        while (fastIndex != null && fastIndex.next != null) {
            slowIndex = slowIndex.next;
            fastIndex = fastIndex.next.next;
            //相遇节点，一定有环
            if (slowIndex == fastIndex) {
                slowIndex = head; //此时将慢节点置于起点
                //当两个节点再次相遇时，即为环的入口节点
                while (slowIndex != fastIndex) {
                    slowIndex = slowIndex.next;
                    fastIndex = fastIndex.next;
                }
                return slowIndex;
            }
        }
        return null;
    }
}
