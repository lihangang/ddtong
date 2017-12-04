package com.ddtong.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;  


@Configuration
public class RedisCacheConfig  {
	
	 Logger logger = LoggerFactory.getLogger(RedisCacheConfig.class);   

    @Bean(name="jedisPool")
    public JedisPool redisPoolFactory( 
            @Value("${jedis.pool.host}")String host,   
            @Value("${jedis.pool.port}")int port,
            @Value("${jedis.pool.timeout}")int timeout,
            @Value("${jedis.pool.password}") String password,
            @Value("${jedis.pool.config.maxTotal}")int maxTotal,  
            @Value("${jedis.pool.config.maxIdle}")int maxIdle,  
            @Value("${jedis.pool.config.maxWaitMillis}")int maxWaitMillis) {
      
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        logger.info("JedisPool注入成功！！");
        logger.info("redis地址：" + host + ":" + port);
        logger.info("redis获取:"+jedisPool.getResource().toString());
        return jedisPool;
    }
      
}