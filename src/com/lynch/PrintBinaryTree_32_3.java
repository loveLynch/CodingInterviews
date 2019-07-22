package com.lynch;

import com.lynch.structure.BinaryTreeNode;

import java.util.Stack;

/**
 * Created by lynch on 2019/3/19. <br>
 * 32.之字形打印二叉树
 * 请实现一个函数按照之字形打印二叉树。即
 * 第一层从左到右打印，第二层从右到左打印，第三层继续从左到右，以此类推
 **/
public class PrintBinaryTree_32_3 {
    /**
     * 可以用两个栈来实现
     * 第k行从左到右打印，第k+1行从右到左打印
     * 注意:根据是从左到右还是从右到左访问的不同，压入左右子节点的顺序也有所不同。
     *
     * @param root
     */
    private static void printBinaryTreeNode(BinaryTreeNode<Integer> root) {
        if (root == null)
            return;
        Stack<BinaryTreeNode<Integer>> stack1 = new Stack<>();
        Stack<BinaryTreeNode<Integer>> stack2 = new Stack<>();
        BinaryTreeNode<Integer> temp;
        stack1.push(root);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (!stack1.isEmpty()) {
                while (!stack1.isEmpty()) {
                    temp = stack1.pop();
                    System.out.print(temp.val + "\t");
                    //注意左右节点压栈顺序，奇数层先左子节点再右子节点
                    if (temp.left != null) {
                        stack2.push(temp.left);
                    }
                    if (temp.right != null) {
                        stack2.push(temp.right);
                    }
                }
            } else {
                while (!stack2.isEmpty()) {
                    temp = stack2.pop();
                    System.out.print(temp.val + "\t");
                    //注意左右节点压栈顺序，偶数层先右子节点再左子节点
                    if (temp.right != null)
                        stack1.push(temp.right);
                    if (temp.left != null)
                        stack1.push(temp.left);
                }
            }
            //按层分行
            System.out.println();
        }


    }

    public static void main(String[] args) {
        //            1
        //          /   \
        //         2       3
        //       /  \     / \
        //     4     5   6     7
        //    / \   /\   /\   / \
        //   8  9 10 11 12 13 14 15
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1);
        root.left = new BinaryTreeNode<Integer>(2);
        root.right = new BinaryTreeNode<Integer>(3);
        root.left.left = new BinaryTreeNode<Integer>(4);
        root.left.right = new BinaryTreeNode<Integer>(5);
        root.right.left = new BinaryTreeNode<Integer>(6);
        root.right.right = new BinaryTreeNode<Integer>(7);
        root.left.left.left = new BinaryTreeNode<Integer>(8);
        root.left.left.right = new BinaryTreeNode<Integer>(9);
        root.left.right.left = new BinaryTreeNode<Integer>(10);
        root.left.right.right = new BinaryTreeNode<Integer>(11);
        root.right.left.left = new BinaryTreeNode<Integer>(12);
        root.right.left.right = new BinaryTreeNode<Integer>(13);
        root.right.right.left = new BinaryTreeNode<Integer>(14);
        root.right.right.right = new BinaryTreeNode<Integer>(15);




        printBinaryTreeNode(root);
    }
}
