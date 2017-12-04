package com.ddtong.demo.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddtong.core.entity.YshExampleEntity;
import com.ddtong.core.enums.YshExampleSexEnum;
import com.ddtong.demo.service.YshExampleService;

@RestController
public class YshExampleController {

	@Autowired
	private YshExampleService yshExampleService;
	

	@RequestMapping("/getYshExamples")
	public List<YshExampleEntity> getUsers() {
		List<YshExampleEntity> users = yshExampleService.getAll();
		return users;
	}
	
	@RequestMapping("/getYshExamples2")
	public List<YshExampleEntity> getUsers2() {
		List<YshExampleEntity> users = yshExampleService.getAll();
		return users;
	}

	@RequestMapping("/getYshExample")
	public YshExampleEntity getUser(Long id) {
		YshExampleEntity user = yshExampleService.getOne(id);
		return user;
	}

	@RequestMapping("/addYshExample")
	public void save(YshExampleEntity user) {
		//if (user == null) {
			user = new YshExampleEntity("嗯嗯水", "adfe784521", YshExampleSexEnum.MAN);
		//}
		yshExampleService.insert(user);
	}

	@RequestMapping(value = "updateYshExample")
	public void update(YshExampleEntity user) {
		yshExampleService.update(user);
	}

	@RequestMapping(value = "/deleteYshExample/{id}")
	public void delete(@PathVariable("id") Long id) {
		yshExampleService.delete(id);
	}

}