package com.niuke;

/**
 * Created by lynch on 2019-07-15. <br>
 **/
public class SingleTon2 {

    // 私有化构造函数
    private SingleTon2() {
    }

    // 利用静态内部类特性实现外部类的单例
    private static class SingleTonBuilder {
        private static SingleTon2 singleTon = new SingleTon2();
    }

    public static SingleTon2 getInstance() {
        return SingleTonBuilder.singleTon;
    }

    public static void main(String[] args) {
        SingleTon2 instance = SingleTon2.getInstance();
    }
}