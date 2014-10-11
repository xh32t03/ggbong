package com.cxf.rest.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @Title:
 * @Description:
 * @Copyright:Copyright (c) 2012
 * @Company:SHERRY-PC
 * @Date:2012-6-21
 * @author joly
 * @version 1.0
 */
public class JSONRequest {
	
	private JSONObject head;
	private JSONObject param;
	private JSONObject request;

	private final String JSON_TOKEN = ",";
	private final String STR_SEP = ":";

	public JSONObject getHead() {
		return head;
	}

	public void setHead(JSONObject head) {
		this.head = head;
	}

	public JSONObject getParam() {
		return param;
	}

	public void setParam(JSONObject param) {
		this.param = param;
	}

	public JSONObject getRequest() {
		return request;
	}

	public void setRequest(JSONObject request) {
		this.request = request;
	}

	public JSONRequest() {
		head = new JSONObject();
		param = new JSONObject();
	}

	/**
	 * 
	 * @param svcName
	 * @param sessionID
	 * @param methodId
	 */
	public JSONRequest(String svcName, String sessionID, String methodId) {
		head = new JSONObject();
		head.put("op", svcName);
		head.put("session_id", sessionID);
		head.put("method_id", methodId);
		param = new JSONObject();
	}

	public JSONRequest(String requestJson) {
		request = JSONObject.parseObject(requestJson);
		if (request != null) {
			head = request.getJSONObject("head");
			param = request.getJSONObject("param");
		}
	}

	/**
	 * 添加请求参数
	 * 
	 * @param key
	 *            参数KEY值
	 * @param value
	 *            参数值
	 */
	public void addParam(String key, Object value) {
		param.put(key, value);
	}

	/**
	 * 添加请求参数，一次添加多个参数 参数格式： "name:张三,age:23"
	 * 
	 * @param str
	 *            JSON参数
	 */
	public void addParams(String str) {
		StringTokenizer st = new StringTokenizer(str, JSON_TOKEN);
		while (st.hasMoreTokens()) {
			// json 条件
			String item = st.nextToken();
			// 分解成key,value
			String[] items = item.split(STR_SEP);
			param.put(items[0], items[1]);
		}

	}

	/**
	 * map转换JSONObject,并作为请求参数成员。 如：param:{人:[姓名：xx，年龄：30]}
	 * 
	 * @param key
	 *            JSONObject key值。
	 * @param map
	 *            成员属性值
	 */
	public void addParam(String key, Map<String, Object> map) {
		JSONObject jsonObject = (JSONObject) JSON.toJSON(map);
		param.put(key, jsonObject);
	}

	/**
	 * map转换JSONObject,并作为请求参数成员。 如：param:{人:[姓名：xx，年龄：30]}
	 * 
	 * @param key
	 *            JSONObject key值。
	 * @param map
	 *            成员属性值
	 */
	public void addParam(String key, List<Map<String, Object>> list) {
		JSONArray jsonObject = (JSONArray) JSON.toJSON(list);
		param.put(key, jsonObject);
	}

	public String toRequest() {
		request = new JSONObject();
		request.put("head", head);
		request.put("param", param);
		return request.toString();
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JSONRequest jsonRequest = new JSONRequest();
		String s = "data={'head':{'op':'','session_id':'','method_id':'33'},'param':{'startStation':'上海','arriveStation':'苏州'}}";
		System.out.println("str="+s);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "a1");
		map.put("b", "b1");
		map.put("c", "c1");
		
		JSONObject obj = (JSONObject) JSON.toJSON(map);
		System.err.println(obj.get("a")+"  "+JSONObject.toJSONString(map));
	}

}
