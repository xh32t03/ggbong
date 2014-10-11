package com.cxf.rest.webservice;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.cxf.rest.base.EtripUtil;
import com.cxf.rest.base.JSONRequest;
import com.cxf.rest.base.JSONResponse;

/**
 * http://localhost:8080/CxfWebService/service?wsdl
 * http://localhost:8080/CxfWebService/service/restful?_wadl
 * http://localhost:8080/CxfWebService/service/restWS?_wadl
 * 
 * http://localhost:8080/CxfWebService/service/restWS/rest?data={"head":{"op":"","session_id":"","method_id":"1"},"param":{"startStation":"上海虹桥","arriveStation":"苏州"}}
 * http://localhost:8080/CxfWebService/service/restWS/rest?userName=1&password=1
 *
 * @author liuxl
 */
@Path("/rest")
public class RestWebService {

	private final static Logger logger = LoggerFactory.getLogger(RestWebService.class);
	private final static Log log = LogFactory.getLog(RestWebService.class);
	private final static String DEFAULT_UTF8_FORMAT = "UTF-8";
	
	@Context
    private UriInfo uriInfo;
    
    @Context
    private Request request;
    
	private MessageContext messageContext;
	
	/**
	 * 存储发送的数据
	 * 
	 * @param messageContext
	 */
	@Context
	public void setMessageContext(MessageContext messageContext) {
		this.messageContext = messageContext;
	}

	@GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/request/{param}")
    public String doRequest(@PathParam("param") String param, 
            @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse) {
        System.out.println(servletRequest);
        System.out.println(servletResponse);
        System.out.println(servletRequest.getParameter("param"));
        System.out.println(servletRequest.getContentType());
        System.out.println(servletResponse.getCharacterEncoding());
        System.out.println(servletResponse.getContentType());
        return "success";
    }
	
	/**
	 * GET METHOD的处理方法
	 * 
	 * @return
	 */
	@GET
	//@Produces("application/json;charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMethod() {
		HttpServletRequest request = messageContext.getHttpServletRequest();
		HttpServletResponse response = messageContext.getHttpServletResponse();
		String encoding = request.getCharacterEncoding();
		System.out.println("encoding="+encoding);
		try {
			request.setCharacterEncoding(DEFAULT_UTF8_FORMAT);
			response.setCharacterEncoding(DEFAULT_UTF8_FORMAT);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		//获取请求参数
		String requestJson = request.getParameter("data");
		System.out.println("before requestJson："+requestJson);
		if (null != requestJson && requestJson.trim().length() > 0) {
			try {
				/**
				 * 大多浏览器会把中文直接换成:ISO-8859-1,如果在你的系统里确实需要用户在地址打入中文直接进行查询,
				 * 建议把URIEncoding改成ISO-8859-1就可以了.但这样以后会造成其它用UTF-8编码的地方就要单独转码了,
				 * 反而不方便(JSON数据的提交);所以我的建议是不要让用户在地址栏打入中文进行操作;
				 */
				requestJson = new String(requestJson.getBytes("ISO-8859-1"),"UTF-8");
				requestJson = java.net.URLDecoder.decode(requestJson,DEFAULT_UTF8_FORMAT);
				System.out.println("after requestJson："+requestJson);
				JSONObject obj = JSONObject.parseObject(requestJson);
				JSONObject head = (JSONObject)obj.get("head");
				JSONObject param = (JSONObject)obj.get("param");
				
				String method_id = head.get("method_id").toString();
				//站站查询
				if("1".equals(method_id)){
					String startStation = (String)param.get("startStation");
					String lastStation = (String)param.get("arriveStation");
					System.err.println(startStation+" -- "+lastStation);
					Map<String, Map<String, Object>> map = EtripUtil.queryStationByStation(startStation,lastStation);
					logger.info("responseJson："+com.alibaba.fastjson.JSONObject.toJSONString(map));
					
					return com.alibaba.fastjson.JSONObject.toJSONString(map);
				}
				//车次查询
				else if("2".equals(method_id)){
					String trainCode = (String)param.get("trainCode");
					String responseJson = EtripUtil.queryByTrainCode(trainCode);
					logger.info(responseJson);
					return responseJson;
				}
				//车站查询
				else if("3".equals(method_id)){
					String station = (String)param.get("station");
					String responseJson = EtripUtil.queryByStation(station);
					logger.info(responseJson);
					return responseJson;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return postMethod(null);
	}

	/**
	 * POST METHOD的处理方法
	 * 
	 * @param formParams
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String postMethod(MultivaluedMap<String, Object> formParams) {
		Map<String,Object> paramMap=null;
		if(formParams !=null){
			this.messageContext.getHttpServletResponse().setCharacterEncoding(DEFAULT_UTF8_FORMAT);
			paramMap=(Map)formParams;
		}else{
			try {
				this.messageContext.getHttpServletRequest().setCharacterEncoding(DEFAULT_UTF8_FORMAT);
				this.messageContext.getHttpServletResponse().setCharacterEncoding(DEFAULT_UTF8_FORMAT);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			paramMap=this.messageContext.getHttpServletRequest().getParameterMap();
		}
		
		// 获取请求参数
		Object methodObject = paramMap.get("userName");
		Object paramObject = paramMap.get("password");
		return handler(methodObject+"", paramObject+"");
	}

	public String handler(String userName, String password) {
		if ("admin".equals(userName) && "admin".equals(password)) {
			return "WelCome admin to Titanium !";
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("as_co", "1_BJBJNZT001");
			map.put("as_na", "站台一");
			map.put("as_ta", "区域");
			map.put("as_ti", "1");
			map.put("st_co", "BJBJN");
			
			log.info("返回数据："+JSONObject.toJSONString(map));
			return JSONObject.toJSONString(map);
		}
	}
}