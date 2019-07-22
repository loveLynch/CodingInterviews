package com.lynch.structure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by lynch on 2019/3/1. <br>
 * 二叉树节点
 **/
public class BinaryTreeNode<T> {
    public T val;
    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;

    public BinaryTreeNode(T val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    //层序遍历
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        queue.offer(this);
        BinaryTreeNode<T> temp;
        while(!queue.isEmpty()){
            temp = queue.poll();
            stringBuilder.append(temp.val);
            stringBuilder.append(",");
            if(temp.left!=null)
                queue.offer(temp.left);
            if(temp.right!=null)
                queue.offer(temp.right);
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
