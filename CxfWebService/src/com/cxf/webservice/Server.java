package com.cxf.webservice;

import javax.xml.ws.Endpoint;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * CxfWSWebservice服务端
 * 
 * @author joly
 * 
 */
public class Server {
	
	public static void deployService() {
		System.out.println("service start ...");
		String address = "http://localhost:8080/helloWorld";
		HelloWorldImpl service = new HelloWorldImpl();
		/**
		 * 
		 * 发布WebService
		 */
		Endpoint.publish(address, service);
		
		System.out.println("service ready ...");
	}
	
	private static void testWebService01(){
		/**
		 * 采用cxf内置的Jetty服务器发布服务。在HelloWorldImpl的实现类中添加main()方法
		 */
		//使用 jaxWs对其进行发布
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
		//svrFactory.setServiceClass(IHelloWorld.class);
		svrFactory.setAddress("http://localhost:8080/helloWorld");
		
		HelloWorldImpl implementor = new HelloWorldImpl();
		svrFactory.setServiceBean(implementor);
		svrFactory.setServiceClass(HelloWorldImpl.class);
		
		//添加请求和响应的拦截器
		svrFactory.getInInterceptors().add(new LoggingInInterceptor());
		svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		
		svrFactory.create(); //内部使用jetty服务器做为支持
		System.out.println("=========================服务已发布=========================");
	}
	
	private static void testWebService02(){
		System.out.println("web service start..");
		String address = "http://localhost:8080/RestWebService/service/helloWorld";
		IHelloWorld implementor = new HelloWorldImpl();
		Endpoint.publish(address, implementor);
		System.out.println("web service started...");
	}
	
	public static void main(String[] args) throws InterruptedException {
//		deployService();
		testWebService01();
		System.out.println("Server start ......");
		Thread.sleep(5 * 1000 * 60);
		System.exit(0);
		System.out.println("Server exit ...");
	}
}
