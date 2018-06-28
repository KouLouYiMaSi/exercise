package com.huo.demos.redis;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemoTest {

    @Autowired
    private RedisDemo redisDemo;

    @Test
    public void testSet() {
        RedisUtil ru = redisDemo.getRedisUtil();
        ru.delete("zsetkey");
        for (int i = 0; i < 100; i++) {
            ru.zAdd("zsetkey", "huo" + (i + 1), i);
        }
        Set<String> resultZset = ru.zRange("zsetkey", 0, 103);
        System.out.println(resultZset);
        System.out.println(ru.zSize("zsetkey"));
        ru.delete("ssetkey");
        for (int i = 0; i < 100; i++) {
            ru.sAdd("ssetkey", "zhang" + i);
        }
        Set<String> resultSet = ru.setMembers("ssetkey");
        System.out.println(resultSet);
        System.out.println(ru.sSize("ssetkey"));
    }

    @Test
    public void testList() {
        RedisUtil ru = redisDemo.getRedisUtil();
    }

}
