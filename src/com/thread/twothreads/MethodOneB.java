package com.thread.twothreads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lynch on 2019-09-03. <br>
 * 利用Lock和Condition：
 **/
public class MethodOneB {
    private Lock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();
    private final ThreadToGo threadToGo = new ThreadToGo();

    public Runnable newThreadOne() {
        final String[] inputArr = Helper.buildNoArr(52);
        return new Runnable() {
            private String[] arr = inputArr;

            public void run() {
                for (int i = 0; i < arr.length; i = i + 2) {
                    try {
                        lock.lock();
                        while (threadToGo.value == 2)
                            condition.await();
                        Helper.print(arr[i], arr[i + 1]);
                        threadToGo.value = 2;
                        condition.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
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
                    try {
                        lock.lock();
                        while (threadToGo.value == 1)
                            condition.await();
                        Helper.print(arr[i]);
                        threadToGo.value = 1;
                        condition.signal();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };
    }

    class ThreadToGo {
        int value = 1;
    }

    public static void main(String args[]) throws InterruptedException {
        MethodOneB two = new MethodOneB();
        Helper.instance.run(two.newThreadOne());
        Helper.instance.run(two.newThreadTwo());
        Helper.instance.shutdown();
    }
}