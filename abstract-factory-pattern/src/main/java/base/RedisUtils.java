package base;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisUtils {
    private Map<String, String> dataMap = new ConcurrentHashMap<String, String>();

    public String get(String key) {
        log.info("redis获取数据 key {}", key);
        return dataMap.get(key);
    }

    public void set(String key, String value) {
        log.info("redis写入数据key{},value{}", key, value);
        dataMap.put(key, value);
    }

    public void set(String key, String value, Long timeOut, TimeUnit timeUnit) {
        log.info("redis写入数据key{},value{},timeOut{},timeUnit{}", key, value, timeOut, timeUnit.toString());
        dataMap.put(key, value);
    }

    public void del(String key) {
        log.info("redis删除数据key{}", key);
        dataMap.remove(key);
    }
}
