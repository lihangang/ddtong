package com.ddtong.exception;

/**
 * 未登录异常定义
 *
 */
public class UnLoginAccoutException extends RuntimeException {

	private static final long serialVersionUID = -7623727729223436032L;

	public UnLoginAccoutException() {
		super();
	}

	public UnLoginAccoutException(String message) {
		super(message);
	}

	public UnLoginAccoutException(String message, Throwable cause) {
		super(message, cause);
	}
}
