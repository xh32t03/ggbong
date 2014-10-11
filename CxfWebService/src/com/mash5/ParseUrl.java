/**
 * 
 */
package com.mash5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author so
 *
 */
public class ParseUrl {
	/**
	 * @param args
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void parseURL(String url){
		
		Map apiList = new HashMap();
	    Map queryList = new HashMap();
	    Map selectList = new HashMap();
	    Map updateList = new HashMap();
	    Map insertList = new HashMap();		    	    
	    	    	    
	    String method = "";
	    String userId = "";
		//String url2 = "http://localhost:8080/service/rest/?api.ds=so9001&method.name=saveUser&query.key1=so&query.key2=soso$selected.name=1&selected.age=1";
		String[] urlArray = url.split("&");
		List urlList = Arrays.asList(urlArray);
		if(urlList != null && urlList.size() > 0){
			try{
				String _url1 = (String)urlList.get(0);//处理第一个条件	
				if(_url1.indexOf("?") != -1 && _url1.indexOf("?") != _url1.length()-1){//当存在？号且不在最后位时	
					String _api = _url1.substring(_url1.indexOf("?")+1,_url1.length()); 		
					
					urlList.set(0, _api);
				}else{
					urlList.remove(0);		
				}
				
				for(int i = 0; i < urlList.size(); i++){
			    	String urlString = urlList.get(i).toString();
			       if(urlString.indexOf("method.") != -1){//方法名
			    	   method = urlString.split("=")[1];		    	  
			       }else if(urlString.indexOf("query.") != -1){		    	   
			    	   String [] query = urlString.split("=");
			    	   queryList.put(query[0].replace("query.", ""), query[1]);
			       }else if(urlString.indexOf("update.") != -1){
			    	   String [] update = urlString.split("=");
			    	   updateList.put(update[0].replace("update.", ""), update[1]);
			       }else if(urlString.indexOf("insert.") != -1){
			    	   String [] insert = urlString.split("=");
			    	   insertList.put(insert[0].replace("insert.", ""), insert[1]);
			       }else if(urlString.indexOf("api.") != -1){
			    	   String [] api = urlString.split("=");
			    	   apiList.put(api[0].replace("api.", ""), api[1]);
			       }else if(urlString.indexOf("select.") != -1){
			    	   String [] select = urlString.split("=");
			    	   selectList.put(select[0].replace("select.", ""), select[1]);
			       }else if(urlString.indexOf("user.") != -1){
			    	   userId = urlString.split("=")[1];
			       }
			    }
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
	    Map jsonMap = new HashMap();
	    jsonMap.put("query", queryList);
	    jsonMap.put("update", updateList);
	    jsonMap.put("insert", insertList);
	    jsonMap.put("api", apiList);
	    jsonMap.put("method", method);
	    jsonMap.put("userId", userId);
	    
	    //System.out.println(JSONConverter.objectToJSON(jsonMap));	   
	}	
	
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public static Map parseMapUrl(Map urlMap){
		Map apiList = new HashMap();
	    Map queryList = new HashMap();
	    Map selectList = new HashMap();
	    Map updateList = new HashMap();
	    Map insertList = new HashMap();	
	    String method = "";
	    String userId = "";
	    Iterator iter = urlMap.keySet().iterator();
	    while(iter.hasNext()){
	    	String _key = (String)iter.next();
	    	Object[] _objArray = (Object[])urlMap.get(_key);
	    	//instanceof 预留判断图片二进制数组
	    	
	    	try{
	    	   String newKey = _key.substring(_key.lastIndexOf(".")+1,_key.length());
	    	   if(_key.indexOf("method.") != -1){//方法名
	 		       method = _objArray[0].toString();
	 	       }else if(_key.indexOf("query.") != -1){	 	    	   
	 	    	   queryList.put(newKey, _objArray.length > 0?_objArray:_objArray[0]);
	 	       }else if(_key.indexOf("update.") != -1){	 	    	   
	 	    	   updateList.put(newKey, _objArray.length > 0?_objArray:_objArray[0]);		    	   	    	 
	 	       }else if(_key.indexOf("insert.") != -1){	 	    	   
	 	    	   insertList.put(newKey, _objArray.length > 0?_objArray:_objArray[0]);
	 	       }else if(_key.indexOf("api.") != -1){	 	    	  
	 	    	   apiList.put(newKey, _objArray.length > 0?_objArray:_objArray[0]);
	 	       }else if(_key.indexOf("select.") != -1){	 	    	   
	 	    	   selectList.put(newKey, _objArray.length > 0?_objArray:_objArray[0]);	    	   
	 	       }else if(_key.indexOf("user.") != -1){
	 	    	   userId = _objArray[0].toString();
	 	       }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}	       
	    }
	    Map jsonMap = new HashMap();
	    jsonMap.put("query", queryList);
	    jsonMap.put("update", updateList);
	    jsonMap.put("insert", insertList);
	    jsonMap.put("api", apiList);
	    jsonMap.put("method", method);
	    jsonMap.put("userId", userId);
	    
	    //System.out.println(JSONConverter.objectToJSON(jsonMap));
	    return jsonMap;
	}
}
