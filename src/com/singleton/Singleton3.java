package com.singleton;

/**
 * Created by lynch on 2019-08-27. <br>
 * 双重检查锁
 * 使用懒汉式写法，即延迟加载，当需要用到实例的时候，才去初始化(new)此实例。
 **/
public class Singleton3 {
    // 静态实例变量加上volatile
    private static volatile Singleton3 instance;
// 私有化构造函数

    private Singleton3() {

    }

    // 双重检查锁
    public static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}