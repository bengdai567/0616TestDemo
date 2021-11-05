package com.example.demo.util;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

public class JedisTest {
    @Test
    public void jedisTest(){
        Jedis jedis = new Jedis("localhost",6379);
        JedisPool jedisPool = new JedisPool();
        jedis.setDataSource(jedisPool);
//        String redis_fz4307867388710912 = jedis.set("testOnetestOne","12355677890",);
        //设置过期时间30秒
        String redis_fz4307867388710912 = jedis.setex("testOnetestOne",30,"12355677890");
        //setrange覆盖原先值，不会覆盖过期时间，但是如果字数小于以前的覆盖数，后面后保留
        final Long testOnetestOne = jedis.setrange("testOnetestOne", 0, "主线程现成线程主线程");
        System.out.println(testOnetestOne);
//        String redis_j80833117634719744 = jedis.get("redis_J80833117634719744");
//        jedis.setex("100", "2", "NX", "1000", TimeUnit.SECONDS);
//        jedis.setex("100", "2","1000", TimeUnit.SECONDS);
//        CacheSourceManager cacheSourceManager = applicationContext.getBean("cacheSourceManager");
        System.out.println(redis_fz4307867388710912);

        //非集群设置
        /*HostAndPort hostAndPort = new HostAndPort("192.168.80.190",7000);
        Set<HostAndPort> hostAndPortSet = new HashSet<>();
        hostAndPortSet.add(hostAndPort);
        JedisCluster jedis = new JedisCluster(hostAndPortSet);
        final String redis_j80833117634719744 = jedis.get("redis_J80833117634719744");
        System.out.println(redis_j80833117634719744);*/
    }
}
