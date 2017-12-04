package com.ddtong.util.baiduapi;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ddtong.util.httpclient.HttpClientBaseUtil;

public class BaiduApiClient {

	private static final Logger logger = LoggerFactory.getLogger(BaiduApiClient.class);

	/**
	 * Geocoding API 是一类简单的HTTP接口，用于提供从地址到经纬度坐标或者从经纬度坐标到地址的转换服务
	 * 
	 * @param address
	 * @param yourak
	 *            应用AK
	 * @param yoursk
	 *            应用SK
	 * @return
	 */
	public static String geocoder(String address, String yourak, String yoursk) {
		try {
			/*
			 * 计算sn跟参数对出现顺序有关，所以用LinkedHashMap保存<key,value>，此方法适用于get请求，
			 * 如果是为发送post请求的url生成签名，请保证参数对按照key的字母顺序依次放入Map。以get请求为例：http://api.
			 * map. baidu.com/geocoder/v2/?address=百度大厦&output=json&ak=yourak，
			 * paramsMap中先放入address，再放output，然后放ak，放入顺序必须跟get请求中对应参数的出现顺序保持一致。
			 */
			Map<String, String> paramsMap = new LinkedHashMap<String, String>();
			paramsMap.put("address", address);
			paramsMap.put("output", "json");
			paramsMap.put("ak", yourak);

			BaiduSnCal snCal = new BaiduSnCal();

			// 调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，拼接返回结果address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
			String paramsStr = snCal.toQueryString(paramsMap);

			// 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk得到/geocoder/v2/?address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
			String wholeStr = new String("/geocoder/v2/?" + paramsStr + yoursk);

			// 对上面wholeStr再作utf8编码
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");

			// 调用下面的MD5方法得到最后的sn签名7de5a22212ffaa9e326444c75a58f9a0
			String sn = snCal.MD5(tempStr);

			String addressEncode = URLEncoder.encode(address, "UTF-8");
			String url = String.format("http://api.map.baidu.com/geocoder/v2/?address=%s&output=%s&ak=%s&sn=%s", addressEncode, paramsMap.get("output"), yourak,
					sn);

			String outstring = HttpClientBaseUtil.get(url, null);
			return outstring;
		} catch (Exception e) {
			logger.error("调用Geocoding API 异常", e);
			return null;
		}
	}

	/**
	 * 通过经纬度获取地理信息
	 * 
	 * @param lng
	 *            经度
	 * @param lat
	 *            纬度
	 * @param yourak
	 *            应用AK
	 * @param yoursk
	 *            应用SK
	 * @return
	 */
	public static String ungeocoder(String lng, String lat, String yourak, String yoursk) {
		try {
			/*
			 * 计算sn跟参数对出现顺序有关，所以用LinkedHashMap保存<key,value>，此方法适用于get请求，
			 * 如果是为发送post请求的url生成签名，请保证参数对按照key的字母顺序依次放入Map。以get请求为例：http://api.
			 * map. baidu.com/geocoder/v2/?address=百度大厦&output=json&ak=yourak，
			 * paramsMap中先放入address，再放output，然后放ak，放入顺序必须跟get请求中对应参数的出现顺序保持一致。
			 */
			String location = lat + "," + lng;
			Map<String, String> paramsMap = new LinkedHashMap<String, String>();
			paramsMap.put("location", location);
			paramsMap.put("output", "json");
			paramsMap.put("ak", yourak);

			BaiduSnCal snCal = new BaiduSnCal();

			// 调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，拼接返回结果address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
			String paramsStr = snCal.toQueryString(paramsMap);

			// 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk得到/geocoder/v2/?address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
			String wholeStr = new String("/geocoder/v2/?" + paramsStr + yoursk);

			// 对上面wholeStr再作utf8编码
			String tempStr = URLEncoder.encode(wholeStr, "UTF-8");

			// 调用下面的MD5方法得到最后的sn签名7de5a22212ffaa9e326444c75a58f9a0
			String sn = snCal.MD5(tempStr);

			String locationEncode = URLEncoder.encode(location, "UTF-8");
			String url = String.format("http://api.map.baidu.com/geocoder/v2/?location=%s&output=%s&ak=%s&sn=%s", locationEncode, paramsMap.get("output"),
					yourak, sn);

			String outstring = HttpClientBaseUtil.get(url, null);
			return outstring;
		} catch (Exception e) {
			logger.error("调用Geocoding API 异常", e);
			return null;
		}
	}

}
