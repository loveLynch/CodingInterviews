package com.thread;

/**
 * Created by lynch on 2019-09-02. <br>
 * 1、继承Thread类创建线程
 * Thread类本质上是实现了Runnable接口的一个实例，代表一个线程的实例。
 * 启动线程的唯一方法就是通过Thread类的start()实例方法。start()方法是一个native方法，
 * 它将启动一个新线程，并执行run()方法。这种方式实现多线程很简单，通过自己的类直接extend Thread，
 * 并复写run()方法，就可以启动新线程并执行自己定义的run()方法
 **/
public class MultiThread1 extends Thread {
    @Override
    public void run() {
        System.out.println("MultiThread1 " + Thread.currentThread().getName() + " start!");
    }

    public static void main(String[] args) {
        MultiThread1 myThread1 = new MultiThread1();
        MultiThread1 myThread2 = new MultiThread1();
        myThread1.start();
        myThread2.start();
    }
}
