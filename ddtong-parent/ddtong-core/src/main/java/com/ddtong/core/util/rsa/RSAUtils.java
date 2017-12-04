package com.ddtong.core.util.rsa;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import com.ddtong.core.util.rsa.basic.Base64Helper;
import com.ddtong.core.util.rsa.basic.KeyHelper;
import com.ddtong.core.util.rsa.basic.KeyVo;
import com.ddtong.core.util.rsa.basic.RsaHelper;

/**
 * 兼容模式 兼容 c# php <br>
 * 兼容模式,普通模式key格式是不一样的,秘钥转换KeyHelper<br>
 */
public class RSAUtils {

	/**
	 * 生成字符串类型的公钥、私钥对
	 * 
	 * @return map
	 * @throws Exception
	 */
	public static KeyVo generateKeys() throws Exception {
		KeyPair kp = KeyHelper.generateRSAKeyPair();
		PublicKey pubKey = kp.getPublic();
		PrivateKey priKey = kp.getPrivate();
		String pubKeyXml = KeyHelper.encodePublicKeyToXml(pubKey);
		String priKeyXml = KeyHelper.encodePrivateKeyToXml(priKey);
		return new KeyVo(pubKeyXml, priKeyXml);
	}

	/**
	 * 公钥加密 兼容模式
	 * 
	 * @param data
	 *            源数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPublicKey(String sourceData, String publicKey) throws Exception {
		PublicKey publicKeyObj = KeyHelper.decodePublicKeyFromXml(publicKey);
		byte[] sourceDataByteArray = sourceData.getBytes("utf-8");
		byte[] encodeDataByteArray = RsaHelper.encryptData(sourceDataByteArray, publicKeyObj);
		return Base64Helper.encode(encodeDataByteArray);
	}

	/**
	 * 私钥解密 兼容模式
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPrivateKey(String encryptedData, String privateKey) throws Exception {
		PrivateKey privateKeyObj = KeyHelper.decodePrivateKeyFromXml(privateKey);
		byte[] encryptedDataArray = Base64Helper.decode(encryptedData);
		byte[] decryptedDataByteArray = RsaHelper.decryptData(encryptedDataArray, privateKeyObj);
		if (decryptedDataByteArray == null)
			return null;
		String decryptedData = new String(decryptedDataByteArray, "utf-8");
		return decryptedData;
	}

	public static void main(String[] args) {
		KeyVo keyvo = null;
		try {
			keyvo = generateKeys();
			System.out.println("公钥：" + keyvo.getRsaPublicKey());
			System.out.println("私钥：" + keyvo.getRsaPrivateKey());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			String estr = encryptByPublicKey("中国制造 made in china", keyvo.getRsaPublicKey());
			System.out.println("密文：" + estr);
			String dstr = decryptByPrivateKey(estr, keyvo.getRsaPrivateKey());
			System.out.println("明文：" + dstr);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
