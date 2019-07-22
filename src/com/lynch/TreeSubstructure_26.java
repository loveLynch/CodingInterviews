package com.lynch;

import com.lynch.structure.BinaryTreeNode;

/**
 * Created by lynch on 2019/3/16. <br>
 * 26.树的子结构
 * 输入两棵二叉树A和B，判断B是不是A的子结构
 * 第一步：在树A中找到和树B的根节点的值一样的节点R
 * 第二步：判断树A中以R为根节点的子树是不是包含和树B一样的结构
 **/
public class TreeSubstructure_26 {
    /**
     * 判断是否有子结构
     * 递归
     *
     * @param pRoot1 树A
     * @param pRoot2 树B(子）
     * @return
     */
    private static boolean hasSubTree(BinaryTreeNode<Integer> pRoot1, BinaryTreeNode<Integer> pRoot2) {
        boolean result = false;

        if (pRoot1 != null && pRoot2 != null) {
            if (pRoot1.val == pRoot2.val) {
                result = doesTree1HaveTree2(pRoot1, pRoot2);
            }
            if (!result) {
                result = hasSubTree(pRoot1.left, pRoot2);

            }
            if (!result) {
                result = hasSubTree(pRoot1.right, pRoot2);

            }
        }
        return result;

    }

    /**
     * 判断各自的左右节点是否相等
     * 递归
     *
     * @param pRoot1 树A
     * @param pRoot2 树B(子）
     * @return
     */
    private static boolean doesTree1HaveTree2(BinaryTreeNode<Integer> pRoot1, BinaryTreeNode<Integer> pRoot2) {
        if (pRoot2 == null)
            return true;
        if (pRoot1 == null)
            return false;
        if (pRoot1.val != pRoot2.val)
            return false;
        return doesTree1HaveTree2(pRoot1.left, pRoot2.left) && doesTree1HaveTree2(pRoot1.right, pRoot2.right);
    }

    public static void main(String[] args) {
        //                  root1             root2         root3      root4     root5
        //                    1                  3            4          9        null
        //                  /    \              / \          / \
        //                  2     3            5   6        6   7
        //                 /     / \
        //               4      5   6
        //                \        /
        //                 7      8
        BinaryTreeNode<Integer> root1 = new BinaryTreeNode<>(1);
        root1.left = new BinaryTreeNode<>(2);
        root1.left.left = new BinaryTreeNode<>(4);
        root1.left.left.right = new BinaryTreeNode<>(7);
        root1.right = new BinaryTreeNode<>(3);
        root1.right.left = new BinaryTreeNode<>(5);
        root1.right.right = new BinaryTreeNode<>(6);
        root1.right.right.left = new BinaryTreeNode<>(8);

        BinaryTreeNode<Integer> root2 = new BinaryTreeNode<>(3);
        root2.left = new BinaryTreeNode<>(5);
        root2.right = new BinaryTreeNode<>(6);

        BinaryTreeNode<Integer> root3 = new BinaryTreeNode<>(4);
        root3.left = new BinaryTreeNode<>(6);
        root3.right = new BinaryTreeNode<>(7);

        BinaryTreeNode<Integer> root4 = new BinaryTreeNode<>(9);

        BinaryTreeNode<Integer> root5 = null;

        System.out.println(hasSubTree(root1, root2));//true
        System.out.println(hasSubTree(root1, root3));//false
        System.out.println(hasSubTree(root1, root4));//false
        System.out.println(hasSubTree(root1, root5));//false


    }

}
