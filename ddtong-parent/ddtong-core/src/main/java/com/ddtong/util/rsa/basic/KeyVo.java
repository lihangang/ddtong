package com.ddtong.util.rsa.basic;

public class KeyVo {
	private String rsaPublicKey;

	private String rsaPrivateKey;

	public KeyVo(String rsaPublicKey, String rsaPrivateKey) {
		super();
		this.rsaPublicKey = rsaPublicKey;
		this.rsaPrivateKey = rsaPrivateKey;
	}

	public String getRsaPublicKey() {
		return rsaPublicKey;
	}

	public String getRsaPrivateKey() {
		return rsaPrivateKey;
	}

}
