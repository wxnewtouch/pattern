package base.hasPattern.factory;

import base.hasPattern.CacheService;
import base.hasPattern.factory.impl.EGMCacheAdapter;
import base.hasPattern.factory.impl.IIRCacheAdapter;
import base.hasPattern.impl.CacheServiceImpl;
import junit.framework.TestCase;
import org.junit.Test;

public class JDKProxyTest extends TestCase {
    @Test
    public void test_jdkProxy() throws Exception {
        CacheService proxy_EGM = JDKProxy.getProxy(CacheServiceImpl.class,new EGMCacheAdapter());
        proxy_EGM.set("user_name_01", "小傅哥");
        String val01 = proxy_EGM.get("user_name_01");
        System.out.println("测试结果：" + val01);

        CacheService proxy_IIR = JDKProxy.getProxy(CacheServiceImpl.class,new IIRCacheAdapter());
        proxy_IIR.set("user_name_01", "小傅哥");
        String name01 = proxy_IIR.get("user_name_01");
        System.out.println("测试结果：" + name01);

    }

}