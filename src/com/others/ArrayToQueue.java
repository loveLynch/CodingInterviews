package com.others;

/**
 * Created by lynch on 2019-09-12. <br>
 * 用数组实现队列
 **/
public class ArrayToQueue {

    private int[] queue;// 内置数组
    private int front;// 头指针
    private int rear;// 尾指针
    private int size;

    public ArrayToQueue() {
        this.queue = new int[10];
        front = 0;
        rear = -1;
    }

    public ArrayToQueue(int size) {
        this.queue = new int[size];
        front = 0;
        rear = -1;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == queue.length;
    }

    /**
     * 判断队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return queue.length - 1 == rear;
    }

    /**
     * 向队列的队尾插入一个元素
     */
    public void addData(int item) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        size++;
        queue[++rear] = item;
    }

    /**
     * 获得对头元素
     *
     * @return
     */
    public int peekFront() {
        return queue[front];
    }

    /**
     * 获得队尾元素
     *
     * @return
     */
    public int peekRear() {
        return queue[rear];
    }

    public int size() {
        return size;
    }

    /**
     * 从队列的对头移除一个元素
     *
     * @return
     */
    public int poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        size--;
        return queue[front++];
    }

    /**
     * 打印队列
     */
    public void printfQueue() {
        int index = front;
        while (index <= rear) {
            System.out.print(queue[index] + " ");
            index++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayToQueue queue = new ArrayToQueue();
        queue.addData(1);
        queue.addData(2);
        queue.addData(3);
        queue.addData(4);
        queue.addData(5);
        System.out.println("大小 " + queue.size());
        queue.printfQueue();
        System.out.println("取出元素 " + queue.poll());
        queue.printfQueue();
        System.out.println("大小 " + queue.size());
        System.out.println("队首元素 " + queue.peekFront());
        System.out.println("队尾元素 " + queue.peekRear());


    }
}
