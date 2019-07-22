package com.lynch;

import com.lynch.structure.ListNode;

/**
 * Created by lynch on 2019-04-03. <br>
 * 圆圈中最后剩下的数字
 * <p>
 * 0，1，2...n-1这n个数字拍成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字，
 * 求剩下的最后一个数字。
 * 如0，1，2，3，4这5个数字组成的圈，
 * 每次删除第3个数字，一次删除2，0，4，1，因此最后剩下的是3。
 **/
public class LastNumberInCircle_62 {
    private static int lastRemaining(int n, int m) {
        if (n < 1 || m < 1)
            return -1;
        ListNode<Integer> head = new ListNode<>(0);
        ListNode<Integer> cur = head;
        for (int i = 1; i < n; i++) {
            ListNode<Integer> node = new ListNode<>(i);
            cur.next = node;
            cur = cur.next;
        }
        cur.next = head;
        cur = head;
        while (true) {
            //长度为1结束循环
            if (cur.next == cur)
                return cur.val;
            //向后移动
            for (int i = 1; i < m; i++)
                cur = cur.next;
            //删除当前节点
            cur.val = cur.next.val;
            cur.next = cur.next.next;
            //删除后，cur停在被删节点的后一节点处
        }
    }

    /**
     * 在这 n个数字中， 第一个被删除的数字是(m-1)%n。为了简单起见，我们把(m- 1)%n 记为 k，
     * 那么删除k之后剩下的 n-1 个数字为 0，1，… ，k-1，k+1，… ，n-1，并且下一次删除从数字 k+1 开始计数。
     * 相当于在剩下的序列中， k+1 排在最前面，从而形成 k+1，... ，n- 1，0，I，… ，k-1 。
     * 该序列最后剩下的数字也应该是关于 n 和 m 的函数。
     * 由于这个序列的规律和前面最初的序列不一样（最初的序列是从 0 开始的连续序列），
     * 因此该函数不同于前面的函数，记为 f’(n-1,m)。最初序列最后剩下的数字 f(n, m）
     * 一定是删除一个数字之后的序列最后剩下的数字，即 f(n, m)=f’(n-1, m）。
     *          0,n=1
     * f(n,m)={ [f(n-1,m)+m]%n ,n>1
     *
     * 约瑟夫环
     *
     * @param n
     * @param m
     * @return
     */
    public static int lastRemaining2(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
            System.out.println(last);
        }
        return last;
    }

    public static void main(String[] args) {
//        System.out.println(lastRemaining(5, 3)); //3
//        System.out.println(lastRemaining(6, 7)); //4
//        System.out.println(lastRemaining(0, 7)); //-1

        System.out.println(lastRemaining2(5, 3)); //3
//        System.out.println(lastRemaining2(6, 7)); //4
//        System.out.println(lastRemaining2(0, 7)); //-1
    }
}
