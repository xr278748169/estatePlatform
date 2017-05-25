package com.kerry.auth.controller;

import com.kerry.auth.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by wangshen on 2017/5/24.
 */
@Component
public class MyStartupRunner implements CommandLineRunner {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void run(String... strings) throws Exception {
        redisUtil.set("123123123","213123123123123");
    }
}
