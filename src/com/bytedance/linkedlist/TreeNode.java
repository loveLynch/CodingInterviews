package com.bytedance.linkedlist;

import com.lynch.structure.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Lynch
 * @date 2019/8/9 12:14
 * 二叉树
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    //层序遍历
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        TreeNode temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            stringBuilder.append(temp.val);
            stringBuilder.append(",");
            if (temp.left != null)
                queue.offer(temp.left);

            if (temp.right != null)
                queue.offer(temp.right);
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}