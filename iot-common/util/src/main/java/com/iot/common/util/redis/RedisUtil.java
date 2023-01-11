package com.iot.common.util.redis;


import cn.hutool.json.JSONUtil;
import com.iot.common.util.JsonUtil;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtil<T> implements RedisObjectHashService<T> {

    private final StringRedisTemplate redisTemplate;

    public RedisUtil(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    /**
     * 写入缓存
     * @return boolean
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     * @param key redisKey
     * @param value 值
     * @return boolean
     */
    public boolean set(final String key, String value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     *
     * @param key redisKey
     * @param value 值
     * @param duration 过期时间
     */
    public void set(final String key, String value, Duration duration) {
        redisTemplate.opsForValue().set(key,value,duration);
    }

    /**
     * 批量删除对应的value
     */
    public void remove(final List<String> keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     * @param pattern 正则表达式
     */
    public void removePattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        assert keys != null;
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     * @param key redisKey
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     * @param key redisKey
     * @return boolean
     */
    public boolean exists(final String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * 读取缓存
     *
     * @param key redisKey
     * @return String
     */
    public String get(final String key) {
        String result;
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 哈希 添加
     *
     * @param key redisKey
     * @param hashKey hashKey(property)
     */
    public void hmSet(String key, Object hashKey, Object value) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    /**
     * 哈希获取数据
     *
     * @param key redisKey
     * @param hashKey hashKey
     * @return Object value
     */
    public Object hmGet(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**
     * 列表添加 顺序执行，先进先出
     *
     * @param k list redisKey
     * @param v value
     */
    public void lPush(String k, String v) {
        ListOperations<String, String> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    /**
     * List<V> range(K key, long start, long end);
     *  返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定。 其中 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。
     *   你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     * @param k redisKey
     * @param start 0开始
     * @param end 结束
     */
    public List<String> lRange(String k, long start, long end) {
        ListOperations<String, String> list = redisTemplate.opsForList();
        return list.range(k, start, end);
    }

    /**
     * 集合添加
     *
     * @param key set redisKey
     * @param value 值
     */
    public void add(String key, String value) {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * 获取集合种所有元数据
     *
     * @param key redisKey
     * @return Set
     */
    public Set<String> setMembers(String key) {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加，并指定分数的成员 如果成员存在则覆盖
     *
     * @param key redisKey
     * @param value 值
     * @param score 分数
     */
    public void zAdd(String key, String value, double score) {
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        zSet.add(key, value, score);
    }

    /**
     * 获取有序集合中分数在指定的最小值 与最大值之间的所有成员集合  闭合区间
     */
    public Set<String> rangeByScore(String key, double score, double score2) {
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        return zSet.rangeByScore(key, score, score2);
    }


    /**
     * redis for object data cache start
     */

    @Override
    public void putObject(String key,T t) {
        String json = JSONUtil.toJsonStr(t);
        Map<String, Object> map = JsonUtil.toMap(json);
        Map<String, String> stringMap = new HashMap<>(map.size());
        map.forEach((k, v) -> {
            String vv = "";
            if (v instanceof String) {
                vv = (String) v;
            } else {
                vv = JsonUtil.toString(v);
            }
            stringMap.put(k, vv);
        });
        redisTemplate.opsForHash().putAll(key, stringMap);
    }

    @Override
    public Long increment(String key, SFunction<? extends T, ? extends Number> fn, long delta) {
        String name = FunctionUtils.getFieldName(fn);
        return redisTemplate.opsForHash().increment(key, name, delta);
    }

    @Override
    public Object get(String key, SFunction<? extends T, ?> fn) {
        String name = FunctionUtils.getFieldName(fn);
        return redisTemplate.opsForHash().get(key, name);
    }

    @Override
    public T getObject(String key, Class<T> cls) {
        if (Boolean.FALSE.equals(redisTemplate.hasKey(key))) {
            return null;
        }
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        String json = JsonUtil.toString(map);
        return JsonUtil.toObject(json, cls);
    }

    @Override
    public void set(String key, SFunction<? extends T, ?> fn, String value) {
        String hashKey = FunctionUtils.getFieldName(fn);
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public void expire(String key, long timeout, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeout, timeUnit);
    }

    @Override
    public void expire(String key,Duration duration) {
        redisTemplate.expire(key,duration);
    }


    /**
     * 获取指定 key 的自增序列值
     * @param key redis key
     * @return 自增序列值
     */
    public Boolean setIfAbsent(String key, Long count, Duration duration) {
        return redisTemplate.opsForValue().setIfAbsent(key, String.valueOf(count), duration);
    }


    public Long incrementAndExpire(String key,Duration duration){
        Long count = redisTemplate.opsForValue().increment(key);
        redisTemplate.expire(key, duration);
        return count;
    }

    /**
     * 获取key剩余过期时间
     */
    public long getExpire(String key,TimeUnit timeUnit){
        var a = redisTemplate.getExpire(key, timeUnit);
        return Objects.nonNull(a)?a:0;
    }
}
