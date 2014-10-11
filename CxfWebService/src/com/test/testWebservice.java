package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;

public class testWebservice {

	//使用第三方jar包jersey-client-1.0.3.1.jar、jersey-core-1.0.3.1.jar实现与 REST 服务通讯。
	private void test1(){
		Client c = Client.create();
		WebResource r = c
				.resource("http://localhost:8080/RestWebService/service/helloWorld/getMongoDBAccount");
		ClientResponse response = r.get(ClientResponse.class);
		System.out.println(response.getStatus());
		System.out.println(response.getHeaders().get("Content-Type"));
		String entity = response.getEntity(String.class);
		System.out.println(entity);
	}
	
	//使用第三方jar包commons-httpclient-3.1.jar实现与 WebService 服务通讯。
	private static void test2(){
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		// 创建GET方法的实例
		GetMethod getMethod = new GetMethod("http://192.168.213.181/mc2_zngf_test/DictionaryService.svc/Dictionary/Json");
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}
			// 读取内容
			byte[] responseBody = getMethod.getResponseBody();
			// 处理内容
			System.out.println(new String(responseBody));
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://localhost:8080/CxfWebService/service/restWS/rest";
		Form form = new Form();
		List<String> list1 = new ArrayList<String>();
		list1.add("liuxiaoling");
		List<String> list2 = new ArrayList<String>();
		list2.add("123");
		form.put("userName", list1);
		form.put("password", list2);
		
		/**生成客户端*/
		Client client = Client.create();
		/**调用工作流地址 */
		WebResource resource = client.resource(url);
		try {
			ClientResponse response = resource
					.type(MediaType.APPLICATION_FORM_URLENCODED)
					.accept(MediaType.APPLICATION_JSON_TYPE)
					.post(ClientResponse.class, form);

			if (response.getStatus() == 200) {// 调用成功
				String str = response.getEntity(String.class);// 获得返回参数
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
