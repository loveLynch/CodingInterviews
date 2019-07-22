package com.lynch;

import com.lynch.structure.BinaryTreeNode;

import java.util.Stack;

/**
 * Created by lynch on 2019-04-02. <br>
 * 54.二叉搜索树的第k大节点
 * <p>
 * 找出二叉搜索树的第k大节点。例如，在下图的树里，第3大节点的值为4，输入该树的根节点，3，则输出4。
 *         5
 *        / \
 *       3   7
 *     / \   / \
 *    2  4  6   8
 **/
public class FindKthNodeInBST_54 {
    /**
     *  二叉搜索树的中序遍历是有序的。
     *  B可以引入一个计数器，每访问一个节点，计数器+1，
     *  当计数器等于k时，被访问节点就是该二叉搜索树的第k大节点
     * @param root
     * @param k
     * @return
     */
    private static BinaryTreeNode<Integer> kthNode(BinaryTreeNode<Integer> root, int k){
        //栈顶元素保证一直是cur的父节点
        if(root==null || k<0)
            return null;
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        BinaryTreeNode<Integer> cur = root;
        int count = 0;
        while (!stack.isEmpty()||cur!=null){
            if(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            else{
                cur = stack.pop();
                count++;
                if(count==k)
                    return cur;
                cur = cur.right;
            }
        }
        return null;
    }
    public static void main(String[] args){
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5);
        root.left = new BinaryTreeNode<>(3);
        root.left.left = new BinaryTreeNode<>(2);
        root.left.right = new BinaryTreeNode<>(4);
        root.right = new BinaryTreeNode<>(7);
        root.right.left = new BinaryTreeNode<>(6);
        root.right.right = new BinaryTreeNode<>(8);
        System.out.println(kthNode(root,2).val);//3
        System.out.println(kthNode(root,6).val);//7
        System.out.println(kthNode(root,8));//null
    }
}
