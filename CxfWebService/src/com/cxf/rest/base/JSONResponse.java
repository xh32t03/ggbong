package com.cxf.rest.base;

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
public class JSONResponse {
	/**
	 * 服务请求的失败的信息
	 */
	private String message;
	/**
	 * 返回的数据信息
	 */
	private String data;
	/**
	 * 服务操作是否成功：（1：成功， 0 ：失败 ，-1：异常） 异常最好提供异常信息
	 */
	private String flag;

	private JSONObject jsonObj;//{'flag':'0','data':{},'message':''}
	
	public final static String FAILURE = "0";
	public final static String SUCCESS = "1";
	public final static String EXCEPTION = "-1";
	
	public JSONResponse() {

	}

	public JSONResponse(String flag, String data, String message) {
		jsonObj = new JSONObject();
		this.data = data;
		this.message = message;
		this.flag = flag;
	}

	/**
	 * 获取响应的数据的结果信息
	 * 
	 * @return
	 */
	public String getJSONResponse() {
		if (jsonObj == null) {
			jsonObj = new JSONObject();
		}
		jsonObj.put("flag", flag);
		jsonObj.put("data", data);
		jsonObj.put("message", message);
		return JSONObject.toJSONString(jsonObj);
	}

	public JSONObject getJsonObject() {
		return jsonObj;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObj = jsonObject;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
