package com.leetcode;

import com.bytedance.linkedlist.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-09-15. <br>
 * 95. 不同的二叉搜索树 II
 * <p>
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class AllBST {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println(generateTrees1(n));
        System.out.println(generateTrees2(n));
        System.out.println(generateTrees3(n));
    }

    /**
     * 递归
     * 左子树的所有值小于根节点，右子树的所有值大于根节点。
     * <p>
     * 所以如果求 1...n 的所有可能。
     * <p>
     * 我们只需要把 1 作为根节点，[ ] 空作为左子树，[ 2 ... n ] 的所有可能作为右子树。
     * <p>
     * 2 作为根节点，[ 1 ] 作为左子树，[ 3...n ] 的所有可能作为右子树。
     * <p>
     * 3 作为根节点，[ 1 2 ] 的所有可能作为左子树，[ 4 ... n ] 的所有可能作为右子树，然后左子树和右子树两两组合。
     * <p>
     * 4 作为根节点，[ 1 2 3 ] 的所有可能作为左子树，[ 5 ... n ] 的所有可能作为右子树，然后左子树和右子树两两组合。
     * <p>
     * ...
     * <p>
     * n 作为根节点，[ 1... n ] 的所有可能作为左子树，[ ] 作为右子树。
     * <p>
     * 至于，[ 2 ... n ] 的所有可能以及 [ 4 ... n ] 以及其他情况的所有可能，可以利用上边的方法，把每个数字作为根节点，然后把所有可能的左子树和右子树组合起来即可。
     * <p>
     * 如果只有一个数字，那么所有可能就是一种情况，把该数字作为一棵树。而如果是 [ ]，那就返回 null。
     * <p>
     * 作者：windliang
     * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-2-7/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public static List<TreeNode> generateTrees1(int n) {

        List<TreeNode> ans = new ArrayList<TreeNode>();
        if (n == 0) {
            return ans;
        }
        return getAns(1, n);

    }

    private static List<TreeNode> getAns(int start, int end) {
        List<TreeNode> ans = new ArrayList<TreeNode>();
        //此时没有数字，将 null 加入结果中
        if (start > end) {
            ans.add(null);
            return ans;
        }
        //只有一个数字，当前数字作为一棵树加入结果中
        if (start == end) {
            TreeNode tree = new TreeNode(start);
            ans.add(tree);
            return ans;
        }
        //尝试每个数字作为根节点
        for (int i = start; i <= end; i++) {
            //得到所有可能的左子树
            List<TreeNode> leftTrees = getAns(start, i - 1);
            //得到所有可能的右子树
            List<TreeNode> rightTrees = getAns(i + 1, end);
            //左子树右子树两两组合
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    //加入到最终结果中
                    ans.add(root);
                }
            }
        }
        return ans;
    }

    public static List<TreeNode> generateTrees2(int n) {
        ArrayList<TreeNode>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<TreeNode>();
        if (n == 0) {
            return dp[0];
        }
        dp[0].add(null);
        //长度为 1 到 n
        for (int len = 1; len <= n; len++) {
            dp[len] = new ArrayList<TreeNode>();
            //将不同的数字作为根节点，只需要考虑到 len
            for (int root = 1; root <= len; root++) {
                int left = root - 1;  //左子树的长度
                int right = len - root; //右子树的长度
                for (TreeNode leftTree : dp[left]) {
                    for (TreeNode rightTree : dp[right]) {
                        TreeNode treeRoot = new TreeNode(root);
                        treeRoot.left = leftTree;
                        //克隆右子树并且加上偏差
                        treeRoot.right = clone(rightTree, root);
                        dp[len].add(treeRoot);
                    }
                }
            }
        }
        return dp[n];
    }

    /**
     * 实现树的复制并且加上偏差
     *
     * @param n
     * @param offset
     * @return
     */
    private static TreeNode clone(TreeNode n, int offset) {
        if (n == null) {
            return null;
        }
        TreeNode node = new TreeNode(n.val + offset);
        node.left = clone(n.left, offset);
        node.right = clone(n.right, offset);
        return node;
    }

    /**
     * 动态规划
     *
     * @param n
     * @return
     */
    public static List<TreeNode> generateTrees3(int n) {
        List<TreeNode> pre = new ArrayList<TreeNode>();
        if (n == 0) {
            return pre;
        }
        pre.add(null);
        //每次增加一个数字
        for (int i = 1; i <= n; i++) {
            List<TreeNode> cur = new ArrayList<TreeNode>();
            //遍历之前的所有解
            for (TreeNode root : pre) {
                //插入到根节点
                TreeNode insert = new TreeNode(i);
                insert.left = root;
                cur.add(insert);
                //插入到右孩子，右孩子的右孩子...最多找 n 次孩子
                for (int j = 0; j <= n; j++) {
                    TreeNode root_copy = treeCopy(root); //复制当前的树
                    TreeNode right = root_copy; //找到要插入右孩子的位置
                    int k = 0;
                    //遍历 j 次找右孩子
                    for (; k < j; k++) {
                        if (right == null)
                            break;
                        right = right.right;
                    }
                    //到达 null 提前结束
                    if (right == null)
                        break;
                    //保存当前右孩子的位置的子树作为插入节点的左孩子
                    TreeNode rightTree = right.right;
                    insert = new TreeNode(i);
                    right.right = insert; //右孩子是插入的节点
                    insert.left = rightTree; //插入节点的左孩子更新为插入位置之前的子树
                    //加入结果中
                    cur.add(root_copy);
                }
            }
            pre = cur;

        }
        return pre;
    }


    /**
     * 复制当前的树
     *
     * @param root
     * @return
     */
    private static TreeNode treeCopy(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = treeCopy(root.left);
        newRoot.right = treeCopy(root.right);
        return newRoot;
    }
}
