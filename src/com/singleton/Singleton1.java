package com.singleton;

/**
 * Created by lynch on 2019-08-27. <br>
 **/
/**
 * 单例模式之饿汉模式(特点：一开始就创建)
 */
public class Singleton1 {

    /**
     * 私有化构造方法，防止外部调用创建对象
     */
    private Singleton1() {

    }

    /** 类加载时就创建  */
    private static final Singleton1 singleObj = new Singleton1();

    /**
     * 给外围提供统一获取对象入口
     *
     * @return
     */
    public static Singleton1 getInstance() {
        return singleObj;
    }

}