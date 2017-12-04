package com.ddtong.demo.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddtong.demo.service.YshDemoService;
import com.ddtong.entity.YshDemoEntity;
import com.ddtong.enums.YshDemoSexEnum;

@RestController
public class YshDemoController {

	@Autowired
	private YshDemoService yshDemoService;
	

	@RequestMapping("/getYshDemos")
	public List<YshDemoEntity> getUsers() {
		List<YshDemoEntity> users = yshDemoService.getAll();
		return users;
	}


	
	@RequestMapping("/getYshDemo")
	public YshDemoEntity getUser(Long id) {
		YshDemoEntity user = yshDemoService.getOne(id);
		return user;
	}

	@RequestMapping("/addYshDemo")
	public void save(YshDemoEntity user) {
		//if (user == null) {
			user = new YshDemoEntity("哈哈", "0987654", YshDemoSexEnum.MAN);
		//}
		
		yshDemoService.insert(user);
	}

	@RequestMapping(value = "updateYshDemo")
	public void update(YshDemoEntity user) {
		yshDemoService.update(user);
	}

	@RequestMapping(value = "/deleteYshDemo/{id}")
	public void delete(@PathVariable("id") Long id) {
		yshDemoService.delete(id);
	}

}