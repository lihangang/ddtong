package com.ddtong.util.rsa.basic;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyHelper {

	/**
	 * 生成RSA密钥对(默认密钥长度为1024)
	 * 
	 * @return
	 */
	public static KeyPair generateRSAKeyPair() {
		return generateRSAKeyPair(1024);
	}

	/**
	 * 生成RSA密钥对
	 * 
	 * @param keyLength
	 *            密钥长度，范围：512～2048
	 * @return
	 */
	public static KeyPair generateRSAKeyPair(int keyLength) {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(keyLength);
			return kpg.genKeyPair();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static String encodePublicKeyToStr(PublicKey key) throws Exception {
		byte[] keyBytes = key.getEncoded();
		String s = Base64Helper.encode(keyBytes);
		return s;
	}

	public static String encodePrivateKeyToStr(PrivateKey key) throws Exception {
		byte[] keyBytes = key.getEncoded();
		String s = Base64Helper.encode(keyBytes);
		return s;
	}

	/**
	 * 从字符串中加载公钥
	 * 
	 * @param publicKeyStr
	 *            公钥数据字符串
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static RSAPublicKey decodePublicKeyFromStr(String publicKeyStr) throws Exception {
		try {
			byte[] buffer = Base64Helper.decode(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	/**
	 * 从字符串加密私钥
	 * 
	 * @param privateKeyStr
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public static RSAPrivateKey decodePrivateKeyFromStr(String privateKeyStr) throws Exception {
		try {
			byte[] buffer = Base64Helper.decode(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("私钥非法");
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}

	/**
	 * java端公钥转换成C#公钥
	 */
	public static String encodePublicKeyToXml(PublicKey key) {
		if (!RSAPublicKey.class.isInstance(key)) {
			return null;
		}
		RSAPublicKey pubKey = (RSAPublicKey) key;
		StringBuilder sb = new StringBuilder();

		sb.append("<RSAKeyValue>");
		sb.append("<Modulus>").append(Base64Helper.encode(pubKey.getModulus().toByteArray())).append("</Modulus>");
		sb.append("<Exponent>").append(Base64Helper.encode(pubKey.getPublicExponent().toByteArray())).append("</Exponent>");
		sb.append("</RSAKeyValue>");
		return sb.toString();
	}

	/**
	 * C#端公钥转换成java公钥
	 * 
	 */
	public static PublicKey decodePublicKeyFromXml(String xml) {
		xml = xml.replaceAll("\r", "").replaceAll("\n", "");
		BigInteger modulus = new BigInteger(1, Base64Helper.decode(getMiddleString(xml, "<Modulus>", "</Modulus>")));
		BigInteger publicExponent = new BigInteger(1, Base64Helper.decode(getMiddleString(xml, "<Exponent>", "</Exponent>")));
		RSAPublicKeySpec rsaPubKey = new RSAPublicKeySpec(modulus, publicExponent);
		KeyFactory keyf;
		try {
			keyf = KeyFactory.getInstance("RSA");
			return keyf.generatePublic(rsaPubKey);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * C#端私钥转换成java私钥
	 * 
	 */
	public static PrivateKey decodePrivateKeyFromXml(String xml) {
		xml = xml.replaceAll("\r", "").replaceAll("\n", "");
		BigInteger modulus = new BigInteger(1, Base64Helper.decode(getMiddleString(xml, "<Modulus>", "</Modulus>")));
		BigInteger publicExponent = new BigInteger(1, Base64Helper.decode(getMiddleString(xml, "<Exponent>", "</Exponent>")));
		BigInteger privateExponent = new BigInteger(1, Base64Helper.decode(getMiddleString(xml, "<D>", "</D>")));
		BigInteger primeP = new BigInteger(1, Base64Helper.decode(getMiddleString(xml, "<P>", "</P>")));
		BigInteger primeQ = new BigInteger(1, Base64Helper.decode(getMiddleString(xml, "<Q>", "</Q>")));
		BigInteger primeExponentP = new BigInteger(1, Base64Helper.decode(getMiddleString(xml, "<DP>", "</DP>")));
		BigInteger primeExponentQ = new BigInteger(1, Base64Helper.decode(getMiddleString(xml, "<DQ>", "</DQ>")));
		BigInteger crtCoefficient = new BigInteger(1, Base64Helper.decode(getMiddleString(xml, "<InverseQ>", "</InverseQ>")));

		RSAPrivateCrtKeySpec rsaPriKey = new RSAPrivateCrtKeySpec(modulus, publicExponent, privateExponent, primeP, primeQ, primeExponentP, primeExponentQ,
				crtCoefficient);
		KeyFactory keyf;
		try {
			keyf = KeyFactory.getInstance("RSA");
			return keyf.generatePrivate(rsaPriKey);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * java端私钥转换成C#私钥
	 * 
	 */
	public static String encodePrivateKeyToXml(PrivateKey key) {
		if (!RSAPrivateCrtKey.class.isInstance(key)) {
			return null;
		}
		RSAPrivateCrtKey priKey = (RSAPrivateCrtKey) key;
		StringBuilder sb = new StringBuilder();

		sb.append("<RSAKeyValue>");
		sb.append("<Modulus>").append(Base64Helper.encode(priKey.getModulus().toByteArray())).append("</Modulus>");
		sb.append("<Exponent>").append(Base64Helper.encode(priKey.getPublicExponent().toByteArray())).append("</Exponent>");
		sb.append("<P>").append(Base64Helper.encode(priKey.getPrimeP().toByteArray())).append("</P>");
		sb.append("<Q>").append(Base64Helper.encode(priKey.getPrimeQ().toByteArray())).append("</Q>");
		sb.append("<DP>").append(Base64Helper.encode(priKey.getPrimeExponentP().toByteArray())).append("</DP>");
		sb.append("<DQ>").append(Base64Helper.encode(priKey.getPrimeExponentQ().toByteArray())).append("</DQ>");
		sb.append("<InverseQ>").append(Base64Helper.encode(priKey.getCrtCoefficient().toByteArray())).append("</InverseQ>");
		sb.append("<D>").append(Base64Helper.encode(priKey.getPrivateExponent().toByteArray())).append("</D>");
		sb.append("</RSAKeyValue>");
		return sb.toString();
	}

	/**
	 * 返回两个字符串中间的内容
	 * 
	 * @param all
	 * @param start
	 * @param end
	 * @return
	 */
	public static String getMiddleString(String all, String start, String end) {
		int beginIdx = all.indexOf(start) + start.length();
		int endIdx = all.indexOf(end);
		return all.substring(beginIdx, endIdx);
	}
}
