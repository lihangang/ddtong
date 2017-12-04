package com.ddtong.exception;

import com.ddtong.vo.ApiResponseResult;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -7145287979732096910L;
	
	protected ApiResponseResult result;

	public ServiceException(ApiResponseResult result) {
		super(result.getMessage());
		this.result = result;
	}

	public ServiceException(ApiResponseResult result, Throwable cause) {
		super(result.getMessage(), cause);
		this.result = result;
	}

	public ApiResponseResult getResult() {
		return result;
	}

	public static ServiceException failure(String message) {
		return new ServiceException(ApiResponseResult.failure(message));
	}

	public static ServiceException failure(String message, Throwable cause) {
		return new ServiceException(ApiResponseResult.failure(message), cause);
	}

	public static ServiceException failure(String status, String message) {
		return new ServiceException(ApiResponseResult.failure(message).status(status));
	}

	public static ServiceException failure(String status, String message, Throwable cause) {
		return new ServiceException(ApiResponseResult.failure(message).status(status), cause);
	}
}
