package com.cheney.thinker.redis.controller;

import com.cheney.thinker.redis.service.CheneyThinkerRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/CheneyThinkerRedis")
public class CheneyThinkerRedisController {

    @Autowired
    private CheneyThinkerRedisService service;

    @RequestMapping("/setString")
    public String setString(String key, String value) {
        service.setString(key, value);
        return "SUCCESS";
    }

    @RequestMapping("/setList")
    public String setList(String key) {
        List<String> stringList = new ArrayList<>();
        stringList.add("Cheney");
        stringList.add("Thinker");
        stringList.add("Redis");
        service.setList(key, stringList);
        return "SUCCESS";
    }

    @RequestMapping("/getString")
    public String getString(String key) {
        return service.getString(key);
    }

    @RequestMapping("/getList")
    public List<String> getList(String key) {
        return service.getList(key);
    }

    @RequestMapping("/remove")
    public String remove(String key) {
        service.remove(key);
        return "SUCCESS";
    }

}
