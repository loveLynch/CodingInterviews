package com.lynch;

import com.lynch.structure.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by lynch on 2019-03-20. <br>
 * 34.二叉树中和为某一值的路径
 * 输入一颗二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 * 从根节点开始一直往下直到叶节点所经过的节点形成一条路径
 * 思路：根节点出发，前序遍历
 * 压栈和出栈
 **/
public class FindPathInBinaryTree_34 {
    /**
     * 前序遍历的方法
     *
     * @param pRoot
     * @param expectedSum 期望和
     */
    private static void findPath(BinaryTreeNode<Integer> pRoot, int expectedSum) {
        if (pRoot == null)
            return;
//        List<Integer> path = new ArrayList<>();
        Stack<Integer> path = new Stack<>();
        findPath(pRoot, path, expectedSum, 0);

    }

    /**
     * @param curNode
     * @param path
     * @param expectedSum
     * @param currentSum
     */
//    private static void findPath(BinaryTreeNode<Integer> curNode, List<Integer> path, int expectedSum, int currentSum) {
    private static void findPath(BinaryTreeNode<Integer> curNode, Stack<Integer> path, int expectedSum, int currentSum) {
        //push.add(curNode.val)
        path.push(curNode.val);
        currentSum += curNode.val;
        if (curNode.left != null)
            findPath(curNode.left, path, expectedSum, currentSum);
        if (curNode.right != null)
            findPath(curNode.right, path, expectedSum, currentSum);
        if (curNode.left == null && curNode.right == null && currentSum == expectedSum)
            System.out.println(path);
        //返回父节点之前删除当前节点
        //path.remove(path.size()-1);
        path.pop();
    }

    public static ArrayList<ArrayList<Integer>> allPath = new ArrayList<>();
    public static ArrayList<Integer> onePath = new ArrayList<>();

    public static ArrayList<ArrayList<Integer>> FindPath(BinaryTreeNode<Integer> root, int target) {
        if (root == null)
            return allPath;
        onePath.add(root.val);
        if (root.left != null) {
            FindPath(root.left, target);
        }
        if (root.right != null) {
            FindPath(root.right, target);
        }
        if (root.left == null && root.right == null && pathSum(onePath) == target) {
            allPath.add((ArrayList<Integer>) onePath.clone());
        }
        onePath.remove(onePath.size() - 1);

        return allPath;


    }

    public static void main(String[] args) {
        //            10
        //          /   \
        //         5     12
        //       /  \
        //      4    7
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(10);
        root.left = new BinaryTreeNode<Integer>(5);
        root.right = new BinaryTreeNode<Integer>(12);
        root.left.left = new BinaryTreeNode<Integer>(4);
        root.left.right = new BinaryTreeNode<Integer>(7);
//        findPath(root, 22);
        ArrayList<ArrayList<Integer>> result = FindPath(root, 22);
        System.out.println(result.size());
        for (ArrayList<Integer> path:result){
            System.out.println(path);
        }
    }

    public static int pathSum(ArrayList<Integer> onePath) {
        int sum = 0;
        for (Integer val : onePath)
            sum += val;
        return sum;
    }
    public static void printPath(ArrayList<Integer> onePath) {
        for (Integer val : onePath)
            System.out.print(val+" ");
        System.out.println();
    }
}
