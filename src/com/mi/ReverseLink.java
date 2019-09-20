package com.mi;

/**
 * Created by lynch on 2019-09-20. <br>
 **/
public class ReverseLink {
    public static void main(String[] args) {
        Link root = new Link(1);
        root.next = new Link(2);
        root.next.next = new Link(3);
        System.out.println("原始");
        print(root);
        Link re = reverseLink(root);
        System.out.println("反转");
        print(re);

    }

    public static Link reverseLink(Link head) {
        Link cur = head;
        Link pre = null;
        Link post = cur.next;
        while (true) {
            cur.next = pre;
            pre = cur;
            cur = post;

            if (post != null) {
                post = post.next;

            } else {
                return pre;
            }
        }

    }

    public static void print(Link head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    static class Link {
        public int val;
        public Link next;

        public Link(int val) {
            this.val = val;
            this.next = null;
        }
    }
}