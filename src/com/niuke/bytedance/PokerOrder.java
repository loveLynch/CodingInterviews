package com.niuke.bytedance;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lynch on 2019-06-29. <br>
 **/
public class PokerOrder {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String N = input.nextLine();
        List<String> pokerList = new ArrayList<>();
        for (int i = 0; i < Integer.valueOf(N); i++) {
            String nextLine = input.nextLine();
            pokerList.add(nextLine);
        }

        for (String singlePoker : pokerList) {
            String[] single = singlePoker.split(" ");
            sort3(single);
            sort2(single);
            sort(single);
        }

    }

    public static void sort2(String[] pokers) {
        // 正向操作过程, 将手中的牌放到桌子上
        // 第一步先创建一个链表
        LinkedList<Integer> pokerList = new LinkedList<>();
        for (String poker : pokers) {
            pokerList.add(Integer.valueOf(poker));
        }
        //声明一个新的容器，在这里可以理解成桌子
        LinkedList<Integer> newPokers2 = new LinkedList<>();
        for (int i = 0; i < pokers.length; i++) {
            //将手牌中的第一张放在桌子上
            newPokers2.add(pokerList.pollFirst());
            //假如这是最后一次循环手牌已经没有了就不需要进入这个判断了
            if (pokerList.size() > 0) {
                //将第一张放在牌堆的最后
                pokerList.addLast(pokerList.pollFirst());
            }
        }
        //循环打印到控制台，
        //循环打印到控制台，
        for (Integer num : newPokers2)
            System.out.print(num + " ");
        System.out.println();
    }

    /**
     * 这里的操作是从桌子把牌拿回到手上
     * 从桌子 到 手上 int[] t = {13,12,11,10,9,8,7,6,5,4,3,2,1};
     * 返回 {1,12,2,8,3,11,4,9,5,13,6,10,7}
     *
     * @param pokers
     */
    public static void sort(String[] pokers) {
        // 反向操作, 将桌子上的牌变到手上
        // 创建一个链表
        LinkedList<Integer> pokerList = new LinkedList<>();
        for (int i=pokers.length-1;i>=0;i--){
            pokerList.add(Integer.valueOf(pokers[i]));
        }
//        for (String poker : pokers) {
//            pokerList.add(Integer.valueOf(poker));
//        }
        //声明一个目标容器，理解成手
        LinkedList<Integer> newPokers2 = new LinkedList<>();
        for (Integer aPokerList : pokerList) {
            //判断手上的牌是否大于1张
            if (newPokers2.size() > 1) {
                //如果大于一张，则把手牌的最后一张放在最上面
                newPokers2.addFirst(newPokers2.pollLast());
            }
            //从桌子上拿一张牌放在手上
            newPokers2.addFirst(aPokerList);
        }
        //循环打印到控制台，
        for (Integer num : newPokers2)
            System.out.print(num + " ");
        System.out.println();
    }

    public static void sort3(String[] pokers) {
        LinkedBlockingQueue<Integer> pokerQueue = new LinkedBlockingQueue<>();
        Stack<Integer> originalStack = new Stack<>();
        for (String singlePoker : pokers) {
            pokerQueue.offer(Integer.valueOf(singlePoker));
        }
//        while (!pokerQueue.isEmpty()) {
//            System.out.print(pokerQueue.poll() + " ");
//        }
//        System.out.println();
        while (!pokerQueue.isEmpty()) {
            originalStack.push(pokerQueue.poll());
            Integer nextNum = pokerQueue.poll();
            if (nextNum != null) {
                pokerQueue.offer(nextNum);
            }
        }
        while (!originalStack.isEmpty()) {
            System.out.print(originalStack.pop() + " ");
        }
        System.out.println();
    }
}
