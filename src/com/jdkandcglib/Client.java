package com.jdkandcglib;

/**
 * Created by lynch on 2019-08-27. <br>
 **/
public class Client {
    public static void main(String[] args) {
        System.out.println("-----------CGLibProxy-------------");
        UserManager userManager = (UserManager) new CGLibProxy()
                .createProxyObject(new UserManagerImpl());
        userManager.addUser("tom", "root");
        System.out.println("-----------JDKProxy-------------");
        JDKProxy jdkPrpxy = new JDKProxy();
        UserManager userManagerJDK = (UserManager) jdkPrpxy
                .newProxy(new UserManagerImpl());
        userManagerJDK.addUser("tom", "root");
    }
}
