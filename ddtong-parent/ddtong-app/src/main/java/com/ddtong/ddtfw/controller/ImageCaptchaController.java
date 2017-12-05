package com.ddtong.ddtfw.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ddtong.ddtfw.captcha.SampleImageCaptchaService;


/**
 * 图形验证码
 */
@Controller
@RequestMapping("{zdpath}/captcha/image")
public class ImageCaptchaController {

	private static final Logger logger = LoggerFactory.getLogger(ImageCaptchaController.class);

	@Autowired
	private SampleImageCaptchaService sampleImageCaptchaService;

	@RequestMapping("")
	public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// set content type as jpeg
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// create the image using session ID
		logger.info("tring to get image captcha service");
		BufferedImage bufferedImage = sampleImageCaptchaService.getImageChallengeForID(request.getSession(true).getId());

		ServletOutputStream servletOutputStream = response.getOutputStream();

		// write the image to the servlet output stream
		logger.info("tring to output buffered image to servlet output stream");
		ImageIO.write(bufferedImage, "jpg", servletOutputStream);

		try {
			servletOutputStream.flush();
		} finally {
			servletOutputStream.close();
		}
	}
}
