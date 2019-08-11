package com.bytedance.linkedlist;

/**
 * @author Lynch
 * @date 2019/8/8 14:51
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
public class SortList {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        System.out.println("original " + head.toString());
//        System.out.println("sort " + sortList(head).toString());
        System.out.println("sort " + sortList1(head).toString());
//        System.out.println("sort " + sortList2(head).toString());
    }

    /**
     * 每次插入的时候都要从第一个元素开始遍历 O(n^2)
     *
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if (head == null)
            return head;
        ListNode sort = new ListNode(0);//定义一个链表用于存储排好序的链表
        ListNode cur = head, pre = sort, post;
        while (cur != null) {
            //保存当前节点的后面的链表
            post = cur.next;
            //当前元素后面插入
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            //插入到相应位置
            cur.next = pre.next;
            pre.next = cur;
            //恢复pre和cur的值
            pre = sort;
            cur = post;
        }
        return sort.next;
    }

    /**
     * 快速排序
     *
     * @param head
     * @return
     */
    public static ListNode sortList1(ListNode head) {
        quickSort(head, null);
        return head;
    }

    public static void quickSort(ListNode head, ListNode end) {
        if (head != end) {
            ListNode node = partion(head, end);
            quickSort(head, node);
            quickSort(node.next, end);
        }
    }

    public static ListNode partion(ListNode head, ListNode end) {
        ListNode p1 = head, p2 = head.next;

        //走到末尾才停
        while (p2 != end) {

            //大于key值时，p1向前走一步，交换p1与p2的值
            if (p2.val < head.val) {
                p1 = p1.next;

                int temp = p1.val;
                p1.val = p2.val;
                p2.val = temp;
            }
            p2 = p2.next;
        }

        //当有序时，不交换p1和key值
        if (p1 != head) {
            int temp = p1.val;
            p1.val = head.val;
            head.val = temp;
        }
        return p1;
    }

    /**
     * 归并排序
     *
     * @param head
     * @return
     */
    public static ListNode sortList2(ListNode head) {
        //采用归并排序
        if (head == null || head.next == null) {
            return head;
        }
        //获取中间结点
        ListNode mid = getMid(head);
        ListNode right = mid.next;
        mid.next = null;
        //合并
        return mergeSort(sortList(head), sortList(right));
    }

    /**
     * 获取链表的中间结点,偶数时取中间第一个
     *
     * @param head
     * @return
     */
    private static ListNode getMid(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //快慢指针
        ListNode slow = head, quick = head;
        //快2步，慢一步
        while (quick.next != null && quick.next.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;
    }

    /**
     * 归并两个有序的链表
     *
     * @param head1
     * @param head2
     * @return
     */
    private static ListNode mergeSort(ListNode head1, ListNode head2) {
        ListNode p1 = head1, p2 = head2, head;
        //得到头节点的指向
        if (head1.val < head2.val) {
            head = head1;
            p1 = p1.next;
        } else {
            head = head2;
            p2 = p2.next;
        }

        ListNode p = head;
        //比较链表中的值
        while (p1 != null && p2 != null) {

            if (p1.val <= p2.val) {
                p.next = p1;
                p1 = p1.next;
                p = p.next;
            } else {
                p.next = p2;
                p2 = p2.next;
                p = p.next;
            }
        }
        //第二条链表空了
        if (p1 != null) {
            p.next = p1;
        }
        //第一条链表空了
        if (p2 != null) {
            p.next = p2;
        }
        return head;
    }
}
