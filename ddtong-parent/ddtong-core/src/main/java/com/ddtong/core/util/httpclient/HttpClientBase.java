package com.ddtong.core.util.httpclient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientBase {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientBase.class);

	public static final String charSet_encode = "UTF-8";

	/**
	 * 设置请求头部
	 * 
	 * @param httpRequest
	 * @param headParamMap
	 * @throws Exception
	 */
	public static void buildHeadInfo(HttpRequestBase httpRequest, Map<String, String> headParamMap) throws Exception {
		try {
			if (httpRequest == null) {
				return;
			}
			if (!(headParamMap != null && headParamMap.size() > 0)) {
				return;
			}
			for (String key : headParamMap.keySet()) {
				httpRequest.setHeader(key, headParamMap.get(key));
			}
		} catch (Exception e) {
			logger.error("设置请求头部异常  " + e.getMessage(), e);
			throw new Exception("设置请求头部异常", e);
		}

	}

	/**
	 * 设置请求参数
	 */
	public static HttpEntity buildHttpEntity(HttpContentTypes contentTypes, String bodyString) throws Exception {
		try {
			StringEntity stringEntity = new StringEntity(bodyString, ContentType.create(contentTypes.getMimeType(), contentTypes.getCharset()));
			return stringEntity;
		} catch (Exception e) {
			logger.error("设置请求参数异常  " + e.getMessage(), e);
			throw new Exception("设置请求参数异常", e);
		}
	}

	private static List<NameValuePair> buildPrams(Map<String, String> params) throws Exception {
		List<NameValuePair> tl = new ArrayList<NameValuePair>();
		if (params == null || params.size() < 1)
			return null;
		for (Iterator<Entry<String, String>> iterator = params.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> param = iterator.next();
			tl.add(new BasicNameValuePair(param.getKey(), param.getValue()));
		}
		return tl;
	}

	/**
	 * 设置请求参数
	 */
	public static HttpEntity buildHttpEntity(Map<String, String> params) throws Exception {
		if (params == null || params.size() == 0) {
			return null;
		}
		try {
			HttpEntity httpEntity = new UrlEncodedFormEntity(buildPrams(params), charSet_encode);
			return httpEntity;
		} catch (Exception e) {
			logger.error("设置请求参数异常   " + e.getMessage(), e);
			throw new Exception("设置请求参数异常", e);
		}
	}

	/**
	 * 执行调用URL
	 */
	public static CloseableHttpResponse execute(CloseableHttpClient httpClient, HttpRequestBase httpRequest) throws Exception {
		try {
			return httpClient.execute(httpRequest);
		} catch (Exception e) {
			logger.error("请求异常  " + e.getMessage(), e);
			throw new Exception("请求异常", e);
		}
	}

	/**
	 * 读取响应
	 * 
	 * @param httpResponse
	 * @return
	 * @throws Exception
	 */
	public static String parseToString(CloseableHttpResponse httpResponse) throws Exception {
		try {
			String responseContent = EntityUtils.toString(httpResponse.getEntity(), charSet_encode);
			logger.info("\n请求返回消息: " + responseContent);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				return responseContent;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("读取响应异常  " + e.getMessage(), e);
			throw new Exception("读取响应异常", e);
		}
	}

	public static CloseableHttpClient buildHttpClient() {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		return httpClientBuilder.build();
	}

	/**
	 * 关闭响应和请求
	 * 
	 * @param httpResponse
	 * @param httpClient
	 */
	public static void close(CloseableHttpResponse httpResponse, CloseableHttpClient httpClient) {
		try {
			if (httpResponse != null) {
				httpResponse.close();
			}
			if (httpClient != null) {
				httpClient.close();
			}
		} catch (Exception e) {
			logger.error("关闭连接异常  " + e.getMessage(), e);
		}
	}
}
