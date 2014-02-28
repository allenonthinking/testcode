/*
 * Copyright 2003 电子计算技术研究所
 * All Right Reserved
 * Author ：Administrator
 * 编码日期格式：2013-8-24
 */
package com.httpclient.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * 
 * 
 */
public class HttpClientUtil {
	private static HttpParams httpParams;
	private static PoolingClientConnectionManager cm;

	/**
	 * 最大连接数
	 */
	public  int MAX_TOTAL = 400;
	/**
	 * 每个路由默认最大连接数
	 */
	public  int DEFAULT_MAX_PER_ROUTE = 400;
	/**
	 * 连接超时
	 */
	public static int CONNECTION_TIMEOUT = 1000;
	/**
	 * socket连接超时
	 */
	public static  int SOCKET_TIMEOUT = 1000;

	private static HttpClient httpClient;

	private static HttpClientUtil httpClientUtil = new HttpClientUtil();
	private HttpClientUtil() {
		this.init();
	}
	public static HttpClientUtil  getInstance(){
		return httpClientUtil;
	}

	public void init() {
		if (cm == null) {
//			#总最大连接数
//			http.maxTotal=400
//			#每路由最大连接数
//			http.defaultMaxPerRoute=400
//			#连接超时毫秒数
//			http.connectionTimeout=100000
//			#socket超时毫秒数
//			http.socketTimeout=100000
			try {
				this.MAX_TOTAL = Integer.parseInt("400");
				this.DEFAULT_MAX_PER_ROUTE = Integer.parseInt("400");
				this.CONNECTION_TIMEOUT = Integer.parseInt("100000");
				this.SOCKET_TIMEOUT = Integer.parseInt("100000");
			} catch (Exception e) {
				this.MAX_TOTAL = 400;
				this.DEFAULT_MAX_PER_ROUTE = 400;
				this.CONNECTION_TIMEOUT = 1000;
				this.SOCKET_TIMEOUT = 1000;
			}
			Scheme http = new Scheme("http", 80,PlainSocketFactory.getSocketFactory());
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(http);
			cm = new PoolingClientConnectionManager(registry);

			cm.setMaxTotal(MAX_TOTAL);
			cm.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
			
			

//			HttpParams params = new BasicHttpParams();
//			params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
//					CONNECTION_TIMEOUT);
//			params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SOCKET_TIMEOUT);
		}

	}

	public void destory() {
		cm.shutdown();
	}

	public static synchronized HttpClient getHttpClient() {
		if (httpClient == null) {
			httpClient = new DefaultHttpClient(cm, httpParams);
			httpClient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);
			httpClient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, SOCKET_TIMEOUT);
		}
		return httpClient;
	}

}
