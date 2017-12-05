package com.ddtong.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ddtong.core.common.DdtongConstant;

/**
 * 消费者
 *
 */
@Controller
@RequestMapping(DdtongConstant.APP_API_PATH + "/custom")
public class AppCustomController {

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		System.out.println("===============進入index============");
		return "/yshfm/index";
	}

}
