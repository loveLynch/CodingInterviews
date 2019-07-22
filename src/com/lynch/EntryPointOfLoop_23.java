package com.lynch;

import com.lynch.structure.ListNode;

/**
 * Created by lynch on 2019/3/14. <br>
 * 23.链表中环的入口节点
 * <p>
 * 找出有环链表中环的入口节点
 **/
public class EntryPointOfLoop_23 {
    /**
     * 链表中存在环的前提下
     * 找到快节点与慢节点相遇的节点
     *
     * @param pHead
     * @return
     */
    private static ListNode<Integer> meetingNode(ListNode pHead) {
        if (pHead == null)
            return null;
        ListNode<Integer> pSlow = pHead.next;//一步走节点
        if (pSlow == null)
            return null;
        ListNode<Integer> pFast = pSlow.next;//两步走节点
        while (pFast != null && pSlow != null) {
            //相遇节点
            if (pFast == pSlow)
                return pFast;
            //继续向前走
            pSlow = pSlow.next;
            pFast = pFast.next;
            //快节点不为空，证明有环
            if (pFast != null)
                pFast = pFast.next;
        }
        return null;
    }

    /**
     * 在找到环中任意节点之后，继续向前走回到原点即可得到节点环中节点数量
     *
     * @param pHead
     * @return
     */
    private static ListNode<Integer> entryPointofLoop(ListNode<Integer> pHead) {
        ListNode<Integer> meetingNode = meetingNode(pHead);
        if (meetingNode == null)
            return null;
        //得到环中节点数目
        int nodesInLoop = 1;
        ListNode<Integer> pNode1 = meetingNode;
        while (pNode1.next != meetingNode) {
            pNode1 = pNode1.next;
            nodesInLoop++;
        }

        //先移动pNode1,次数为环中节点数目
        pNode1 = pHead;//链表起点
        for (int i = 0; i < nodesInLoop; i++) {
            pNode1 = pNode1.next;
        }
        //再移动pNode1和pNode2,当两节点相遇节点即为入环节点
        ListNode<Integer> pNode2 = pHead;//链表起点
        while (pNode1 != pNode2) {
            pNode1 = pNode1.next;
            pNode2 = pNode2.next;

        }
        return pNode1;
    }


    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        ListNode<Integer> node4 = new ListNode<>(4);
        ListNode<Integer> node5 = new ListNode<>(5);
        ListNode<Integer> node6 = new ListNode<>(6);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node3;//入环节点node3,即val=3

        //        ________
        //       |        |
        // 1->2->3->4->5->6

        System.out.println("入环节点:" + entryPointofLoop(head).val);
    }
}
