package base.noPattern;

import java.util.concurrent.TimeUnit;

public interface CacheService {
    String get(final String key, int redisType);
    void set(String key,String value, int redisType);
    void set(String key, String value, Long timeOut, TimeUnit timeUnit, int redisType);
    void del(String key, int redisType);
}
