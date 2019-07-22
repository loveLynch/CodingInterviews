package com.niuke;

/**
 * Created by lynch on 2019-07-15. <br>
 * 双重检验锁
 **/
public class SingleTon {
    // 静态实例变量加上volatile
    private static volatile SingleTon instance;
    // 私有化构造函数

    private SingleTon() {

    }
    //双重检验锁
    public static SingleTon getInstance() {
        if (instance == null) {
            synchronized (SingleTon.class) {
                if (instance == null) {
                    instance = new SingleTon();
                }
            }
        }
        return instance;
    }
}


