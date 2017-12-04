package com.ddtong.core.util.rsa.basic;

import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class RsaHelper {

	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 64;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	// 用公钥加密
	public static byte[] encryptData(byte[] data, PublicKey pubKey) {
		try {
			/*
			 * Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			 * cipher.init(Cipher.ENCRYPT_MODE, pubKey); return
			 * cipher.doFinal(data);
			 */
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);

			int inputLen = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}
				// 写入内存缓冲区
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			// 获取内存缓冲中的数据，转换成数组
			byte[] decryptedData = out.toByteArray();
			out.close();
			return decryptedData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 用私钥解密
	public static byte[] decryptData(byte[] encryptedData, PrivateKey priKey) {
		try {
			/*
			 * Cipher cipher = Cipher.getInstance("RSA");
			 * cipher.init(Cipher.DECRYPT_MODE, priKey); return
			 * cipher.doFinal(encryptedData);
			 */
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, priKey);
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
				}
				// 写入内存缓冲区
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			// 获取内存缓冲中的数据，转换成数组
			byte[] decryptedData = out.toByteArray();
			out.close();
			return decryptedData;
		} catch (Exception e) {
			return null;
		}
	}

}
