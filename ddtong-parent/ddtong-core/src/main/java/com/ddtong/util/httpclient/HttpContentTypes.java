package com.ddtong.util.httpclient;

public enum HttpContentTypes {

	application_json("application/json", "UTF-8"),

	application_xml("application/xml", "UTF-8"),

	application_soap_xml("application/soap+xml", "UTF-8"),

	text_xml("text/xml", "UTF-8");

	private String mimeType;

	private String charset;

	HttpContentTypes(String mimeType, String charset) {
		this.mimeType = mimeType;
		this.charset = charset;
	}

	public String getMimeType() {
		return mimeType;
	}

	public String getCharset() {
		return charset;
	}

}
