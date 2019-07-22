package com.lynch;

import com.lynch.structure.BinaryTreeNode;


/**
 * Created by lynch on 2019-03-20. <br>
 * 36.二叉搜索树与双向链表
 * <p>
 * 输入一颗二叉搜索树，将该二叉搜索树转换成一个排序的双向链表，
 * 不能创建任何新的节点，只能调整树中节点指针的指向。
 **/
public class ConvertBinarySearchTree_36 {
    /**
     * 思路：中序遍历
     * 递归
     * 二叉树的left、right可以看做双向链表的prev、next，
     * 因此可以完成二叉搜索树到双向链表的转换，关键是如何保证转换后为排序好的链表。
     *
     * @param pRoot
     * @return
     */
    private static BinaryTreeNode<Integer> convert(BinaryTreeNode<Integer> pRoot) {
        if (pRoot == null)
            return null;

        //处理二叉搜索树
        BinaryTreeNode<Integer> pLastNodeInList = null;
        BinaryTreeNode<Integer> pHead = convertNode(pRoot, pLastNodeInList);

        //找到转换后的链表的头节点
        while (pHead != null && pHead.left != null) {
            pHead = pHead.left;
        }

        //返回头节点
        return pHead;
    }

    private static BinaryTreeNode<Integer> convertNode(BinaryTreeNode<Integer> pNode, BinaryTreeNode<Integer> pLastNodeInList) {
        if (pNode == null)
            return null;
        BinaryTreeNode<Integer> pCurrent = pNode;

        //递归处理左子树
        if (pCurrent.left != null)
            pLastNodeInList = convertNode(pNode.left, pLastNodeInList);

        //处理当前节点
        //将当前节点的左指针指向已经转换好的链表的最后一个位置
        pCurrent.left = pLastNodeInList;
        //将已经转换好的链表的最后一个节点的右指针指向当前节点
        if (pLastNodeInList != null)
            pLastNodeInList.right = pCurrent;
        //更新已经转换好的链表的最后一个节点
        pLastNodeInList = pCurrent;

        //递归处理右子树
        if (pCurrent.right != null)
            pLastNodeInList = convertNode(pNode.right, pLastNodeInList);
        return pLastNodeInList;

    }

    public static void main(String[] args) {
        //            10
        //          /   \
        //         6     14
        //       /  \   / \
        //      4    8 12  16
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(10);
        root.left = new BinaryTreeNode<Integer>(6);
        root.right = new BinaryTreeNode<Integer>(14);
        root.left.left = new BinaryTreeNode<Integer>(4);
        root.left.right = new BinaryTreeNode<Integer>(8);
        root.right.left = new BinaryTreeNode<Integer>(12);
        root.right.right = new BinaryTreeNode<Integer>(16);
        BinaryTreeNode<Integer> result = convert(root);
        //打印注意陷入死循环
        BinaryTreeNode<Integer> temp = result;
        while (temp != null) {
            temp = temp.right;
            if (temp != null)
                System.out.print(temp.val + "\t");
        }
    }
}
