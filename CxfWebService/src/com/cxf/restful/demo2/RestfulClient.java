package com.cxf.restful.demo2;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;

import com.alibaba.fastjson.JSONObject;
import com.cxf.restful.demo2.dto.StudentDto;
import com.cxf.restful.demo2.service.impl.StudentServiceImpl;

/**
 * JAX-RS并不提供调用RESTful服务客户端。CXF框架提供了两种方式来创建客户端，这两种都可以使用Spring配置。
 * @author liuxl
 *
 */
public class RestfulClient
{
    private static final String reqUrl = "http://localhost:9000/service";  
    private static final String TYPE_XML = "application/xml";  
    private static final String TYPE_JSON = "application/json"; 
    
    // 代理API
    // 代理API允许你使用RESTful服务的资源类和接口。代理类是客户端直接调用接口方法，使用户不需要手工创建HTTP请求。将RESTful服务类传递给org.apache.cxf.jaxrs.client.JAXRSClientFactory类。一旦代理类创建好了，你可以直接使用RESTful服务接口类的任何方法。
    private static void testRestful1(final String format)
    {
        StudentServiceImpl store = JAXRSClientFactory.create(reqUrl, StudentServiceImpl.class);
        // Makes remote call to Category RESTFul service
        store.getStudent("alex");
    }
    
    private static void testRestful2(final String format)
    {
        System.out.println("testAddCategory called with format " + format);
        WebClient client = WebClient.create(reqUrl);
        client.path("/student/getStudent2").accept(format).type(format);
        
        StudentDto rtnResponse = client.post("111", StudentDto.class);
        System.out.println("Category Id retreived for format " + format + " is " + rtnResponse.getId());
        System.out.println(JSONObject.toJSONString(rtnResponse));
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        testRestful1(TYPE_JSON);
    }

}
