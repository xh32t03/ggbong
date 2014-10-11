package com.cxf.webservice;

import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.cxf.utils.MapAdapter;

/**
 * WebService接口
 * 
 * @author joly
 * 
 */
@WebService
public interface IHelloWorld {
	
	public String sayHello(@WebParam(name="name") String name);
	public String sayBye(@WebParam(name="name")String name);
	
	String sayHi(@WebParam(name = "text") String text);
	String sayHiToUser(User user);
	String[] sayHiToUserList(List<User> userList);
	
	public String getMongoDBAccount();
	
	@XmlJavaTypeAdapter(MapAdapter.class)
	public Map<String,Object> getMapVK();
	
	public List<Object> getlstObject();
	
//	public List<Map<String,Object>> getlstMapVK();
	
	public List<User> getlstUser();
	
}
