package com.jdkandcglib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lynch on 2019-08-27. <br>
 * java动态代理是利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理
 * JDK动态代理只能对实现了接口的类生成代理，而不能针对类
 **/
public class JDKProxy implements InvocationHandler {

    private Object targetObject;//需要代理的目标对象

    /**
     * 将目标对象传入进行代理
     * @param targetObject
     * @return
     */
    public Object newProxy(Object targetObject) {
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(), this);//返回代理对象
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        checkPopedom();//一般我们进行逻辑处理的函数比如这个地方是模拟检查权限
        Object ret = null;      // 设置方法的返回值
        ret  = method.invoke(targetObject, args);       //调用invoke方法，ret存储该方法的返回值
        return ret;
    }

    /**
     * 模拟检查权限的例子
     */
    private void checkPopedom() {
        System.out.println(".:检查权限  checkPopedom()!");
    }
}
