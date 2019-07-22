package com.lynch;


import java.util.Arrays;

/**
 * Created by lynch on 2019-03-20. <br>
 * 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
 * 假设输入的数字都互不相同，如数组{5,7,6,9,11,10,8} ,true
 * 如数组{7,4,6,5} ,false
 * 二叉搜索树(BST)
 * 1.若任意节点的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
 * 2.若任意节点的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
 * 3.任意节点的左、右子树也分别为二叉查找树；
 * 4.没有键值相等的节点。
 **/
//       8
//      / \
//    6     10
//   / \    / \
//   5  7  9   11
public class BinarySearchTreePostOrder_33 {
    /**
     * 由于后序遍历
     * 最后一个节点为根节点，结合BST特点，找出二叉树的左右子树并判断合理性
     * 递归
     *
     * @param sequence
     * @return
     */
    private static boolean verifySquenceOfBST(int sequence[], int length) {
        if (sequence == null || length <= 0)
            return false;
        int root = sequence[length - 1];
        //BST中左子树节点小于根节点的值
        int rightStart = 0;
        for (int i = 0; i < length - 1; i++) {
            if (sequence[i] > root) {
                break;
            }
            rightStart++;
        }
        //BST中右子树节点大于根节点的值
        for (int j = rightStart; j < length - 1; j++) {
            if (sequence[j] < root)
                return false;
        }
        //判断左子树是不是BST
        boolean left = true;
        if (rightStart > 0)
            left = verifySquenceOfBST(sequence, rightStart);
        //判断右子树是不是BST
        boolean right = true;
        if (rightStart < length - 1) {
            //右子树
            int rightSequence[] = Arrays.copyOfRange(sequence, rightStart, sequence.length);
            right = verifySquenceOfBST(rightSequence, length - rightStart - 1);
        }


        return (left && right);
    }

    public static void main(String[] args) {

        int sequence1[] = {5, 7, 6, 9, 11, 10, 8};
        int sequence2[] = {7, 4, 6, 5};
        System.out.println(verifySquenceOfBST(sequence1, sequence1.length));//true
        System.out.println(verifySquenceOfBST(sequence2, sequence2.length));//false
    }

}
