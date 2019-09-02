package com.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by lynch on 2019-09-02. <br>
 * 使用ExecutorService、Callable、Future实现有返回结果的线程
 * ExecutorService、Callable、Future三个接口实际上都是属于Executor框架。返回结果的线程是在JDK1.5中引入的新特征，有了这种特征就不需要再为了得到返回值而大费周折了。而且自己实现了也可能漏洞百出。
 * <p>
 * 可返回值的任务必须实现Callable接口。类似的，无返回值的任务必须实现Runnable接口。
 * <p>
 * 执行Callable任务后，可以获取一个Future的对象，在该对象上调用get就可以获取到Callable任务返回的Object了。
 * <p>
 * 注意：get方法是阻塞的，即：线程无返回结果，get方法会一直等待。
 **/
public class MultiThread4 {

    /**
     * 有返回值的线程
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws ExecutionException,
            InterruptedException {
        System.out.println("----程序开始运行----");
        Date date1 = new Date();

        int taskSize = 5;
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Callable c = new MyCallable(i + " ");
            // 执行任务并获取Future对象
            Future f = pool.submit(c);
            // System.out.println(">>>" + f.get().toString());
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();

        // 获取所有并发任务的运行结果
        for (Future f : list) {
            // 从Future对象上获取任务的返回值，并输出到控制台
            System.out.println(">>>" + f.get().toString());
        }

        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【"
                + (date2.getTime() - date1.getTime()) + "毫秒】");
    }
}

class MyCallable implements Callable<Object> {
    private String taskNum;

    MyCallable(String taskNum) {
        this.taskNum = taskNum;
    }

    public Object call() throws Exception {
        System.out.println(">>>" + taskNum + "任务启动");
        Date dateTmp1 = new Date();
        Thread.sleep(1000);
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println(">>>" + taskNum + "任务终止");
        return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
    }
}
