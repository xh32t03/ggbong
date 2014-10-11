package com.cxf.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;  

/**
 * JSON 即 JavaScript Object Natation，它是一种轻量级的数据交换格式，非常适合于服务器与JavaScript的交互。
 * Json必需的包：
 	commons-httpclient-3.1.jar
	commons-lang-2.4.jar
	commons-logging-1.1.1.jar
	json-lib-2.2.3-jdk13.jar
	ezmorph-1.0.6.jar
	commons-collections-3.2.1.jar

	包下载路径：
	http://commons.apache.org/index.html
	http://json-lib.sourceforge.net/
	http://ezmorph.sourceforge.net/
	http://morph.sourceforge.net/
	http://www.docjar.com/
 *  
 */  
public class JsonUtil {  
  
	/**
	 * Converts a JSON string representing an array to an array of Java Beans of
	 * a given Class.
	 */
	public static Object[] fromJSONArray(String json, Class clazz, Map<String, Class> classMap) {
		JSONArray ja = JSONArray.fromObject(json);
		return (Object[]) JSONArray.toArray(ja, clazz, classMap);
	}

	/**
	 * Converts a JSON string representing an array to a java.util.List that
	 * contains Java Beans of a given Class.
	 */
	public static List fromJSONList(String json, Class clazz, Map<String, Class> classMap) {
		JSONArray ja = JSONArray.fromObject(json);
		return JSONArray.toList(ja, clazz, classMap);
	}

	/**
	 * Converts a JSON string representing an object to a Java Bean object of a
	 * given Class.
	 */
	public static Object fromJSONObject(String json, Class clazz, Map<String, Class> classMap) {
		JSONObject jo = JSONObject.fromObject(json);
		return JSONObject.toBean(jo, clazz, classMap);
	}

	/**
	 * Pretty prints a JSONArray or JSONObject.
	 */
	private static void prettyPrint(JSON j) {
		System.out.print(j.toString(2));
	}

	/**
	 * Pretty prints a JSON string that represents an array.
	 */
	public static void prettyPrintArray(String json) {
		prettyPrint(JSONArray.fromObject(json));
	}

	/**
	 * Pretty prints a JSON string that represents an object.
	 */
	public static void prettyPrintObject(String json) {
		prettyPrint(JSONObject.fromObject(json));
	}

	/**
	 * Converts a Java Bean to a JSON string.
	 */
	public static String toJSON(Object object) {
		JSONObject jo = JSONObject.fromObject(object);
		return jo.toString();
	}
	
	/**
	 * Converts a java.util.List to a JSON string.
	 */
	public static String toJSON(List list) {
		JSONArray ja = JSONArray.fromObject(list);
		return ja.toString();
	}
	
	/**
	 * Converts a java.util.Set to a JSON string.
	 */
	public static String toJSON(Set<String> set) {
		JSONArray ja = JSONArray.fromObject(set);
		return ja.toString();
	}
	
	/**
	 * Converts a java.util.Map to a JSON string.
	 */
	public static String toJSON(Map map) {
		JSONObject ja = JSONObject.fromObject(map);
		return ja.toString();
	}
	
	/**
	 * Converts an array of booleans, numbers, strings and Java Beans to a JSON
	 * string.
	 */
	public static String toJSON(Object[] array) {
		JSONArray ja = JSONArray.fromObject(array);
		return ja.toString();
	}

	 /**
	  * Generate a valid JSON string from the given <code>str</code>.
	  *
	  * @param str A String
	  * @return JSON string surrounded by double quotes.
	  * @see <a href="http://tools.ietf.org/html/rfc4627">RFC 4627</a>
	  */
	public static String getJsonString(String str) {
		if (str == null || str.length() == 0) {
			return "\"\"";
		}

		int len = str.length();
		StringBuffer sb = new StringBuffer(len + 2);
		// leading quote
		sb.append('"');
		// append passed string escaping characters as required
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			switch (c) {
				// reverse solidus and double quote
				case '\\':
				case '"':
					sb.append('\\').append(c);
					break;
				// tab, line breaking chars and backspace
				case '\b':
					sb.append("\\b");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\r':
					sb.append("\\r");
					break;
				case '\t':
					sb.append("\\t");
					break;
				// other control characters and 'unescaped'
				default:
					if (c < 32) {
						// control characters except those already covered
						// above.
						String uc = Integer.toHexString(c);
						sb.append("\\u");
						int uLen = uc.length();
						while (uLen++ < 4) {
							sb.append('0');
						}
						sb.append(uc);
					} else {
						// unescaped = %x20-21 / %x23-5B / %x5D-10FFFF
						sb.append(c);
					}
			}
		}
		// trailing quote
		sb.append('"');
		return sb.toString();
	}  
  
	/**
	 * json字符串Map转换，如{a:'',b:'',c:''}
	 * @param str
	 * @return
	 */
	public static Map<String, Object> json2Map(String str) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(null==str || str.trim().length()<=0) return map;
		JSONObject json = JSONObject.fromObject(str);
		Iterator<String> keys = json.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value = json.get(key).toString();
			if (value.startsWith("{") && value.endsWith("}")) {
				map.put(key, json2Map(value));
			} else {
				map.put(key, value);
			}
		}
		return map;
	}
	/**
	 * 
	 * @param jsonArray，如[{a:'',b:'',c:''}]
	 * @return
	 */
	public static List<String> jsonArray2List(String jsonArray){
		JSONArray obj = JSONArray.fromObject(jsonArray);
		List<String> list = new ArrayList<String>();
		for(Object o : obj){
			System.out.println(o);
			list.add(o.toString());
		}
		return list;
	}
	/**
	 * 把请求返回的信息具有键值对的数组转换成List，如[{a:'',b:'',c:''}，{a1:'',b1:'',c1:''}，{a2:'',b2:'',c2:''}]
	 * @param jsonArray
	 * @return
	 */
	public static List<Map<String, Object>> produceJSONArray2List(String jsonArray){
		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
		JSONArray obj = JSONArray.fromObject(jsonArray);
		try {
			for (Object o : obj) {
				JSONObject dataObj = (JSONObject) o;
				String str = dataObj.toString();
				Map<String, Object> map = json2Map(str);
				lstMap.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return lstMap;
	}
}  
