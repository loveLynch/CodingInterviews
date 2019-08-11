package com.bytedance.linkedlist;

/**
 * @author Lynch
 * @date 2019/8/9 12:14
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * 示例 1:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * <p>
 * 说明:
 * <p>
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 */
public class TreeNodeLowestCommonAncestor {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        System.out.println(lowestCommonAncestor1(root, root.left, root.right).val);
        System.out.println(lowestCommonAncestor1(root, root.left, root.left.right.right).val);
        System.out.println(lowestCommonAncestor2(root, root.left, root.right).val);
        System.out.println(lowestCommonAncestor2(root, root.left, root.left.right.right).val);


    }

    /**
     * 自定向下的方法 时间复杂度O(n^2)
     * 我们可以从根结点出发，判断当前结点的左右子树是否包含这两个结点。
     * 如果左子树包含两个结点，则它们的最低公共祖先结点也一定在左子树中。
     * 如果右子树包含两个结点，则它们的最低公共祖先结点也一定在右子树中。
     * 如果一个结点在左子树，而另一个结点在右子树中，则当前结点就是它们的最低公共祖先结点
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (hasNode(root.left, p) && hasNode(root.left, q))
            return lowestCommonAncestor1(root.left, p, q);
        if (hasNode(root.right, p) && hasNode(root.right, q))
            return lowestCommonAncestor1(root.right, p, q);
        return root;
    }

    /**
     * 判断root为根的树是否包含节点x
     *
     * @param root
     * @param x
     * @return
     */
    public static boolean hasNode(TreeNode root, TreeNode x) {
        if (root == null)
            return false;
        if (root == x)
            return true;
        return hasNode(root.left, x) || hasNode(root.right, x);
    }

    /**
     * 自底向上 时间复杂度O(n)
     * 自底向上遍历结点，一旦遇到结点等于p或者q，则将其向上传递给它的父结点。
     * 父结点会判断它的左右子树是否都包含其中一个结点，
     * 如果是，则父结点一定是这两个节点p和q的LCA，传递父结点到root。
     * 如果不是，我们向上传递其中的包含结点p或者q的子结点，或者NULL(如果子结点不包含任何一个)。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left == null)
            return right;
        if (right == null)
            return left;
        return root;
    }
}
