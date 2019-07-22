package com.lynch;

import com.lynch.structure.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by lynch on 2019-04-01. <br>
 * 55.二叉树的深度
 * <p>
 * 求二叉树的深度。仅仅包含一个根节点的二叉树深度为1。
 * <p>
 * 思路：二叉树root的深度比其子树root.left与root.right的深度的最大值大1。因此可以通过上述结论递归求解。
 * 如果不使用递归，可以通过层序遍历（广度优先遍历）解决
 **/
public class BinaryTreeDepth_55 {
    //递归
    public static int treeDepth(BinaryTreeNode<Integer> root){
        if(root==null)
            return 0;
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        return left>right?(left+1):(right+1);
    }
    //非递归，广度优先/层序遍历
    public static int treeDepth2(BinaryTreeNode<Integer> root){
        if(root==null)
            return 0;
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            BinaryTreeNode<Integer> cur = null;
            for(int i=0;i<size;i++){
                cur = queue.poll();
                if(cur.left!=null) queue.offer(cur.left);
                if(cur.right!=null) queue.offer(cur.right);
            }
            depth++;
        }
        return depth;

    }
    public static void main(String[] args){
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1);
        root.left = new BinaryTreeNode<>(2);
        root.left.left = new BinaryTreeNode<>(4);
        root.left.right = new BinaryTreeNode<>(5);
        root.left.right.left = new BinaryTreeNode<>(7);
        root.right = new BinaryTreeNode<>(3);
        root.right.right = new BinaryTreeNode<>(6);
        System.out.println(treeDepth(root));
        System.out.println(treeDepth2(root));
    }
}
