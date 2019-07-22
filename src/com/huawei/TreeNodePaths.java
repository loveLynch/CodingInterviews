package com.huawei;

import com.lynch.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynch on 2019-06-10. <br>
 **/
public class TreeNodePaths {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        List<String> result = binaryTreePaths(root);
        for (String str : result)
            System.out.println(str + " ");
    }

    public static List<String> binaryTreePaths(TreeNode root) {

        ArrayList<String> res = new ArrayList<String>();

        if (root == null)
            return res;

        if (root.left == null && root.right == null) {
            res.add(Integer.toString(root.val));
            return res;
        }
        List<String> leftPaths = binaryTreePaths(root.left);
        for (String s : leftPaths) {
            StringBuilder sb = new StringBuilder(Integer.toString(root.val));

            sb.append("->");
            sb.append(s);
            res.add(sb.toString());
        }

        List<String> rightPaths = binaryTreePaths(root.right);
        for (String s : rightPaths) {
            StringBuilder sb = new StringBuilder(Integer.toString(root.val));
            sb.append("->");
            sb.append(s);
            res.add(sb.toString());
        }
        return res;
    }
}
