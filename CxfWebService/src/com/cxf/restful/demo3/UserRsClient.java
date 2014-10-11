package com.cxf.restful.demo3;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 使用URLConnection调用RESTful的服务 此外建议使用httpClient读取，将会更快 使用urlConnection可能没有返回结果
 * 
 * @author
 * @version
 */
public class UserRsClient {
	UserRsClient() throws Exception {
		save2();
		all();
	}

	/**
	 * 查询所有信息
	 * 
	 * @throws Exception
	 */
	private void all() throws Exception {
		HttpClient hc = new HttpClient();//创建一个客户端，类似打开一个浏览器 
		GetMethod get = new GetMethod("http://localhost:9000/users/all");//创建一个get方法，类似在浏览器地址栏中输入一个地址 
		//设置请求头、设置编码
		get.setRequestHeader("accept", "application/json");
		hc.getParams().setContentCharset("UTF-8");
		int statusCode = hc.executeMethod(get);
		System.err.println("返回的状态码：" + statusCode);
		if (statusCode == 200) {
			String str = get.getResponseBodyAsString();
			System.err.println("返回信息：\n" + str);
		}
		get.releaseConnection();
	}

	/**
	 * 保存一条信息,仍然使用GET方式
	 */
	private void save() throws Exception {
		String name = "Jack";// 因为是get类型，所以不能包含中文
		String age = "35";
		String url = "http://localhost:9000/users/save/" + name + "/" + age;
		GetMethod get = new GetMethod(url);
		get.setRequestHeader("accept", "application/json");
		HttpClient hc = new HttpClient();
		hc.getParams().setContentCharset("UTF-8"); // 设置编码
		// .setRequestHeader("Content","text/html;charset=UTF-8");
		int code = hc.executeMethod(get);
		System.err.println("返回的状态码是：" + code);
		if (code == 200) {
			String str = get.getResponseBodyAsString();
			System.err.println("返回的信息是:\n" + str);
		}
		get.releaseConnection();
	}

	/**
	 * 以下使用POST方式
	 */
	private void save2() throws Exception {
		String name = "张三";
		String age = "35";
		String url = "http://localhost:9000/users/add/";
		PostMethod pm = new PostMethod(url);
		pm.setRequestHeader("accept", "application/json");
		pm.setRequestHeader("Encoding", "UTF-8");
		pm.setParameter("name", name);
		pm.setParameter("age", age);
		HttpClient hc = new HttpClient();
		hc.getParams().setContentCharset("UTF-8");// 设置编码，否则会返回中文乱码
		int code = hc.executeMethod(pm);
		System.err.println("Post 方式的返回值是:" + code);
		if (code == 200) {
			String ss = pm.getResponseBodyAsString();
			System.err.println(">>:" + ss);
		}
		pm.releaseConnection();
	}

	public static void main(String[] args) throws Exception {
		//使用HttpClient调用RESTful的web服务
		new UserRsClient();
	}
}
