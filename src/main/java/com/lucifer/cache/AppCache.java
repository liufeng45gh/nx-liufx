package com.lucifer.cache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by liufx on 16/1/5.
 */
@Service
public class AppCache {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public  <T> T find(String key, CacheProvider provider){
        Object object = redisTemplate.opsForValue().get(key);
        if (null != object) {
            return (T) object;
        }
        if(null == provider) {
            return null;
        }
        object = provider.getData();
        if (object == null) {
           return null;
        }
        redisTemplate.opsForValue().set(key,object);
        return (T) object;
    }
    
    public void set(String key,Object object){
    	redisTemplate.opsForValue().set(key,object);
    }

    public void set(String key,Object object,Integer timeout){
        redisTemplate.opsForValue().set(key,object);
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    public void remove(String key){
        redisTemplate.delete(key);
    }

    /**
     * 删除所有匹配
     * @param key 例如 user:*
     */
    @SuppressWarnings("unchecked")
    public void removeAll(String key){       
	Set<String> keys = redisTemplate.keys(key);
        for(String aKey : keys) {
            redisTemplate.delete(aKey);
        }
    }


}
