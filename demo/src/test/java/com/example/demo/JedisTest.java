package com.example.demo;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {
    @Test
    public void jedisTest(){
        Jedis jedis = new Jedis("11.50.36.32",6379);
        JedisPool jedisPool = new JedisPool();
        jedis.setDataSource(jedisPool);
        String redis_fz4307867388710912 = jedis.set("testOnetestOne","12355677890");

//        jedis.setex("100", "2", "NX", "1000", TimeUnit.SECONDS);
//        jedis.setex("100", "2","1000", TimeUnit.SECONDS);
//        CacheSourceManager cacheSourceManager = applicationContext.getBean("cacheSourceManager");
        System.out.println(redis_fz4307867388710912);
    }
}
