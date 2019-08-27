package com.singleton;

/**
 * Created by lynch on 2019-08-27. <br>
 **/

/**
 * 单例模式之懒汉模式(特点：要用到的时候才创建)
 * 特殊点：当多线程的场合，一开始会创建多个对象，所以此场景下懒汉模式不适用
 */
public class Singleton2 {

    /**
     * 私有化构造方法，防止外部调用创建对象
     */
    private Singleton2() {

    }

    /**
     * 初始化对象为空，当需要的时候再创建
     */
    private static final Singleton2 singleObj = null;

    /**
     * 给外围提供统一获取对象入口
     *
     * @return
     */
    public static Singleton2 getInstance() {

        // 当对象为空的时候就创建
        if (singleObj == null) {
            return new Singleton2();
        }

        // 否则就直接返回
        return singleObj;
    }

}
