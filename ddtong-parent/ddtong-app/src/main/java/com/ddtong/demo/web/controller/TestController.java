package com.ddtong.demo.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddtong.entity.YshDemoEntity;
import com.ddtong.enums.YshDemoSexEnum;
import com.ddtong.vo.ApiResponseResult;

@Controller
public class TestController {

	@RequestMapping("/test/index")
	public String index(HttpServletRequest request) {
		String cx = request.getSession().getServletContext().getRealPath("");
		System.out.println(cx);
		System.out.println("===============進入index============");
		return "/yshfm/index";
	}

	@RequestMapping(value = "/test/jsonp", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String jsonp() {
		System.out.println("===============進入jsonp============");
		return "這是test jsonp";
	}

	@RequestMapping(value = "/test/jsonres", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ApiResponseResult jsonres() {
		System.out.println("===============進入jsonres============");
		YshDemoEntity entity = new YshDemoEntity();
		entity.setId(12L);
		entity.setNickName("Test");
		entity.setUserSex(YshDemoSexEnum.MAN);

		return ApiResponseResult.success("成功").status("0").data(entity);
	}
}
