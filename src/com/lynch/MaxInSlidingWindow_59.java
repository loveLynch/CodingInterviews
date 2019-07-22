package com.lynch;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lynch on 2019-04-02. <br>
 * 59.滑动窗口的最大值
 * <p>
 * 给定一个数组和滑动窗口的大小，请找出所有滑动窗口的最大值。
 * 如:输入数组{2,3,4,2,6,2,5,1}和数字3，
 * 那么一共存在6个滑动窗口，{2,3,4},{3,4,2},{4,2,6},{2,6,2},{6,2,5,},{2,5,1}
 * 他们的最大值分别为{4,4,6,6,6,5}。
 **/
public class MaxInSlidingWindow_59 {
    /**
     * 利用队列插入，移动步数取值
     * 将新插入的值与原队列中的最大值比较
     * 第一个窗口和最后一个窗口需单独确定最大值
     *
     * @return
     */
    private static List<Integer> maxInSlidingWindow(int[] data, final int size) {
        if (data == null || size<= 0 || data.length < size)
            return null;
        Queue<Integer> storeQueue = new LinkedBlockingQueue<>();
        //找出第一个窗口中的最大值，并插入队列
        List<Integer> maxList = new ArrayList<>();//存储最大值
        int moveIndex = size;
        int max = data[0];//临时存储最大值
        for (int i = 0; i < moveIndex; i++) {
            if (max < data[i])
                max = data[i];
            storeQueue.offer(data[i]);
        }
        maxList.add(max);//存储第一个窗口的最大值
        //现在开始依次滑动窗口到倒数第二个窗口
        while (storeQueue.size() < data.length - 1) {
            storeQueue.offer(data[moveIndex]);
            if (data[moveIndex] > max)
                max = data[moveIndex];
            maxList.add(max);
            moveIndex++;
        }
        //最后一个窗口最大值
        max = data[data.length - size];
        for (int j = data.length - size; j < data.length; j++) {
            if (max < data[j])
                max = data[j];
        }
        maxList.add(max);
        return maxList;
    }

    /**
     * 把可能成为最大值数字的下标放入双端队列deque，从而减少遍历次数。
     * 首先，所有在没有查看后面数字的情况下，任何一个节点都有可能成为某个状态的滑动窗口的最大值，
     * 因此，数组中任何一个元素的下标都会入队。关键在于出队，以下两种情况下，
     * 该下标对应的数字不会是窗口的最大值需要出队：(1)该下标已经在窗口之外，比如窗口长度为3，下标5入队，那么最大值只可能在下标3,4,5中出现，队列中如果有下标2则需要出队；
     * (2)后一个元素大于前面的元素，那么前面的元素出对，
     * 比如目前队列中有下标3、4，data[3] = 50,data[4]=40，下标5入队，但data[5] = 70，
     * 则队列中的3，4都需要出队。
     *
     * @param data
     * @param size
     * @return
     */
    public static int[] maxInWindows(int[] data, final int size) {
        if (data == null || size<= 0 || data.length < size)
            return new int[0];
        int[] result = new int[data.length - size + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < size - 1; i++) {
            while (!deque.isEmpty() && data[i] >= data[deque.getLast()])
                deque.removeLast();
            deque.addLast(i);
        }
        for (int i = size - 1; i < data.length; i++) {
            while (!deque.isEmpty() && i - deque.getFirst() + 1 > size)
                deque.removeFirst();
            while (!deque.isEmpty() && data[deque.getLast()] <= data[i])
                deque.removeLast();
            deque.addLast(i);
            result[i - (size - 1)] = data[deque.getFirst()];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] data = {2, 3, 4, 2, 6, 2, 5, 1};
//        int [] data ={16,14,12,10,8,6,4};
        System.out.println("3窗口");
        List<Integer> maxList = maxInSlidingWindow(data, 3);
        for (Integer max : maxList)
            System.out.print(max + "\t");
        System.out.println();
        int[] maxData = maxInWindows(data, 3);
        for (int max : maxData)
            System.out.print(max + "\t");
        System.out.println();
        System.out.println("5窗口");
        List<Integer> maxList4 = maxInSlidingWindow(data, 5);
        for (Integer max : maxList4)
            System.out.print(max + "\t");
        System.out.println();
        int[] maxData4 = maxInWindows(data, 5);
        for (int max : maxData4)
            System.out.print(max + "\t");
    }
}
