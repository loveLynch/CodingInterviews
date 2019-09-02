package com.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lynch on 2019-09-02. <br>
 **/


public class CountNum implements Runnable {
    public int lastNum;

    public CountNum(int lastNum) {
        this.lastNum = lastNum;
    }

    private static AtomicInteger count = new AtomicInteger(0);

    public synchronized static void calc(int lastNum) {
        if ((count.get()) < lastNum) {
            int c = count.incrementAndGet();// 自增1,返回更新值
            System.out.println("正在运行是线程" + Thread.currentThread().getName() + ":" + c);
        }

    }

    public void run() {
        while (true) {
            CountNum.calc(lastNum);
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int lastNum = 10;
            CountNum thread = new CountNum(lastNum);
            Thread t = new Thread(thread);
            t.start();

        }


    }


}
