package com.mash5.dbprovider;

import org.json.JSONException;
import org.json.JSONObject;

public class BusinessProvider {

	public static JSONObject getBusinessTemplate(int id) {
		String templateStr = "{presentation: [" +
				"{id: 1, widget: 'com.mash5.drive.Author', layout:[{anchor: 0, verb: 'ALIGN_PARENT_LEFT'}, {anchor: 0, verb: 'ALIGN_PARENT_TOP'}], data: {}} , " +
				"{id: 2, widget: 'com.mash5.drive.MicroBlog', layout: [{anchor: 1, verb: 'RIGHT_OF'}, {anchor: 0, verb: 'ALIGN_PARENT_TOP'}], data: {}}" +
				"]}";
		JSONObject template = null;
		try {
			template = new JSONObject(templateStr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return template;
	}
	
	public static JSONObject getBusinessForm(int id) {
		String formStr = "{name: 'bug提交', description: '这是bug提交的form', fields: [" +
				"{label: 'BUG描述', name: 'desc', pos: 1, type: 'Text'}," +
				"{label: '问题类型', name: 'type', pos:2, type: 'Radio', choices: ['Bug', 'Poor user experience', 'Feature enhancement']}, " +
				"{label: '委派人', name: 'appoint', pos:3, type: 'Select', choices: ['王斌', '徐珍', '建安', '彦鵬']}, " +
				"{label: '状态', name: 'status', pos: 4, type: 'Radio', choices: ['Open', 'Closed']}" +
				"]}";
		JSONObject form = null;
		try {
			form = new JSONObject(formStr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return form;
	}
	
	public static JSONObject getBusiness(int id) {
		String boStr = "{id:1}";
		JSONObject bo = null;
		try {
			bo = new JSONObject(boStr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return bo;

	}
	
	//
	public static JSONObject getFeedOfAll() {
		JSONObject jsObj = null;
		String json = "{object:'这是全部的搜索结果'}";
		
		try {
			jsObj = new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsObj;
	}	
	
	public static JSONObject getFeedOfDep() {
		JSONObject jsObj = null;
		String json = "{object:'这是搜索部门的结果'}";
		
		try {
			jsObj = new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsObj;
	}	
	
	public static JSONObject getFeedOfTeam() {
		JSONObject jsObj = null;
		String json = "{object:'这是搜索团队的结果'}";
		
		try {
			jsObj = new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsObj;
	}	
	
	public static JSONObject getFeedOfBiz() {
		JSONObject jsObj = null;
		String json = "{object:'这是搜索业务的结果'}";
		
		try {
			jsObj = new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsObj;
	}
	
	public static JSONObject getFeedOfUser() {
		JSONObject jsObj = null;
		String json = "{object:'这是搜索人员的结果'}";
		
		try {
			jsObj = new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsObj;
	}
	
}
