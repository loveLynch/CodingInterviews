package com.lynch;

import com.lynch.structure.BinaryTreeNodeF;

/**
 * Created by lynch on 2019/3/4. <br>
 * 8.二叉树的下一个节点
 * 给定一颗二叉树和其中的一个节点，找出中序遍历序列的下一个节点
 * 树中的节点除了有两个分别指向左、右子节点的指针，还有一个指向父节点的指针
 **/
public class BinaryTreeNextNode_8 {
/*
* 1。如果输入的当前节点有右孩子，则它的下一个节点即为该右孩子为根节点的子树的最左边的节点
* 2。如果输入的当前节点没有右孩子，就需要判断其与自身父节点的关系：
2-1。如果当前节点没有父节点，那所求的下一个节点不存在，返回null.
2-2。如果输入节点是他父节点的左孩子，那他的父节点就是所求的下一个节点
2-3。如果输入节点是他父节点的右孩子，那就需要将输入节点的父节点作为新的当前节点，返回到2。,判断新的当前节点与他自身父节点的关系
* */

    public static BinaryTreeNodeF getNext(BinaryTreeNodeF node) {
        if (node == null)
            return null;
        else if (node.right != null) {
            node = node.right;
            while (node.left != null)
                node = node.left;
            return node;
        }
        //循环对2。进行执行
        while (node.father != null) {
            if (node.father.left == node)
                return node.father;
            node = node.father;
        }
        return null;
    }

    public static void main(String[] args) {
        //                    a
        //                  /    \
        //                  b     c
        //                 / \    / \
        //               d    e  f   g
        //                   / \
        //                  h   i
        //根节点
        BinaryTreeNodeF root = new BinaryTreeNodeF('a');
        //左子树
        root.left = new BinaryTreeNodeF('b');
        root.left.father = root;
        root.left.left = new BinaryTreeNodeF('d');
        root.left.left.father = root.left;
        root.left.right = new BinaryTreeNodeF('e');
        root.left.right.father = root.left;
        root.left.right.left = new BinaryTreeNodeF('h');
        root.left.right.left.father = root.left.right;
        root.left.right.right = new BinaryTreeNodeF('i');
        root.left.right.right.father = root.left.right;
        //右子树
        root.right = new BinaryTreeNodeF('c');
        root.right.father = root;
        root.right.left = new BinaryTreeNodeF('f');
        root.right.left.father = root.right;
        root.right.right = new BinaryTreeNodeF('g');
        root.right.right.father = root.right;


        //下一个节点的验证
        //1。b.next->h   a.next->f
        System.out.println("b.next->" + getNext(root.left).val);
        System.out.println("a.next->" + getNext(root).val);
        //2-3。->2-1。g.next->null
        System.out.println("g.next->" + getNext(root.right.right));
        //2-2。d.next->b f.next->c
        System.out.println("d.next->" + getNext(root.left.left).val);
        System.out.println("f.next->" + getNext(root.right.left).val);
        //2-3。->2-2。i.next->a
        System.out.println("i.next->"+getNext(root.left.right.right).val);
        //
        System.out.println("c.next->" + getNext(root.right).val);
        System.out.println("e.next->" + getNext(root.left.right).val);



    }
}

