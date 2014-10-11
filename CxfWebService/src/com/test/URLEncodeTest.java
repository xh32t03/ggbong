package com.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEncodeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="http://192.168.60.139:8080/rest_test/service/mash5Service/rest?api.key=3abcd569ef4330&method.name=getBusinessForm啊";
		url = "http://localhost:8080/CxfWebService/service/restWS/rest?data={'head':{'op':'','session_id':'','method_id':'1'},'param':{'startStation':'上海','arriveStation':'苏州'}}";
		try {
			String enUrl=URLEncoder.encode(url, "UTF-8");
			System.out.println("enUrl="+enUrl);
			String deUrl=URLDecoder.decode(enUrl+"&insert.content=新建数据", "UTF-8");
			System.out.println("deUrl="+deUrl);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
