package com.lynch;

import com.lynch.structure.BinaryTreeNode;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * Created by lynch on 2019-03-20. <br>
 * 37.序列化二叉树
 * <p>
 * 实现两个函数，分别用来序列化和反序列化二叉树。
 **/
public class SerializeBinaryTree_37 {
    /**
     * 在遍历结果中，记录null指针后（比如用一个特殊字符$），那么任何一种遍历方式都能回推出原二叉树。
     * 如果期望边读取序列化数据，边反序列化二叉树，那么仅可以使用前序或层序遍历。
     * 但层序记录的null个数要远多于前序，因此选择使用记录null指针的前序遍历进行序列化
     *
     * @param root
     * @return
     */
    private static String serialize(BinaryTreeNode<Integer> root) {
        if (root == null)
            return "$,";
        StringBuilder result = new StringBuilder();
        result.append(root.val);
        result.append(",");
        result.append(serialize(root.left));
        result.append(serialize(root.right));
        return result.toString();
    }

    private static BinaryTreeNode<Integer> deserialize(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        return deserializeCore(stringBuilder);
    }

    /**
     * 根据二叉树前序遍历特点，当字符串连续读出两个"$"，则证明某根节点左、右子节点均为空，它是一个叶节点
     * 依次以二叉树前序遍历特点进行反序列化。遇到$即为null节点
     *
     * @param stringBuilder
     * @return
     */
    private static BinaryTreeNode<Integer> deserializeCore(StringBuilder stringBuilder) {
        if (stringBuilder.length() == 0)
            return null;
        String num = stringBuilder.substring(0, stringBuilder.indexOf(","));
        stringBuilder.delete(0, stringBuilder.indexOf(","));//删除数字或字符
        stringBuilder.deleteCharAt(0);//删除逗号,
        if (num.equals("$"))
            return null;
        BinaryTreeNode<Integer> node = new BinaryTreeNode<>(Integer.parseInt(num));
        node.left = deserializeCore(stringBuilder);
        node.right = deserializeCore(stringBuilder);
        return node;
    }

    public static void main(String[] args) {
        //            1
        //          /   \
        //         2     3
        //       /      / \
        //      4      5   6
        //    1,2,4,$,$,$,3,5,$,$,6,$,$
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1);
        root.left = new BinaryTreeNode<Integer>(2);
        root.right = new BinaryTreeNode<Integer>(3);
        root.left.left = new BinaryTreeNode<Integer>(4);
        root.right.left = new BinaryTreeNode<Integer>(5);
        root.right.right = new BinaryTreeNode<Integer>(6);
        System.out.println("前序遍历原始树：" + root);
        String result = serialize(root);
        System.out.println("序列化结果：" + result);
        BinaryTreeNode<Integer> deserializeRoot = deserialize(result);
        System.out.println("反序列后的树：" + deserializeRoot);

    }
}
