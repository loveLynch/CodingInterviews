package com.others;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by lynch on 2019-09-20. <br>
 * 四个线程和一个变量i=1
 * 两个线程对i执行加1操作
 * 另外两个线程对i执行减1操作
 **/
public class MainThread {
    public static volatile int i = 1;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        while (true) {
        try {
            Callable less1 = new LessCall(i);
            Future fLess1 = pool.submit(less1);
            i = (int) fLess1.get();
            System.out.println(i);
            Callable add1 = new AddCall(i);
            Future fAdd1 = pool.submit(add1);
            i = (int) fAdd1.get();
            System.out.println(i);
            Callable less2 = new LessCall(i);
            Future fLess2 = pool.submit(less2);
            i = (int) fLess2.get();
            System.out.println(i);
            Callable add2 = new AddCall(i);
            Future fAdd2 = pool.submit(add2);
            i = (int) fAdd2.get();
            System.out.println(i);


        } catch (Exception e) {
            e.printStackTrace();
        }
        }


    }

}

class AddCall implements Callable<Integer> {
    int i;

    AddCall(int i) {
        this.i = i;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("当前线程 " + Thread.currentThread().getName());
        return i + 1;
    }
}

class LessCall implements Callable<Integer> {
    int i;

    LessCall(int i) {
        this.i = i;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("当前线程 " + Thread.currentThread().getName());
        return i - 1;
    }
}

class AddThread implements Runnable {
    int i;

    public AddThread(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        i++;
    }
}

class LessThread implements Runnable {
    int i;

    public LessThread(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        i--;
    }
}

