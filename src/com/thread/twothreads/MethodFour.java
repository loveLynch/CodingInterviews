package com.thread.twothreads;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lynch on 2019-09-03. <br>
 * 利用BlockingQueue；
 * BlockingQueue定义的常用方法如下:
 * <p>
 * add(Object)：把Object加到BlockingQueue里，如果BlockingQueue可以容纳，则返回true，否则抛出异常。
 * offer(Object)：表示如果可能的话，将Object加到BlockingQueue里，即如果BlockingQueue可以容纳，则返回true，否则返回false。
 * put(Object)：把Object加到BlockingQueue里，如果BlockingQueue没有空间，则调用此方法的线程被阻断直到BlockingQueue里有空间再继续。
 * poll(time)：获取并删除BlockingQueue里排在首位的对象，若不能立即取出，则可以等time参数规定的时间，取不到时返回null。当不传入time值时，立刻返回。
 * peek()：立刻获取BlockingQueue里排在首位的对象，但不从队列里删除，如果队列为空，则返回null。
 * take()：获取并删除BlockingQueue里排在首位的对象，若BlockingQueue为空，阻断进入等待状态直到BlockingQueue有新的对象被加入为止。
 **/
public class MethodFour {
    private final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    //一种是共享一个queue，根据peek和poll的不同来实现；
    public Runnable newThreadOne() {
        final String[] inputArr = Helper.buildNoArr(52);
        return new Runnable() {
            private String[] arr = inputArr;

            public void run() {
                for (int i = 0; i < arr.length; i = i + 2) {
                    Helper.print(arr[i], arr[i + 1]);
                    queue.offer("TwoToGo");
                    while (!"OneToGo".equals(queue.peek())) {
                    }
                    queue.poll();
                }
            }
        };
    }

    public Runnable newThreadTwo() {
        final String[] inputArr = Helper.buildCharArr(26);
        return new Runnable() {
            private String[] arr = inputArr;

            public void run() {
                for (int i = 0; i < arr.length; i++) {
                    while (!"TwoToGo".equals(queue.peek())) {
                    }
                    queue.poll();
                    Helper.print(arr[i]);
                    queue.offer("OneToGo");
                }
            }
        };
    }

    //第二种是两个queue，利用take()会自动阻塞来实现。
    private final LinkedBlockingQueue<String> queue1 = new LinkedBlockingQueue<>();
    private final LinkedBlockingQueue<String> queue2 = new LinkedBlockingQueue<>();

    public Runnable newThreadThree() {
        final String[] inputArr = Helper.buildNoArr(52);
        return new Runnable() {
            private String[] arr = inputArr;

            public void run() {
                for (int i = 0; i < arr.length; i = i + 2) {
                    Helper.print(arr[i], arr[i + 1]);
                    try {
                        queue2.put("TwoToGo");
                        queue1.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public Runnable newThreadFour() {
        final String[] inputArr = Helper.buildCharArr(26);
        return new Runnable() {
            private String[] arr = inputArr;

            public void run() {
                for (int i = 0; i < arr.length; i++) {
                    try {
                        queue2.take();
                        Helper.print(arr[i]);
                        queue1.put("OneToGo");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public static void main(String args[]) throws InterruptedException {
        MethodFour four = new MethodFour();
        Helper.instance.run(four.newThreadOne());
        Helper.instance.run(four.newThreadTwo());
        Thread.sleep(2000);
        System.out.println("");
        Helper.instance.run(four.newThreadThree());
        Helper.instance.run(four.newThreadFour());
        Helper.instance.shutdown();
    }
}
