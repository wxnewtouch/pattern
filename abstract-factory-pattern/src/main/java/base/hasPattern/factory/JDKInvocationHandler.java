package base.hasPattern.factory;

import base.hasPattern.util.ClassLoaderUtils;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@AllArgsConstructor
public class JDKInvocationHandler implements InvocationHandler {
    private ICacheAdapter iCacheAdapter;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return iCacheAdapter.getClass().getMethod(method.getName(), ClassLoaderUtils.getClazzByArgs(args)).invoke(iCacheAdapter, args);
    }
}
