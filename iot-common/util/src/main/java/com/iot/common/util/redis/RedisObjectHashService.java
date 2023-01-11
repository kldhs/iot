package com.iot.common.util.redis;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public interface RedisObjectHashService<T> {
    void putObject(String key, T t);

    Long increment(String key, SFunction<? extends T, ? extends Number> fn, long delta);

    Object get(String key, SFunction<? extends T, ?> fn);

    T getObject(String key, Class<T> cls);

    void set(String key, SFunction<? extends T, ?> fn, String value);

    void expire(String key, long timeout, TimeUnit timeUnit);

    void expire(String key, Duration duration);
}
