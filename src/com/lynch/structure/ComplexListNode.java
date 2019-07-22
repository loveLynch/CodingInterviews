package com.lynch.structure;

/**
 * Created by lynch on 2019-03-20. <br>
 **/
public class ComplexListNode {
    public int val;
    public ComplexListNode next;
    public ComplexListNode sibling;

    public ComplexListNode(int val) {
        this.val = val;
    }


    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ComplexListNode cur = this;
        while (cur != null) {
            ret.append(cur.val);
            if (cur.sibling != null)
                ret.append("(" + cur.sibling.val + ")");
            else {
                ret.append("(_)");
            }
            ret.append('\t');
            cur = cur.next;
        }
        return ret.toString();
    }
}