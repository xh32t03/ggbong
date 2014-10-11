package com.cxf.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * 
 * @Title: 采用Httpclient组件访问相关的WebService
 * @Description:
 * @Copyright:Copyright (c) 2011
 * @Company:易程科技股份有限公司
 * @Date:2012-4-13
 * @author liuxl
 * @version 1.0
 */
public class HttpClientUtil {
	
	public static String getMethod(String url){
		return callService(url, "Get");
	}
	public static String postMethod(String url){
		return callService(url, "Post");
	}
	
	/**
	 * 
	 * @param httpurl
	 * @return
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
			System.out.println(new String(responseBody));
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
	 * 使用HttpClient调用远程servlet
	 * 
	 * @param httpurl
	 * @param xmlInfo
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static InputStream executeHttp(String httpurl, String xmlInfo,
			Map<String, String> map) {
		HttpClient httpclient = new HttpClient();
		// 使用Post发送消息的方法的应用
		PostMethod postmethod = new PostMethod(httpurl);
		ByteArrayRequestEntity requestEntity = new ByteArrayRequestEntity(
				xmlInfo.getBytes(), "text/html; charset=UTF-8");
		InputStream inputstream = null;
		// 设置请求的实体
		postmethod.setRequestEntity(requestEntity);
		// 设置请求的格式
		try {
			// 设置消息头信息
			if (map != null) {
				for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
					Entry<String, String> header = (Entry<String, String>) it
							.next();
					String key = header.getKey();
					String value = header.getValue();
					postmethod.setRequestHeader(key, value);
				}
			}
			// 发送消息的方法的
			httpclient.executeMethod(postmethod);

			// 发送成功接受请求的信息
			if (postmethod.getStatusCode() == HttpStatus.SC_OK) {
				inputstream = postmethod.getResponseBodyAsStream();
			} else {
				System.out.println("unexpected failure:"
						+ postmethod.getStatusLine());
			}

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postmethod.releaseConnection();
		}
		return inputstream;
	}

	/**
	 * 使用HttpClient调用远程servlet
	 * 
	 * @param httpurl
	 * @param xmlInfo
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static InputStream executeHttp(String httpurl,
			Map<String, String> paramMaps, Map<String, String> map) {
		HttpClient httpclient = new HttpClient();
		// 使用Post发送消息的方法的应用
		PostMethod postmethod = new PostMethod(httpurl);
		InputStream inputstream = null;
		// 设置请求的填入各个表单域的值
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		NameValuePair[] params = new NameValuePair[paramMaps.size()];
		if (paramMaps != null) {
			for (Iterator it = paramMaps.entrySet().iterator(); it.hasNext();) {
				Entry<String, String> header = (Entry<String, String>) it
						.next();
				String key = header.getKey();
				String value = header.getValue();
				NameValuePair param = new NameValuePair();
				param.setName(key);
				param.setValue(value);
				paramList.add(param);
			}
		}
		paramList.toArray(params);
		postmethod.setRequestBody(params);
		// 设置请求的格式
		try {
			if (map != null) {
				// 设置消息头信息
				for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
					Entry<String, String> header = (Entry<String, String>) it
							.next();
					String key = header.getKey();
					String value = header.getValue();
					postmethod.setRequestHeader(key, value);
				}
			}
			// 发送消息的方法的
			int statusCode = httpclient.executeMethod(postmethod);
			// 自动转向的方式的应用
			// HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发301或者302
			if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY
					|| statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
				Header locationHeader = postmethod.getRequestHeader("location");
				String location = null;
				if (locationHeader != null) {
					location = locationHeader.getValue();
					System.out
							.println("the page was redirected to:" + location);

				} else {
					System.out.println("Location field value is null！");
				}
			}
			// 发送成功接受请求的信息
			if (postmethod.getStatusCode() == HttpStatus.SC_OK) {
				inputstream = postmethod.getResponseBodyAsStream();
			} else {
				System.out.println("unexpected failure:"
						+ postmethod.getStatusLine());
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postmethod.releaseConnection();
		}
		return inputstream;
	}
}
