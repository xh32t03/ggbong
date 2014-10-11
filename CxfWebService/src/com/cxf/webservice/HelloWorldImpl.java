package com.cxf.webservice;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import net.sf.json.JSONObject;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

/**
 * 使用CXF发布一个服务
 * 必须要添加WebService注解。否则不会对外暴露任何一个方法
 * @WebService 注解表示是要发布的web服务
 *		name：用于Interface，属映射到wsdl:portType element的name属性。 
 *		targetNamespace：用于Interface和implement，如果不指定，缺省会使用包名倒序做为wsdl名空间。
 *		serviceName：用于implement，表示wsdl服务名。
 *		portName：用于implement，表示wsdl:port 的name属性。
 *		endpointInterface：用于implement，指定Interface全名，包括包名。
 *		
 * 
 */
@WebService(endpointInterface="com.cxf.webservice.IHelloWorld",serviceName="helloWorld")
@SOAPBinding(style = Style.RPC)
public class HelloWorldImpl implements IHelloWorld {
//	@Override
//	public String sayHello(@WebParam(name = "name") String name) {
//		return name + " say: Hello World!";
//	}
	
	Map<Integer, User> users = new LinkedHashMap<Integer, User>();

	@Override
	public String sayHello(String name) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "a1");
		map.put("b", "b1");
		map.put("c", "c1");
		
		return JSONObject.fromObject(map).toString();
		//return name + " say: Hello World!";
	}

	@Override
	public String sayBye(String name) {
		// TODO Auto-generated method stub
		System.out.println(222);
		return "Good Bye,"+name;
	}
	
	public String sayHi(String text) {
		return "Hello " + text;
	}

	public String sayHiToUser(User user) {
		users.put(users.size() + 1, user);
		return "Hello " + user.getUsername();
	}

	public String[] sayHiToUserList(List<User> userList) {
		String[] result = new String[userList.size()];
		int i = 0;
		for (User u : userList) {
			result[i] = "Hello " + u.getUsername();
			i++;
		}
		return result;
	}

	@Override
	public String getMongoDBAccount(){
		try {
			Mongo mg = new Mongo();
			DB db = mg.getDB("test");
			DBCollection c = db.getCollection("Account");
			DBCursor cur = c.find();
			System.out.println(JSON.serialize(cur));
			return JSON.serialize(cur);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getlstObject() {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		list.add("上海");
		list.add("北京");
		list.add("深圳");
		list.add("杭州");
		list.add("南京");
		return list;
	}

	@Override
	public Map<String, Object> getMapVK() {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Shanghai", "上海");
		map.put("Beijing", "北京");
		map.put("Shenzhen", "深圳");
		map.put("Hangzhou", "杭州");
		map.put("Nanjing", "南京");
		return map;
	}

//	@Override
//	public List<Map<String, Object>> getlstMapVK() {
//		// TODO Auto-generated method stub
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("Shanghai", "上海");
//		map.put("Beijing", "北京");
//		map.put("Shenzhen", "深圳");
//		map.put("Hangzhou", "杭州");
//		map.put("Nanjing", "南京");
//		
//		list.add(map);
//		return list;
//	}

	@Override
	public List<User> getlstUser() {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		User u = new User();
		u.setUsername("liuxiaoling");
		u.setPassword("23");
		u.setDescription("this is testing..");
		list.add(u);
		
		return list;
	}
	
}
