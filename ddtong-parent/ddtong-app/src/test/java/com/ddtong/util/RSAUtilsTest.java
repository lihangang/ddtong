package com.ddtong.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ddtong.util.rsa.RSAUtils;
import com.ddtong.util.rsa.basic.KeyVo;

public class RSAUtilsTest {

	private static final Logger logger = LoggerFactory.getLogger(RSAUtilsTest.class);

	//@Test
	public void jiami() {

		try {
			KeyVo keyVo = RSAUtils.generateKeys();

			String publicKey = keyVo.getRsaPublicKey();
			String privateKey = keyVo.getRsaPrivateKey();
			logger.info("公钥:" + publicKey);
			logger.info("私钥:" + privateKey);

			String sourceData = "rsa test test";
			logger.info("原文本:" + sourceData);
			String dataEnc = RSAUtils.encryptByPublicKey(sourceData, publicKey);
			logger.info("加密后:" + dataEnc);
			String dataDec = RSAUtils.decryptByPrivateKey(dataEnc, privateKey);
			logger.info("解密后:" + dataDec);

		} catch (Exception e) {
			logger.error("RSA error.....", e);
		}
	}
}
