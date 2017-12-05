package com.ddtong.core.util;

import java.security.MessageDigest;

public class MD5 {
	
	/**
	 * 获得字符串的MD5摘要， 返回32位小写
	 */
	public static String encode(String origin) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			return hexStr(md.digest(origin.getBytes("UTF-8")));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * byte数组转换成hex字符串(小写)。
	 */
	private static String hexStr(byte[] ba) {
		String s = "";
		for (int i = 0, c = ba.length; i < c; i++) {
			s += String.format("%02x", ba[i]);
		}
		return s;
	}
}
