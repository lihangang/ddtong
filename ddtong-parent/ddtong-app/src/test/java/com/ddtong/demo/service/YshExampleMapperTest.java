package com.ddtong.demo.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ddtong.core.entity.YshExampleEntity;
import com.ddtong.core.enums.YshExampleSexEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YshExampleMapperTest {

	@Autowired
	private YshExampleService yshExampleService;

	//@Test
	public void testInsert() throws Exception {
		yshExampleService.insert(new YshExampleEntity("yshexample1", "a123456", YshExampleSexEnum.MAN));
		yshExampleService.insert(new YshExampleEntity("yshexample12", "b123456", YshExampleSexEnum.WOMAN));
	}

	//@Test
	public void testQuery() throws Exception {
		List<YshExampleEntity> yshdemo = yshExampleService.getAll();
		if (yshdemo == null || yshdemo.size() == 0) {
			System.out.println("is null");
		} else {
			System.out.println(yshdemo.toString());
		}
	}

}