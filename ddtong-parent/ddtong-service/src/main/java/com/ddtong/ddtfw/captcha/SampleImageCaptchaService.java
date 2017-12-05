package com.ddtong.ddtfw.captcha;

import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

@Service
public class SampleImageCaptchaService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static ImageCaptchaService instance;

	private ImageCaptchaService getInstance() {
		if (instance == null) {
			// SimpleListImageCaptchaEngine()
			instance = new DefaultManageableImageCaptchaService(new FastHashMapCaptchaStore(), new CaptchaEngineEx(), 180, 100000, 75000);
		}
		return instance;
	}

	public BufferedImage getImageChallengeForID(String ID) throws CaptchaServiceException {
		if (instance == null) {
			getInstance();
		}

		return instance.getImageChallengeForID(ID);
	}

	public boolean validateResponseForID(String ID, Object response) {
		try {
			if (instance == null) {
				getInstance();
			}
			return instance.validateResponseForID(ID, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
}
