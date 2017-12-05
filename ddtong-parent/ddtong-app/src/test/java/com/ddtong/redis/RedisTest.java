package com.ddtong.redis;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.ddtong.core.entity.YshDemoEntity;
import com.ddtong.core.enums.YshDemoSexEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private DdtRedisClient ddtRedisClient;
	
	//@Test
	public void test() throws Exception {
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}

	//@Test
	public void testObj() throws Exception {
		YshDemoEntity entity = new YshDemoEntity("yshdemo1", "a123456", YshDemoSexEnum.MAN);
		ValueOperations<String, YshDemoEntity> operations = redisTemplate.opsForValue();
		operations.set("com.neox", entity);
		operations.set("com.neo.f", entity, 1, TimeUnit.SECONDS);
		Thread.sleep(1000);
		// redisTemplate.delete("com.neo.f");
		boolean exists = redisTemplate.hasKey("com.neo.f");
		if (exists) {
			System.out.println("exists is true");
		} else {
			System.out.println("exists is false");
		}
		Assert.assertEquals("yshdemo1", operations.get("com.neox").getUserName());
	}
}
