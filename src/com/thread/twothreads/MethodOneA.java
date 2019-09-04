package com.thread.twothreads;

/**
 * Created by lynch on 2019-09-03. <br>
 * 第一种解法，包含多种小的不同实现方式，但一个共同点就是靠一个共享变量来做控制
 * 利用最基本的synchronized、notify、wait：
 **/
public class MethodOneA {
    private final ThreadToGo threadToGo = new ThreadToGo();

    public Runnable newThreadOne() {
        final String[] inputArr = Helper.buildNoArr(52);
        return new Runnable() {
            private String[] arr = inputArr;

            public void run() {
                try {
                    for (int i = 0; i < arr.length; i = i + 2) {
                        synchronized (threadToGo) {
                            while (threadToGo.value == 2)
                                threadToGo.wait();
                            Helper.print(arr[i], arr[i + 1]);
                            threadToGo.value = 2;
                            threadToGo.notify();
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("Oops...");
                }
            }
        };
    }

    public Runnable newThreadTwo() {
        final String[] inputArr = Helper.buildCharArr(26);
        return new Runnable() {
            private String[] arr = inputArr;

            public void run() {
                try {
                    for (int i = 0; i < arr.length; i++) {
                        synchronized (threadToGo) {
                            while (threadToGo.value == 1)
                                threadToGo.wait();
                            Helper.print(arr[i]);
                            threadToGo.value = 1;
                            threadToGo.notify();
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("Oops...");
                }
            }
        };
    }

    class ThreadToGo {
        int value = 1;
    }

    public static void main(String args[]) throws InterruptedException {
        MethodOneA one = new MethodOneA();
        Helper.instance.run(one.newThreadOne());
        Helper.instance.run(one.newThreadTwo());
        Helper.instance.shutdown();
    }
}