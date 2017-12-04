package com.ddtong.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.ddtong.util.baiduapi.BaiduApiClient;

public class BaiduApiTest {
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		//String outsring = BaiduApiClient.geocoder("河南省南阳市第一人民医院", "1KUqK0y6NqrZVOSVBXIyWverKcrMpXbG", "ZUqoBfIoC3V61BfujL6ZKTkQdh690sfF");
		//String outsring = BaiduApiClient.ungeocoder("112.54619494620123","33.00944589540308", "1KUqK0y6NqrZVOSVBXIyWverKcrMpXbG", "ZUqoBfIoC3V61BfujL6ZKTkQdh690sfF");
		String outsring = BaiduApiClient.ungeocoder("112.54619494620123","33.00944589540308", "1KUqK0y6NqrZVOSVBXIyWverKcrMpXbG", "ZUqoBfIoC3V61BfujL6ZKTkQdh690sfF");
		
		//String outsring = BaiduApiClient.geocoder("北京月坛公园", "1KUqK0y6NqrZVOSVBXIyWverKcrMpXbG", "ZUqoBfIoC3V61BfujL6ZKTkQdh690sfF");
		//String outsring = BaiduApiClient.ungeocoder("116.35888186152688","39.921476172949727", "1KUqK0y6NqrZVOSVBXIyWverKcrMpXbG", "ZUqoBfIoC3V61BfujL6ZKTkQdh690sfF");
	}
}
