package com.ddtong.ddtfw.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ddtong.core.vo.ApiResponseResult;

/**
 * 文件上传
 */
@Controller
@RequestMapping(value = "/{zdpath}/ddtfw/uploadfile")
public class UploadFileContorller {

	private static final Logger logger = LoggerFactory.getLogger(UploadFileContorller.class);

	@RequestMapping(value = "/userHeadshot", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ApiResponseResult uploadUserHeadshot(@PathVariable("zdpath") String zdpath,
			@RequestParam(value = "data1", required = false) String data1,
			@RequestParam(value = "data2", required = false) String data2,
			@RequestParam(value = "data3", required = false) String data3,
			@RequestParam(value = "headimage", required = false) MultipartFile file, HttpServletRequest request) {

		logger.info(zdpath + "进入上传用户头像");

		// 重命名上传后的文件名
		String fileName = UUID.randomUUID().toString();
		// 定义上传路径
		String path = "F:/ddt-upload/" + fileName;
		File destFile = new File(path);

		try {
			file.transferTo(destFile);
			return ApiResponseResult.success("成功");
		} catch (Exception e) {
			logger.error("上传用户头像异常", e);
			return ApiResponseResult.failure("上传用户头像失败").debugMessage(e.getMessage());
		}
	}

}
