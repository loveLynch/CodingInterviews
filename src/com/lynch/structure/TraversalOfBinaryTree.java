package com.lynch.structure;

/**
 * Created by lynch on 2019/3/4. <br>
 **/

import java.util.*;

/**
 * Created by ryder on 2017/6/13.
 * 二叉树的遍历：
 * 前序（递归，非递归），中序（递归，非递归），后序（递归，非递归），层序
 */
public class TraversalOfBinaryTree {
    //前序遍历递归版
    public static List<Integer> preorderRecursively(BinaryTreeNode<Integer> node) {
        List<Integer> list = new ArrayList<>();
        if (node == null)
            return list;
        list.add(node.val);
        list.addAll(preorderRecursively(node.left));
        list.addAll(preorderRecursively(node.right));
        return list;
    }

    //中序遍历递归版
    public static List<Integer> inorderRecursively(BinaryTreeNode<Integer> node) {
        List<Integer> list = new ArrayList<>();
        if (node == null)
            return list;
        list.addAll(inorderRecursively(node.left));
        list.add(node.val);
        list.addAll(inorderRecursively(node.right));
        return list;
    }

    //后序遍历递归版
    public static List<Integer> postorderRecursively(BinaryTreeNode<Integer> node) {
        List<Integer> list = new ArrayList<>();
        if (node == null)
            return list;
        list.addAll(postorderRecursively(node.left));
        list.addAll(postorderRecursively(node.right));
        list.add(node.val);
        return list;
    }

    //前序遍历非递归版
    public static List<Integer> preorderIteratively(BinaryTreeNode<Integer> node) {
        //stack栈顶元素永远为cur的父节点
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        BinaryTreeNode<Integer> cur = node;
        List<Integer> list = new LinkedList<>();
        if (node == null)
            return list;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                list.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop().right;
            }
        }
        return list;
    }

    //中序遍历非递归版
    public static List<Integer> inorderIteratively(BinaryTreeNode<Integer> node) {
        //stack栈顶元素永远为cur的父节点
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        BinaryTreeNode<Integer> cur = node;
        List<Integer> list = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                list.add(stack.peek().val);
                cur = stack.pop().right;
            }
        }
        return list;
    }

    //后序遍历非递归版
    public static List<Integer> postorderIteratively(BinaryTreeNode<Integer> node) {
        //stack栈顶元素永远为cur的父节点
        //prevVisted用于区分是从左子树还是右子树返回的
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        BinaryTreeNode<Integer> cur = node;
        BinaryTreeNode<Integer> prevVisted = null;
        List<Integer> list = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.peek().right;
                if (cur != null && cur != prevVisted) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    prevVisted = stack.pop();
                    list.add(prevVisted.val);
                    cur = null;
                }
            }
        }
        return list;
    }

    //层序遍历
    public static List<Integer> levelorder(BinaryTreeNode<Integer> node) {
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        BinaryTreeNode<Integer> temp = null;
        if (node == null)
            return list;
        queue.add(node);
        while (!queue.isEmpty()) {
            temp = queue.poll();
            list.add(temp.val);
            if (temp.left != null)
                queue.offer(temp.left);
            if (temp.right != null)
                queue.offer(temp.right);
        }
        return list;
    }
}