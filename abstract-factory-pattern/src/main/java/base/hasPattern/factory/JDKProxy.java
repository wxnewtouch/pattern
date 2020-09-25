package base.hasPattern.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JDKProxy {
    public static <T>T getProxy(Class<T> interfaceClass, ICacheAdapter iCacheAdapter) throws Exception{
        InvocationHandler invocationHandler = new JDKInvocationHandler(iCacheAdapter);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] interfaces = interfaceClass.getInterfaces();
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{interfaces[0]}, invocationHandler);
    }
}
