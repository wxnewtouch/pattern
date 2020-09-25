package base.serviceImpl;

import base.CacheService;
import base.RedisUtils;

import java.util.concurrent.TimeUnit;

public class CacheServiceImpl implements CacheService {

    private RedisUtils redisUtils = new RedisUtils();

    public String get(String key) {
        return redisUtils.get(key);
    }

    public void set(String key, String value) {
        redisUtils.set(key, value);
    }

    public void set(String key, String value, Long timeOut, TimeUnit timeUnit) {
        redisUtils.set(key, value, timeOut, timeUnit);
    }

    public void del(String key) {
        redisUtils.del(key);
    }
}
