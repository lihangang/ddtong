package com.ddtong.util.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientBaseUtil extends HttpClientBase {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientBaseUtil.class);

	/**
	 * 读取流
	 * 
	 * @param inStream
	 * @return 字节数组
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}

	/**
	 * 
	 * Content-Type: application/x-www-form-urlencoded; charset=UTF-8
	 * 
	 * @param url
	 *            调用地址
	 * @param headParamMap
	 *            头部信息
	 * @param bodyParamMap
	 *            发送参数 (UTF-8)
	 * @return
	 * @throws Exception
	 */
	public static String postMap(String url, Map<String, String> headParamMap, Map<String, String> bodyParamMap) throws Exception {
		CloseableHttpClient httpClient = buildHttpClient();
		CloseableHttpResponse httpResponse = null;
		logger.info("\n请求地址: " + url);
		try {
			HttpPost httpPost = new HttpPost(url);
			buildHeadInfo(httpPost, headParamMap);
			HttpEntity httpEntity = buildHttpEntity(bodyParamMap);
			httpPost.setEntity(httpEntity);

			httpResponse = execute(httpClient, httpPost);
			String responseContent = parseToString(httpResponse);
			return responseContent;
		} catch (Exception e) {
			throw e;
		} finally {
			close(httpResponse, httpClient);
		}
	}
	
	/**
	 * Content-Type: HttpContentTypes
	 * 
	 * @param url
	 *            调用地址
	 * @param headParamMap
	 *            头部信息
	 * @param contentTypes
	 *            数据类型
	 * @param bodyString
	 *            发送数据
	 * @return
	 * @throws Exception
	 */
	public static String postBody(String url, Map<String, String> headParamMap, HttpContentTypes contentTypes, String bodyString) throws Exception {
		CloseableHttpClient httpClient = buildHttpClient();
		CloseableHttpResponse httpResponse = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			buildHeadInfo(httpPost, headParamMap);
			HttpEntity httpEntity = buildHttpEntity(contentTypes, bodyString);
			httpPost.setEntity(httpEntity);

			logger.info("\n请求地址: " + url);
			logger.info("\n请求body: " + bodyString);

			httpResponse = execute(httpClient, httpPost);
			String responseContent = parseToString(httpResponse);
			return responseContent;
		} catch (Exception e) {
			throw e;
		} finally {
			close(httpResponse, httpClient);
		}
	}

	/**
	 * GET 方式调用
	 * 
	 * @param url
	 *            调用地址
	 * @param headParamMap
	 *            请求头
	 * @return
	 * @throws Exception
	 */
	public static String get(String url, Map<String, String> headParamMap) throws Exception {
		CloseableHttpClient httpClient = buildHttpClient();
		CloseableHttpResponse httpResponse = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			buildHeadInfo(httpGet, headParamMap);

            logger.info("\n请求地址: " + url);
			httpResponse = execute(httpClient, httpGet);
			String responseContent = parseToString(httpResponse);
			return responseContent;
		} catch (Exception e) {
			throw e;
		} finally {
			close(httpResponse, httpClient);
		}
	}

	/**
	 * GET 方式调用
	 * 
	 * @param url
	 *            调用地址
	 * @param headParamMap
	 *            请求头
	 * @param paramMap
	 *            请参数
	 * @param charset
	 *            url 的参数编码
	 * @return
	 * @throws Exception
	 */
	public static String get(String url, Map<String, String> headParamMap, Map<String, String> paramMap, String charset) throws Exception {
		if (paramMap == null || paramMap.size() <= 0) {
			return get(url, headParamMap);
		}

		StringBuffer sf = new StringBuffer(url);
		if (url.indexOf("?") < 0) {
			sf.append("?");
		}

		if (!url.endsWith("?") && !url.endsWith("&")) {
			sf.append("&");
		}

		for (String key : paramMap.keySet()) {
			sf.append(key).append("=").append(URLEncoder.encode(paramMap.get(key), charset));
			sf.append("&");
		}

		String zurl = sf.toString();
		if (zurl.endsWith("&")) {
			zurl = zurl.substring(0, zurl.length() - 1);
		}

		return get(zurl, headParamMap);
	}

	/**
	 * GET 方式调用
	 * 
	 * @param url
	 *            调用地址
	 * @param headParamMap
	 *            请求头
	 * @param paramMap
	 *            请参数 , 参数编码默认UTF-8
	 * @return
	 * @throws Exception
	 */
	public static String get(String url, Map<String, String> headParamMap, Map<String, String> paramMap) throws Exception {
		return get(url, headParamMap, paramMap, "UTF-8");
	}

	/**
	 * GET 方式调用
	 * 
	 * @param url
	 *            调用地址
	 * @return
	 * @throws Exception
	 */
	public static String get(String url) throws Exception {
		return get(url, null, null, "UTF-8");
	}
	
	/**
	 * 文件上传
	 * 
	 * @param url
	 * @param headParamMap
	 *            头部data
	 * @param paramMap
	 *            form data
	 * @param filemap
	 *            上传的文件
	 * @return
	 * @throws Exception
	 */
	public static String upload(String url, Map<String, String> headParamMap, Map<String, String> paramMap, Map<String, File> filemap) throws Exception {
		if (filemap == null || filemap.size() <= 0) {
			logger.info("filemap is empty");
			return "";
		}

		CloseableHttpClient httpClient = buildHttpClient();
		CloseableHttpResponse httpResponse = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			buildHeadInfo(httpPost, headParamMap);

			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			ContentType contentType = ContentType.create("text/plain", Consts.UTF_8);

			if (paramMap != null && paramMap.size() > 0) {
				for (String paramname : paramMap.keySet()) {
					// 相当于 <input type="text" name="userName" value=userName>
					multipartEntityBuilder.addTextBody(paramname, paramMap.get(paramname), contentType);
				}
			}
			if (filemap != null && filemap.size() > 0) {
				for (String filename : filemap.keySet()) {
					// 相当于<input type="file" name="file"/>
					multipartEntityBuilder.addBinaryBody(filename, filemap.get(filename));
				}
			}

			HttpEntity httpEntity = multipartEntityBuilder.build();
			httpPost.setEntity(httpEntity);

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
