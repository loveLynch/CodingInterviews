package com.thread;

/**
 * Created by lynch on 2019-09-02. <br>
 * 实现Runnable接口创建线程
 * 如果自己的类已经extends另一个类，就无法直接extends Thread，
 * 此时，可以实现一个Runnable接口
 **/
public class MultiThread2 implements Runnable {

    @Override
    public void run() {
        System.out.println("MultiThread2 " + Thread.currentThread().getName() + " start!");

    }

    public static void main(String[] args) {
        MultiThread2 myThread1 = new MultiThread2();
        Thread thread1 = new Thread(myThread1);
        thread1.start();
        MultiThread2 myThread2 = new MultiThread2();
        Thread thread2 = new Thread(myThread2);
        thread2.start();
    }
}
