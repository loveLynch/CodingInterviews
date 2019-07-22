package com.lynch;

import com.lynch.structure.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by lynch on 2019/3/19. <br>
 * 32.分行从上到下打印二叉树
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印 ，每一层打印一行。
 **/
public class PrintBinaryTree_32_2 {
    /**
     * 可以用队列来实现
     * 同样是层序遍历，与上一题不同的是，此处要记录每层的节点个数，
     * 在每层打印结束后多打印一个回车符。
     *
     * @param root
     */
    private static void printBinaryTreeNodeLine(BinaryTreeNode<Integer> root) {
        if (root == null)
            return;
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        BinaryTreeNode<Integer> temp;
        while (!queue.isEmpty()) {
            for (int size = queue.size(); size > 0; size--) {
                temp = queue.poll();
                System.out.print(temp.val + "\t");
                if (temp.left != null)
                    queue.offer(temp.left);
                if (temp.right != null)
                    queue.offer(temp.right);
            }
            System.out.println();
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
        printBinaryTreeNodeLine(root);
    }
}
