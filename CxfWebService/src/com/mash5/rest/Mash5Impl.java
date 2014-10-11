package com.mash5.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.json.JSONObject;

import com.mash5.ParseUrl;
import com.mash5.dbprovider.BusinessProvider;
import com.mash5.dbprovider.Constants;
import com.mash5.dbprovider.DepartmentProvider;
import com.mash5.dbprovider.TeamProvider;
import com.mash5.dbprovider.UserProvider;

/**
 * 请求的URL：http://localhost:8080/CxfWebService/service/restWS/mash5?id=234u2136hfsdjhr4234&method.name=mash5.user.getUser	
 * @author liuxl
 */
public class Mash5Impl implements Mash5Interface {
	
	private final static Log log = LogFactory.getLog(Mash5Impl.class);
	private MessageContext messageContext;

	@Context
	public void setMessageContext(MessageContext messageContext) {
		this.messageContext = messageContext;
	}
	
	public String getStringPost() {
		Map<String, String[]> map = this.messageContext.getHttpServletRequest().getParameterMap();
		if(Constants.realPath==null||Constants.realPath.equals("")){
			Constants.realPath=this.messageContext.getHttpServletRequest().getScheme()+"://"+this.messageContext.getHttpServletRequest().getServerName()+":"+this.messageContext.getHttpServletRequest().getServerPort()+this.messageContext.getHttpServletRequest().getContextPath()+"/";
			log.info("realPath-->>"+Constants.realPath);
		}
		if(map!=null){
			Iterator<String> it=map.keySet().iterator();
			while(it.hasNext()){
				String key=(String)it.next();
				String[] value=map.get(key);
				System.out.println("{"+key+":"+value[0]+"}");
			}
			Map _map = ParseUrl.parseMapUrl(map);
			if(_map.get("method") != null){
				String method = _map.get("method").toString();
				return this.checkMethod(method);
			}
		}
		return "{\"error\":\"错误信息\"}";
	}
	
	public String getStringGet(){
		return getStringPost();
	}

	public Map<String, Object> getMapVK() {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("a", "a1");
		map.put("b", "b1");
		map.put("c", "c1");
		
		return map;
	}
	
	public String checkMethod(String method){
		String result = "{\"success\":false,\"message\":\"缺少方法名属性\",\"object\":null}";
		if(method.equals("getBusinessTemplate")){
			BusinessProvider bp = new BusinessProvider();
			JSONObject json = bp.getBusinessTemplate(0);
			result = json.toString();
			//result = JSONConverter.objectToJSON(json);
			//getBusinessTemplate
		}else if(method.equals("getBusinessForm")){
			BusinessProvider bp = new BusinessProvider();
			JSONObject json = bp.getBusinessForm(0);
			result = json.toString();
			//result = JSONConverter.objectToJSON(json);
		}else if(method.equals("getBusiness")){
			BusinessProvider bp = new BusinessProvider();
			JSONObject json = bp.getBusiness(0);
			result = json.toString();
			//result = JSONConverter.objectToJSON(json);
		}else if(method.equals("getFeedOfAll")){
			BusinessProvider bp = new BusinessProvider();
			JSONObject json = bp.getFeedOfAll();
			result = json.toString();
			//result = JSONConverter.objectToJSON(json);
		}else if(method.equals("getFeedOfDep")){
			BusinessProvider bp = new BusinessProvider();
			JSONObject json = bp.getFeedOfDep();
			result = json.toString();
			//result = JSONConverter.objectToJSON(json);
		}else if(method.equals("getFeedOfTeam")){
			BusinessProvider bp = new BusinessProvider();
			JSONObject json = bp.getFeedOfTeam();
			result = json.toString();
			//result = JSONConverter.objectToJSON(json);
		}else if(method.equals("getFeedOfBiz")){
			BusinessProvider bp = new BusinessProvider();
			JSONObject json = bp.getFeedOfBiz();
			result = json.toString();
			//result = JSONConverter.objectToJSON(json);
		}else if(method.equals("getFeedOfUser")){
			BusinessProvider bp = new BusinessProvider();
			JSONObject json = bp.getFeedOfUser();
			result = json.toString();
			//result = JSONConverter.objectToJSON(json);
		}
		
		else if(method.equals("getDepList")){
			DepartmentProvider dp = new DepartmentProvider();
			JSONObject json = dp.getDepList();
			result = json.toString();
		}else if(method.equals("getDepById")){
			DepartmentProvider dp = new DepartmentProvider();
			JSONObject json = dp.getDepById(3);
			//result = JSONConverter.objectToJSON(json);
			result = json.toString();
		}
		
		else if(method.equals("getAllDepartAndFeedTeam")){
			TeamProvider tp = new TeamProvider();
			JSONObject json = tp.getAllDepartAndFeedTeam();
			//result = JSONConverter.objectToJSON(json);
			result = json.toString();
		}else if(method.equals("getTeamByTeamId")){
			TeamProvider tp = new TeamProvider();
			JSONObject json = tp.getTeamByTeamId(1);
			//result = JSONConverter.objectToJSON(json);
			result = json.toString();
		}else if(method.equals("getTeamsArr")){
			TeamProvider tp = new TeamProvider();
			List<JSONObject> _list = tp.getTeamsArr(new int[]{});
			if(_list==null||_list.size()==0)return "{getTeams方法数据不正确}";
			//result = JSONConverter.objectToJSON(_list);
			result = _list.toString();
		}else if(method.equals("getTeams")){
			TeamProvider tp = new TeamProvider();
			JSONObject json = tp.getTeams();
			if(json==null)return "{getTeams方法数据不正确}";
			//result = JSONConverter.objectToJSON(_list);
			result = json.toString();
		}else if(method.equals("getAllUsers")){
			TeamProvider tp = new TeamProvider();
			JSONObject json = tp.getAllUsers();
			if(json==null)return "{getTeams方法数据不正确}";
			//result = JSONConverter.objectToJSON(_list);
			result = json.toString();
		}
		
		else if(method.equals("getUser")){
			//UserProvider up = new UserProvider();
			JSONObject json = null;
			try {
				json = UserProvider.getUser(1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//result = JSONConverter.objectToJSON(json);
			result = json.toString();
		}else if(method.equals("getUserList")){
			UserProvider up = new UserProvider();
			List<JSONObject> _list = null;
			try {
				_list = up.getUserList(new int[]{});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(_list==null||_list.size()==0)return "{getUserList方法数据不正确}";
			//result = JSONConverter.objectToJSON(_list);
			result = _list.toString();
		}else if(method.equals("getUserItems")){
			UserProvider up = new UserProvider();
			JSONObject json = up.getUserItems();
			result = json.toString();
		}else if(method.equals("getDepartment")){
			JSONObject json = DepartmentProvider.getDepartment();
			result = json.toString();
		}
		
		try {
			result=URLEncoder.encode(result, "UTF-8");
			System.out.println("URLDecoder after:"+URLDecoder.decode(result, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

}
