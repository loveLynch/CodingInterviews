package com.lynch;

import com.lynch.structure.ComplexListNode;

import java.util.HashMap;

/**
 * Created by lynch on 2019-03-20. <br>
 * 35.复杂链表的复制 *****
 * 复制一个复杂链表，在链表中，每个节点除了有一个pNext指针指向下一个节点，
 * 还有一个pSibling指针指向链表中的任意节点或者null
 **/
public class ComplexListNodeCopy_35 {
    /**
     * 解法一
     * time:o(n^2) space:o(1)
     * 新链表使用的n个长度的空间不算入
     * 先复制val与next（时间o(n)），再复制sibling域（时间o(n^2)）
     *
     * @param head
     * @return
     */

    public static ComplexListNode copy1(ComplexListNode head) {
        if (head == null)
            return null;
        ComplexListNode newHead = new ComplexListNode(head.val);
        ComplexListNode cur = head.next;
        ComplexListNode newCur = null;
        ComplexListNode newCurPrev = newHead;
        while (cur != null) {
            newCur = new ComplexListNode(cur.val);
            newCurPrev.next = newCur;
            newCurPrev = newCurPrev.next;
            cur = cur.next;
        }
        cur = head;
        newCur = newHead;
        ComplexListNode temp = head;
        ComplexListNode newTemp = newHead;
        while (cur != null) {
            if (cur.sibling != null) {
                temp = head;
                newTemp = newHead;
                while (temp != cur.sibling) {
                    temp = temp.next;
                    newTemp = newTemp.next;
                }
                newCur.sibling = newTemp;
            }
            cur = cur.next;
            newCur = newCur.next;
        }
        return newHead;
    }

    /**
     * 解法二
     * time:o(n) space:o(n)
     * 使用o(n)的空间，换取了时间复杂度的降低,使用一个O(n)大小的哈希表
     *
     * @param head
     * @return
     */
    public static ComplexListNode copy2(ComplexListNode head) {
        if (head == null)
            return null;
        HashMap<ComplexListNode, ComplexListNode> oldToNew = new HashMap<>();
        ComplexListNode newHead = new ComplexListNode(head.val);
        oldToNew.put(head, newHead);
        ComplexListNode cur = head.next;
        ComplexListNode newCur = null;
        ComplexListNode newCurPrev = newHead;
        while (cur != null) {
            newCur = new ComplexListNode(cur.val);
            oldToNew.put(cur, newCur);
            newCurPrev.next = newCur;
            newCurPrev = newCurPrev.next;
            cur = cur.next;
        }
        cur = head;
        newCur = newHead;
        while (cur != null) {
            if (cur.sibling != null) {
                newCur.sibling = oldToNew.get(cur.sibling);
            }
            cur = cur.next;
            newCur = newCur.next;
        }
        return newHead;
    }

    /**
     * 解法三：共三步走
     * time:o(n) space:o(1)
     * 1）cloneNodes完成新链表节点的创建，仅对val域赋值，且每个新节点接在原链表对应节点的后面。如A->B->C,处理完后为A->A'->B->B'->C->C'，时间复杂度o(n)。
     * 2）connectRandomNode完成random域的赋值。假设A.random=C,我们需要设置A'.random=C'，此处获取C'可以在o(1)的时间复杂度完成，全部赋值完毕时间复杂度为o(n)。
     * 3）reconnectNodes就是将上述链表重组，使A->A'->B->B'->C->C'变为A->B->C，A'->B'->C'。此处需要注意尾部null的处理
     *
     * @param head
     * @return
     */
    public static ComplexListNode copy3(ComplexListNode head) {
        cloneNodes(head);
        connectSiblingNodes(head);
        return reconnectNodes(head);
    }


    /**
     * 第一步：复制原始链表中的任意节点N并创建节点N',再把N'链接到N的后面
     *
     * @param head
     */
    private static void cloneNodes(ComplexListNode head) {
        ComplexListNode node = head;
        while (node != null) {
            ComplexListNode cloned = null;
            cloned = new ComplexListNode(node.val);
            cloned.next = node.next;
            cloned.sibling = node.sibling;

            node.next = cloned;

            node = cloned.next;

        }

    }

    /**
     * 第二步：
     * 设置复制出来的sibling节点
     * cconnectSiblingNodes完成sibling域的赋值。
     * 假设A.random=C,我们需要设置A'.random=C'，
     * 此处获取C'可以在o(1)的时间复杂度完成，全部赋值完毕时间复杂度为o(n)
     *
     * @param head
     */
    private static void connectSiblingNodes(ComplexListNode head) {
        ComplexListNode node = head;
        while (node != null) {
            ComplexListNode cloned = node.next;
            if (node.sibling != null) {
                cloned.sibling = node.sibling.next;
            }
            node = cloned.next;
        }

    }

    /**
     * 第三步：把第二步得到的链表分成两个链表，奇数位置上的节点组成原始链表
     * 偶数位置上的的组成复制出来的链表
     *
     * @param head
     * @return
     */
    private static ComplexListNode reconnectNodes(ComplexListNode head) {
        ComplexListNode node = head;
        ComplexListNode clonedHead = null;
        ComplexListNode clonedNode = null;
        if (node != null) {
            clonedHead = clonedNode = node.next;
            node.next = clonedNode.next;
            node = node.next;
        }
        while (node != null) {
            clonedNode.next = node.next;
            clonedNode = clonedNode.next;
            node = clonedNode.next;

        }
        return clonedHead;

    }

    public static void main(String[] args) {
        ComplexListNode head = new ComplexListNode(1);
        ComplexListNode c2 = new ComplexListNode(2);
        ComplexListNode c3 = new ComplexListNode(3);
        ComplexListNode c4 = new ComplexListNode(4);
        ComplexListNode c5 = new ComplexListNode(5);
        head.next = c2;
        head.sibling = c3;
        head.next.next = c3;
        head.next.sibling = c5;
        head.next.next.next = c4;
        head.next.next.next.next = c5;
        head.next.next.next.sibling = c2;
        System.out.println("original:" + '\t' + head);
        System.out.println("copy1:  " + '\t' + copy1(head));
        System.out.println("copy2:  " + '\t' + copy2(head));
        System.out.println("copy3:  " + '\t' + copy3(head));
    }


}
