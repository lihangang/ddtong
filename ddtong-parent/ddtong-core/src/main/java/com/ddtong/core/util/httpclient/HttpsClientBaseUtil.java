package com.ddtong.core.util.httpclient;

import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpsClientBaseUtil extends HttpClientBase {

	private static final Logger logger = LoggerFactory.getLogger(HttpsClientBaseUtil.class);

	public static String postBody(String url, Map<String, String> headParamMap, HttpContentTypes contentTypes, String bodyString) throws Exception {
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

		CloseableHttpResponse httpResponse = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			buildHeadInfo(httpPost, headParamMap);
			HttpEntity httpEntity = buildHttpEntity(contentTypes, bodyString);
			httpPost.setEntity(httpEntity);

			logger.info("\n请求地址: " + url);
			logger.info("\n请求body: \n" + bodyString);

			httpResponse = execute(httpClient, httpPost);
			String responseContent = parseToString(httpResponse);

			return responseContent;
		} catch (Exception e) {
			throw e;
		} finally {
			close(httpResponse, httpClient);
		}
	}
}
