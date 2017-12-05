package com.ddtong.demo.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ddtong.core.entity.YshDemoEntity;
import com.ddtong.core.enums.YshDemoSexEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YshDemoMapperTest {

	@Autowired
	private YshDemoService yshDemoService;

	//@Test
	public void testInsert() throws Exception {
		yshDemoService.insert(new YshDemoEntity("yshdemo1", "a123456", YshDemoSexEnum.MAN));
		yshDemoService.insert(new YshDemoEntity("yshdemo2", "b123456", YshDemoSexEnum.WOMAN));
	}

	//@Test
	public void testQuery() throws Exception {
		List<YshDemoEntity> yshdemo = yshDemoService.getAll();
		if (yshdemo == null || yshdemo.size() == 0) {
			System.out.println("is null");
		} else {
			System.out.println(yshdemo.toString());
		}
	}

}