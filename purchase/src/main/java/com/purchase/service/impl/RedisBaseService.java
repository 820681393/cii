package com.purchase.service.impl;

import com.purchase.service.IRedisBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by Owner on 2018/9/27.
 */
@Component
public class RedisBaseService<K,V>  implements IRedisBaseService<K,V>{
    @Autowired
    private RedisTemplate<K,V> redisTemplate;

    public void add(K key, V value) {
        if (redisTemplate == null) {
            System.out.println("redisTemplate 实例化失败");
            return;
        } else {
            redisTemplate.opsForValue().set(key, value, 365, TimeUnit.DAYS);
        }
    }

    @Override
    public void addTimeMinute(K key, V value, int minute) {
        if(minute==0){
            minute=10;
        }
        if (redisTemplate == null) {
            System.out.println("redisTemplate 实例化失败");
            return;
        } else {
            redisTemplate.opsForValue().set(key, value, minute, TimeUnit.MINUTES);
        }
    }

    @Override
    public V get(K key) {
        V value =redisTemplate.opsForValue().get(key);
        return value;
    }

    @Override
    public void addToken(K key, V value) {
        if (redisTemplate == null) {
            System.out.println("redisTemplate 实例化失败");
            return;
        } else {
            redisTemplate.opsForValue().set(key, value, 365, TimeUnit.DAYS);
        }
    }

    @Override
    public void delete(K key) {
        redisTemplate.delete(key);
    }

}
