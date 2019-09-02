package com.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by lynch on 2019-09-02. <br>
 * 实现Callable接口通过FutureTask包装器来创建Thread线程
 * <p>
 * Callable接口（也只有一个方法）
 **/
public class MultiThread3 {
    static class ThreadDemo implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = 0; i <= 10; i++) {
                System.out.println("MultiThread3 " + Thread.currentThread().getName() + " start!");
                sum += i;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        ThreadDemo td1 = new ThreadDemo();

        // 1.执行Callable方式，需要FutureTask实现类的支持，用于接收运算结果
        FutureTask<Integer> result1 = new FutureTask<>(td1);
        new Thread(result1).start();

        ThreadDemo td2 = new ThreadDemo();
        // 1.执行Callable方式，需要FutureTask实现类的支持，用于接收运算结果
        FutureTask<Integer> result2 = new FutureTask<>(td2);
        new Thread(result2).start();

        // 2.接收线程运算后的结果
        Integer sum;
        try {
            //等所有线程执行完，获取值，因此FutureTask 可用于 闭锁
            sum = result1.get();
            System.out.println("-----------------------------");
            System.out.println(sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
