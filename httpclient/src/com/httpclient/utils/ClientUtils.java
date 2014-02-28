package com.httpclient.utils;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;


public class ClientUtils {

	/**
	 * 设置Http请求头信息
	 * 
	 * @param httpPost
	 */
	public static void setRequestHeader(HttpPost httpPost) {
		String cookie = "";
		// cookie取值为执行LoginClient.java后获取的响应头信息的Set-Cookie属性值
		cookie = "JSESSIONID=2AC8A6AC881B4924DB2C284AE64C1FF6; Path=/otsmobile";
		httpPost.setHeader("Cookie", cookie);
		// printRequestHeaders(httpPost);
	}

	/**
	 * 设置Http请求头信息
	 * 
	 * @param httpPost
	 */
	public static void setRequestHeader(HttpGet httpGet) {
		String cookie = "";
		// cookie取值为执行LoginClient.java后获取的响应头信息的Set-Cookie属性值
		cookie = "JSESSIONID=2AC8A6AC881B4924DB2C284AE64C1FF6; Path=/otsmobile";
		httpGet.setHeader("Cookie", cookie);
		// printRequestHeaders(httpPost);
	}

	/**
	 * 输出请求头信息
	 * 
	 * @param response
	 */
	public static void printRequestHeaders(HttpPost httpPost) {
		System.out.println("----------------请求头--------------------------");
		Header[] headers = httpPost.getAllHeaders();
		for (Header header : headers) {
			System.out.println(header.getName() + ":" + header.getValue());
		}
		System.out.println("------------------------------------------------");
	}

	/**
	 * 输出响应头信息
	 * 
	 * @param response
	 */
	public static void printResponseHeaders(HttpResponse response) {
		System.out.println("----------------响应头--------------------------");
		Header[] headers = response.getAllHeaders();
		for (Header header : headers) {
			System.out.println(header.getName() + ":" + header.getValue());
		}
		System.out.println("------------------------------------------------");
	}

	/**
	 * 获取当前时间戳，格式：yyyyMMddHHmmss
	 */
	// public static String getTimeStr() {
	// return DateUtils.dateToString(new Date(), DateUtils.yyyyMMddHHmmss);
	// }

	/**
	 * 获取校验码：当前时间戳+固定验证码的MD5值
	 */
	// public static String getCheckCode(String time_str, String device_no,
	// String pwd_str, String os_type) {
	// if ("i".equals(os_type)) {
	// pwd_str = OtsUtils.getIPhoneCode();
	// }
	// if ("a".equals(os_type)) {
	// pwd_str = OtsUtils.getAndroidCode();
	// }
	// return StringUtils.encrypt(pwd_str + time_str + device_no);
	// }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println(DateUtils.isDate("20091004"));
		// System.out.println(DateUtils.isDate("20091004",DateUtils.yyyyMMddHHmmss));
		// System.out.println(DateUtils.isDate("2009-1-04","yyyy-MM-dd"));
	}
}
