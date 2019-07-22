package com.lynch;

import com.lynch.structure.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by lynch on 2019/3/19. <br>
 * 32.层次遍历打印二叉树
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印 ，每一层打印一行。
 **/
public class PrintBinaryTree_32_1 {
    /**
     * 可以用队列来实现
     * 每次打印一个节点的时候，如果该节点有子节点，则把该节点放入一个队列的末尾
     * 则接下来队列的头部取出最早进入队列的节点，重复前面的打印操作
     *
     * @param root
     */
    private static void printBinaryTreeNode(BinaryTreeNode<Integer> root) {
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        BinaryTreeNode<Integer> temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            System.out.print(temp.val + "\t");
            if (temp.left != null)
                queue.offer(temp.left);
            if (temp.right != null)
                queue.offer(temp.right);
        }

    }

    public static void main(String[] args) {
        //            1
        //          /   \
        //         2     3
        //       /  \   / \
        //      4    5 6   7
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1);
        root.left = new BinaryTreeNode<Integer>(2);
        root.right = new BinaryTreeNode<Integer>(3);
        root.left.left = new BinaryTreeNode<Integer>(4);
        root.left.right = new BinaryTreeNode<Integer>(5);
        root.right.left = new BinaryTreeNode<Integer>(6);
        root.right.right = new BinaryTreeNode<Integer>(7);
        printBinaryTreeNode(root);
    }
}
