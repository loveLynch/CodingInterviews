package com.lynch;

/**
 * Created by lynch on 2019-05-09. <br>
 **/

import com.lynch.structure.TreeNode;

/**
 *
 */
public class Solution {

    public static boolean HasSubtree(TreeNode pRoot1, TreeNode pRoot2) {
        boolean result = false;

        if (pRoot1 != null && pRoot2 != null) {
            if (pRoot1.val == pRoot2.val) {
                result = DoesTree1HasTree2(pRoot1, pRoot2);
            }
            if (!result) {
                result = HasSubtree(pRoot1.left, pRoot2);

            }
            if (!result) {
                result = HasSubtree(pRoot1.right, pRoot2);

            }
        }
        return result;


    }

    public static boolean DoesTree1HasTree2(TreeNode root1, TreeNode root2) {
        if (root2 == null)
            return true;
        if (root1 == null)
            return false;
        if (root1.val != root2.val)
            return false;
        return DoesTree1HasTree2(root1.left, root2.left) && DoesTree1HasTree2(root1.right, root2.right);
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.left.left = new TreeNode(4);
        root1.left.left.right = new TreeNode(7);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(5);
        root1.right.right = new TreeNode(6);
        root1.right.right.left = new TreeNode(8);

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(5);
        root2.right = new TreeNode(6);
        System.out.println(HasSubtree(root1, root2));
    }
}