package com.ddtong.vo;

public class ApiResponseResult {
	private boolean result;// 成功为true，失败为false
	private String status = "";// 自定义的状态码
	private String message = "";
	private String debugMessage = "";
	private Object data;

	public ApiResponseResult() {
		super();
	}

	public ApiResponseResult(boolean success) {
		this.result = success;
	}

	public ApiResponseResult(boolean success, String message) {
		this.result = success;
		this.message = message;
	}

	public ApiResponseResult(boolean success, String message, Object data) {
		this.result = success;
		this.message = message;
		this.data = data;
	}

	public static ApiResponseResult success() {
		return new ApiResponseResult(true);
	}

	public static ApiResponseResult failure() {
		return new ApiResponseResult(false);
	}

	public static ApiResponseResult success(String message) {
		return new ApiResponseResult(true, message);
	}

	public static ApiResponseResult failure(String message) {
		return new ApiResponseResult(false, message);
	}

	public ApiResponseResult status(String status) {
		this.status = status;
		return this;
	}

	public ApiResponseResult data(Object data) {
		this.data = data;
		return this;
	}

	public ApiResponseResult debugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
		return this;
	}

	/* get set ..... */

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
