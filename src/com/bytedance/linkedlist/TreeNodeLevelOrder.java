package com.bytedance.linkedlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lynch
 * @date 2019/8/9 15:49
 * 二叉树的锯齿形层次遍历
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层次遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 */
public class TreeNodeLevelOrder {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(zigzagLevelOrder(root));
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        traverse(root, result, 0);
        return result;
    }

    public static void traverse(TreeNode root, List<List<Integer>> result, int level) {
        if (root != null) {
            LinkedList<Integer> list;
            if (result.size() <= level) { //result的大小小于等于层数，则该层还未被访问
                list = new LinkedList<Integer>();
                result.add(list);
            } else {
                list = (LinkedList<Integer>) result.get(level); //获取该层的已加入的树值
            }

            if (level % 2 == 0) {
                list.addLast(root.val);
            } else {
                list.addFirst(root.val);
            }
            System.out.println("level " + level + " " + list);
            //递归
            traverse(root.left, result, level + 1);
            traverse(root.right, result, level + 1);
        }
    }
}
