package com.lynch;

import com.lynch.structure.TraversalOfBinaryTree;
import com.lynch.structure.BinaryTreeNode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lynch on 2019/3/4. <br>
 * 7.重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，重建该二叉树
 * 输入中不含有重复的数字，例如前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
 * <p>
 * Notes:前序+中序，后续+中序可以完成重建，而前序+后序无法完成
 * 首先根据前序遍历序列的第一个数字创建根节点，接下来在中序遍历序列中找到根节点的位置
 * 从而确定左、右子树的节点数量  constructCore()递归构造
 **/
public class ReconstructBinaryTree_7 {

    public static BinaryTreeNode construct(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || preorder.length != inorder.length)
            return null;
        return constructCore(preorder, 0, inorder, 0, preorder.length);
    }

    /**
     * 前序的第一个数字1表明是树的根节点，结合中序可知，472为根节点的左子树，5386为根节点的右子树
     * @param preorder       前序
     * @param preorder_start 前序起点
     * @param inorder        中序
     * @param inorder_start  中序起点
     * @param length
     * @return
     */
    public static BinaryTreeNode constructCore(int[] preorder, int preorder_start, int[] inorder, int inorder_start, int length) {
        if (length == 0)
            return null;
        int inorder_index = -1;
        //找到根节点值
        for (int i = inorder_start; i < inorder_start + length; i++) {
            if (inorder[i] == preorder[preorder_start]) {
                inorder_index = i;
                break;
            }
        }
        int left_length = inorder_index - inorder_start;
        BinaryTreeNode node = new BinaryTreeNode(preorder[preorder_start]);
        node.left = constructCore(preorder, preorder_start + 1, inorder, inorder_start, left_length);
        node.right = constructCore(preorder, preorder_start + left_length + 1, inorder, inorder_index + 1, length - left_length - 1);
        return node;
    }

    public BinaryTreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre==null || in==null)
            return null;
        BinaryTreeNode treeNode = null;
        for(int i = 0; i < in.length; i++){
            if(pre[0] == in[i]){
                treeNode =  new BinaryTreeNode(pre[0]);
                treeNode.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i+1), Arrays.copyOfRange(in, 0, i));
                treeNode.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i+1, pre.length), Arrays.copyOfRange(in, i+1,in.length));
            }
        }
        return treeNode;
    }

    public static void main(String[] args) {
        //                    1
        //                  /    \
        //                  2     3
        //                 /     / \
        //               4      5   6
        //                \        /
        //                 7      8
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        BinaryTreeNode root = construct(pre, in);
        //前序遍历序列
        List<Integer> preOrder = TraversalOfBinaryTree.preorderIteratively(root);
        //中序遍历序列
        List<Integer> inOrder = TraversalOfBinaryTree.inorderIteratively(root);
        //后续遍历序列
        List<Integer> postOrder = TraversalOfBinaryTree.postorderIteratively(root);
        System.out.println("前序遍历序列" + preOrder);
        System.out.println("中序遍历序列" + inOrder);
        System.out.println("后续遍历序列" + postOrder);
    }
}
