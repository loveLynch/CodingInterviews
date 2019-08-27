package com.singleton;

/**
 * Created by lynch on 2019-08-27. <br>
 **/
public class Tests {
    public static void main(String[] args) {

        Singleton1 instance1 = Singleton1.getInstance();
        Singleton2 instance2 = Singleton2.getInstance();
        Singleton3 instance3 = Singleton3.getInstance();
        Singleton4 instance4 = Singleton4.getInstance();
    }
}
