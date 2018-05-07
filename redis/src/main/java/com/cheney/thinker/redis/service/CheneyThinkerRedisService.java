package com.cheney.thinker.redis.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class CheneyThinkerRedisService {

    @Autowired
    private StringRedisTemplate stingRedisTemplate;

    public void setString(String key, String value, Long time) {
        setObject(key, value, time);
    }

    public void setString(String key, String value) {
        setObject(key, value, null);
    }

    public void setList(String key, List<String> value) {
        setObject(key, value, null);
    }

    public void setObject(String key, Object value, Long time) {
        if (StringUtils.isEmpty(key) || null == value) {
            return;
        }
        if (value instanceof String) {
            String strValue = (String) value;
            stingRedisTemplate.opsForValue().set(key, strValue);
            if (null != time) {
                stingRedisTemplate.opsForValue().set(key, strValue, time, TimeUnit.SECONDS);
            }
            return;
        }
        if (value instanceof List) {
            List<String> stringList = (List<String>) value;
            for (String strValue : stringList) {
                stingRedisTemplate.opsForList().leftPush(key, strValue);
            }
            return;
        }
    }

    public String getString(String key) {
        return stingRedisTemplate.opsForValue().get(key);
    }

    public List<String> getList(String key) {
        int size = stingRedisTemplate.opsForList().size(key).intValue();
        List<String> stringList = new ArrayList<>(size);
        while (size-- > 0) {
            stringList.add(stingRedisTemplate.opsForList().rightPopAndLeftPush(key, key));
        }
        return stringList;
    }

    public Boolean remove(String key) {
        return stingRedisTemplate.delete(key);
    }

}
