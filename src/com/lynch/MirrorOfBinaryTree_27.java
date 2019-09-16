package com.lynch;

import com.lynch.structure.BinaryTreeNode;

/**
 * Created by lynch on 2019/3/16. <br>
 * 27.二叉树的镜像
 * 输入一个二叉树，输出它的镜像
 **/
public class MirrorOfBinaryTree_27 {
    /**
     * 先前序遍历这棵树的每个节点，做过遍历的节点有子节点，就交换两个子节点
     * 当交换完所有非叶节点的左右子节点之后就得到镜像
     *
     * @param pNode
     */
    private static void mirrorRecursively(BinaryTreeNode<Integer> pNode) {
        if (pNode == null)
            return;
        if (pNode.left == null && pNode.right == null)
            return;
        BinaryTreeNode<Integer> pTemp = pNode.left;
        pNode.left = pNode.right;
        pNode.right = pTemp;

        if (pNode.left != null)
            mirrorRecursively(pNode.left);

        if (pNode.right != null)
            mirrorRecursively(pNode.right);

    }

    private static void mirrorRecursivelySim(BinaryTreeNode<Integer> pNode) {
        if (pNode == null)
            return;
        BinaryTreeNode<Integer> pTemp = pNode.left;
        pNode.left = pNode.right;
        pNode.right = pTemp;
        //不用再去判断左右节点，因为下面这个递归，左节点又是根节点，就判断了左是否为空
        mirrorRecursivelySim(pNode.left);

        mirrorRecursivelySim(pNode.right);

    }

    public static void main(String[] args) {
        //                  root
        //                    1
        //                  /    \
        //                  2     3
        //                 /     / \
        //               4      5   6
        //                \        /
        //                 7      8
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1);
        root.left = new BinaryTreeNode<>(2);
        root.left.left = new BinaryTreeNode<>(4);
        root.left.left.right = new BinaryTreeNode<>(7);
        root.right = new BinaryTreeNode<>(3);
        root.right.left = new BinaryTreeNode<>(5);
        root.right.right = new BinaryTreeNode<>(6);
        root.right.right.left = new BinaryTreeNode<>(8);

        System.out.println(root);

        mirrorRecursively(root);
        System.out.println(root);

        mirrorRecursivelySim(root);
        System.out.println(root);

    }
}
