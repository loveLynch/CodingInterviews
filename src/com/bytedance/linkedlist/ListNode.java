package com.bytedance.linkedlist;

/**
 * @author Lynch
 * @date 2019/8/8 9:22
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("[");
        for (ListNode cur = this; ; cur = cur.next) {
            if (cur == null) {
                ret.deleteCharAt(ret.lastIndexOf(" "));
                ret.deleteCharAt(ret.lastIndexOf(","));
                break;
            }
            ret.append(cur.val);
            ret.append(", ");
        }
        ret.append("]");
        return ret.toString();
    }
}