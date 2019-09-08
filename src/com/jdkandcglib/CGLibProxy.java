package com.jdkandcglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by lynch on 2019-08-27. <br>
 * cglib动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
 * CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法
 **/
public class CGLibProxy implements MethodInterceptor {
    private Object targetObject;// CGLib需要代理的目标对象

    public Object createProxyObject(Object obj) {
        this.targetObject = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        Object proxyObj = enhancer.create();
        return proxyObj;// 返回代理对象
    }

    /**
     * 拦截器的方法
     *
     * @param proxy       cglib代理后的对象
     * @param method      目标方法，调用该方法相当于直接调用目标方法
     * @param args        目标方法的方法参数
     * @param methodProxy 代理方法，invokeSuper绑定的类是代理类，invoke绑定的类是目标类
     */
    public Object intercept(Object proxy, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {
        Object obj = null;
        if ("addUser".equals(method.getName())) {// 过滤方法
            checkPopedom();// 检查权限
        }
        obj = method.invoke(targetObject, args);
        return obj;
    }

    private void checkPopedom() {
        System.out.println(".:检查权限  checkPopedom()!");
    }
}
