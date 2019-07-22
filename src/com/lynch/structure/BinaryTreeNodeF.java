package com.lynch.structure;

/**
 * Created by lynch on 2019/3/6. <br>
 * 二叉树节点带父节点
 **/
public class BinaryTreeNodeF {
    public char val;
    public BinaryTreeNodeF left;
    public BinaryTreeNodeF right;
    public BinaryTreeNodeF father;

    public BinaryTreeNodeF(char val) {
        this.val = val;
        this.left = null;
        this.right = null;
        this.father = null;
    }
}
