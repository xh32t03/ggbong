package com.mash5;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * json-object转换
 */
public class JSONConverter {
	
	private JSONConverter(){
	}
	
	/**
	 * 对象到json字符串转换
	 * @param o
	 * @return
	 */
	public static String objectToJSON(Object o){
		JSONObject json= JSONObject.fromObject(o);
		return json.toString();
	}
	
	/**
	 * json字符串到对象转换。有嵌套map用jsonToMap方法<br>
	 * 转换嵌套Map会把里头的Map转换成net.sf.ezmorph.bean.MorphDynaBean, mongodb存数据时是会出错
	 * @param jsonString
	 * @param claz
	 * @return
	 */
	public static Object jsonToObject(String jsonString,Class claz){
		JSONObject json= JSONObject.fromObject(jsonString);
		return JSONObject.toBean(json,claz);
	}
	
	/**
	 * json字符串到Map转换
	 * @param str
	 * @return
	 */
	public static Map<String,Object> jsonToMap(String str){
		Map<String,Object> map=new HashMap<String,Object>();
		JSONObject json=JSONObject.fromObject(str);
		Iterator<String> keys=json.keys();
		while(keys.hasNext()){
			String key=(String)keys.next();
			String value=json.get(key).toString();
			if(value.startsWith("{")&&value.endsWith("}")){
				map.put(key, jsonToMap(value));
			}else{
				map.put(key, value);
			}
		}
		return map;
	}


//	public static void main(String[] arr){
//		com.mash5.data.model.Session s=new com.mash5.data.model.Session();
//		s.setLoginStatus(1);
//		String jstr=JSONConverter.objectToJSON(s);
//		System.out.println(jstr);
//		com.mash5.data.model.Session se=(com.mash5.data.model.Session)JSONConverter.jsonToObject(jstr,com.mash5.data.model.Session.class);
//		
//		System.out.println("se.getLoginStatus():"+se.getLoginStatus());
//	}
	
}
