package com.lynch;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by lynch on 2019-04-02. <br>
 * 59.2.队列的最大值
 * <p>
 * 定义一个队列并实现函数max得到队列里的最大值。要求max，pushBack，popFront的时间复杂度都是o(1)。
 * <p>
 * 思路1：借助之前做过的9.用两个栈实现队列和30.包含min函数的栈，我们可以实现求队列的最大值。
 * 入队时间复杂度o(1)，出队时间复杂度o(n),获取最大值时间复杂度o(1)，空间复杂度o(n)。
 * 思路2：将上一题的滑动窗口看成一个队列即可。入队时间复杂度o(1)，出队时间复杂度o(1)，
 * 调整记录下标的双端队列的时间复杂度最差为o(n)，获取最值得时间复杂度为o(1)。
 **/
public class QueueWithMax_59_2 {
    private static class QueueWithMax<T extends Comparable> {
        private Deque<InternalData<T>> queueData;
        private Deque<InternalData<T>> queueMax;
        private int currentIndex;
        public QueueWithMax() {
            this.queueData = new ArrayDeque<>();
            this.queueMax = new ArrayDeque<>();
            this.currentIndex = 0;
        }
        public T max(){
            if(queueMax.isEmpty())
                return null;
            return queueMax.getFirst().value;
        }
        public void pushBack(T value){
            while (!queueMax.isEmpty()&&value.compareTo(queueMax.getLast().value)>=0)
                queueMax.removeLast();
            InternalData<T> addData = new InternalData<>(value,currentIndex);
            queueMax.addLast(addData);
            queueData.addLast(addData);
            currentIndex++;
        }
        public T popFront(){
            if(queueData.isEmpty())
                return null;
            InternalData<T> delData = queueData.removeFirst();
            if(delData.index==queueMax.getFirst().index)
                queueMax.removeFirst();
            return delData.value;
        }
        private static class InternalData<M extends Comparable> {
            public M value;
            public int index;
            public InternalData(M value,int index){
                this.value = value;
                this.index = index;
            }
        }
    }
    public static void main(String[] args) {
        QueueWithMax<Integer> queue = new QueueWithMax<>();
        queue.pushBack(3);
        System.out.println(queue.max());
        queue.pushBack(5);
        System.out.println(queue.max());
        queue.pushBack(1);
        System.out.println(queue.max());
        System.out.println("开始出队后，调用max");
        System.out.println(queue.max());
        queue.popFront();
        System.out.println(queue.max());
        queue.popFront();
        System.out.println(queue.max());
        queue.popFront();
        System.out.println(queue.max());


    }
}
