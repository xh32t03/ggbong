package com.cxf.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @Description: WebService客户端调用服务端通用类
 * @author joly
 */
public class WsClientTest {
	/**
	 * @Title: callService
	 * @Description: 调用远程的ws.webservice并返回数据
	 * @param wsUrl
	 *            ws地址
	 * @param method
	 *            调用的ws方法名
	 * @param args
	 *            参数
	 * @return：String
	 * @throws
	 */
	public static String callService(String wsUrl, String method, Object... args) {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(wsUrl);
		Object[] res = null;
		try {
			res = client.invoke(method, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (String) res[0];
	}
	
	/**
	 * @Title: jerseyGetMessages
	 * @Description: 使用第三方jar包jersey-client-1.0.3.1.jar、jersey-core-1.0.3.1.jar实现与 REST 服务通讯
	 * @param url

	 * @return：String
	 * @throws
	 */
	public static String jerseyGetMessages(String url) {
		com.sun.jersey.api.client.Client c = com.sun.jersey.api.client.Client
				.create();
		com.sun.jersey.api.client.WebResource r = c.resource(url);
		com.sun.jersey.api.client.ClientResponse response = r
				.get(ClientResponse.class);
		System.err.println(response.getStatus());
		System.err.println(response.getHeaders().get("Content-Type"));
		String entity = response.getEntity(String.class);
		System.out.println("jerseyGetMessages返回数据："+entity);
		return entity;
	}
	
	public static String getMessages(String url){
		HttpClient hc = new HttpClient();//创建一个客户端，类似打开一个浏览器 
		GetMethod get = new GetMethod(url);//创建一个get方法，类似在浏览器地址栏中输入一个地址 
		//设置请求头、设置编码
		get.setRequestHeader("accept", "application/json");
		hc.getParams().setContentCharset("UTF-8");
		try {
			int statusCode = hc.executeMethod(get);
			System.err.println("Get方式返回的状态码是：" + statusCode);
			if (statusCode == 200) {
				String str = get.getResponseBodyAsString();
				System.err.println("返回信息：\n" + str);
				return str;
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			get.releaseConnection();
		}
		return null;
	}
	public static String postMessages(String url, Map<String, Object> parmMap){
		PostMethod pm = new PostMethod(url);
		pm.setRequestHeader("accept", "application/json");
		pm.setRequestHeader("Encoding", "UTF-8");
		
		if(parmMap!=null && parmMap.size()>0){
			for(String key : parmMap.keySet()){
				pm.setParameter(key, (String) parmMap.get(key));
			}
		}
		
		HttpClient hc = new HttpClient();
		hc.getParams().setContentCharset("UTF-8");// 设置编码，否则会返回中文乱码
		try {
			int statusCode = hc.executeMethod(pm);
			System.err.println("Post方式返回的状态码是：" + statusCode);
			if (statusCode == 200) {
				String str = pm.getResponseBodyAsString();
				System.err.println(">>:" + str);
				return str;
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			pm.releaseConnection();
		}
		return null;
	}
	
	public static String getMethod(String url){
		return callService(url, "Get");
	}
	public static String postMethod(String url){
		return callService(url, "Post");
	}
	/**
	 * @Title: callService
	 * @Description: httpclient调用远程的webservice并返回JSON数据
	 * @param url
	 *            ws地址
	 * @param method
	 *            调用方式[Get、Post]
	 * @return
	 * @return：String
	 * @throws
	 */
	public static String callService(String url, String method) {
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		HttpMethod httpmethod = null;
		if (method.equals("Get")) {
			// 创建GET方法的实例
			httpmethod = new GetMethod(url);
		} else {
			// 创建POST方法的实例
			httpmethod = new PostMethod(url);
		}

		// 设置相关的参数信息
		HttpMethodParams httpMethodParams = httpmethod.getParams();
		// 使用系统提供的默认的恢复策略
		httpMethodParams.setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 设置编码
		httpMethodParams.setContentCharset("utf-8");
		httpMethodParams.setParameter("Content-Type",
				"application/json; charset=utf-8");

		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(httpmethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + httpmethod.getStatusLine());
			}
			// 读取内容
			byte[] responseBody = httpmethod.getResponseBody();
			// 处理内容
			System.err.println("callService返回数据："+new String(responseBody));
			return new String(responseBody, "utf-8");
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			httpmethod.releaseConnection();
		}
		return null;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://192.168.213.181/mc2_zngf_test/DictionaryService.svc/Dictionary/Json";
		String str = getMessages(url);
//		System.out.println(str);
//		System.out.println(getMethod(url));
		System.out.println(jerseyGetMessages(url));
	}
}
