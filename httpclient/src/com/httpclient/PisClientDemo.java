package com.httpclient;


import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.httpclient.dto.QueryParamsDTO;
import com.httpclient.dto.TrainInfoDTO;
import com.httpclient.utils.ClientUtils;
import com.httpclient.utils.HttpClientUtil;
import com.thoughtworks.xstream.XStream;

public class PisClientDemo {
	XStream xstream = new XStream();
	public static void main(String[] args) {
//		System.out.println("单项查询接口");
//		client.testQueryByUserInfo();
		System.out.println("*********************");
		System.out.println("1234567".substring(0,3));
		System.out.println("1234567".substring(4));
		System.out.println(Integer.parseInt("1230567".substring(3)));
		
//		System.out.println("测试AJAX请求");
//		client.testAjaxRequestWhitSession();
//		System.out.println("*********************");
//		JSONObject json = post();
//		System.out.println("*********************");
//		if(json!=null){
//			System.out.println(json.toString());
//		}
	}
	private static HttpClient client = HttpClientUtil.getHttpClient();
	public static final String serverUrl = "http://127.0.0.1:8080/pis/";
	private static String encoding = "utf-8";
	public static JSONObject post() {
		
		
		String logurl = serverUrl + "loginAction?loginName=211382198808293916&password=123456";
		
		HttpPost logpost = new HttpPost(logurl);
		try {
			HttpResponse res = client.execute(logpost);
			System.out.println(res.getStatusLine().getStatusCode());
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			System.out.println("login OK");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		String url = serverUrl + "query/queryByUserInfo/list?startDate=2013-05-01&endDate=2013-05-29&id_kind=1&id_no=211322198702285284";
		HttpPost post = new HttpPost(url);
		JSONObject json = null;
		try {
			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				json = new JSONObject(new JSONTokener(new InputStreamReader(
						entity.getContent(), encoding)));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			// 关闭连接 ,释放资源
			post.releaseConnection();
		}
		return json;
	}
	
	/**
	 * 测试开放的http post方式接口的controller
	 */
	public void testQueryByUserInfo() {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		QueryParamsDTO queryParamsDTO = new QueryParamsDTO();
		queryParamsDTO.setStartDate("20130522");
		queryParamsDTO.setEndDate("20130528");
		queryParamsDTO.setId_kind("1");
		List<String> list = new ArrayList<String>();
		list.add("211322198702285284");
		queryParamsDTO.setListId_no(list);
		xstream.alias("queryParamsDTO", QueryParamsDTO.class);
		String param = xstream.toXML(queryParamsDTO);
//		 String param = "";
		
		URI uri;
		try {
//			uri = URIUtils.createURI("http", "10.3.0.211:8180", -1,
//					"/pis/rest/queryByUserInfo", "", null);
			uri = URIUtils.createURI("http", "127.0.0.1:8080", -1,
					"/pis/rest/queryByUserInfo", "", null);
			HttpPost httpPost = new HttpPost(uri);
			// 解决中文参数乱码问题
			HttpEntity entity = new StringEntity(param);
			httpPost.setEntity(entity);
			ClientUtils.setRequestHeader(httpPost);
			HttpResponse response = httpclient.execute(httpPost);
			response.setHeader("Content-Type", "text/plain;charset=UTF_8");
			entity = response.getEntity();
			System.out.println("test BatchCheck");
			String xml = new String(EntityUtils.toString(entity));
			System.out.println(xml);
			xstream.alias("trainInfo", TrainInfoDTO.class);
			List<TrainInfoDTO> result = (List<TrainInfoDTO>) xstream.fromXML(xml);
			for (int i = 0; i < result.size(); i++) {
				TrainInfoDTO info = result.get(i);
				System.out.println("统计日期:"+info.getStatist_date());
				System.out.println("姓名:"+info.getId_name());
				System.out.println("证件类型"+info.getId_kind());
				System.out.println("证件编号："+info.getId_no());
				System.out.println("乘车日期:"+info.getTrain_date());
				System.out.println("发车时间:"+info.getStart_time());
				System.out.println("发站:"+info.getFrom_station_name());
				System.out.println("到站:"+info.getTo_station_name());
				System.out.println("车次"+info.getBoard_train_code());
				System.out.println("状态:"+info.getStutsMessage());
			}
			httpclient.getConnectionManager().shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}