package com.cxf.webservice;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * 客户端开发
 * 
 * @author liuxl
 * 
 */
public class Client {
	
	/**
	 * 对应服务testWebService01
	 */
	private static void testWebServiceClient01(){
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress("http://localhost:8080/CxfWebService/service/helloWorld");
		factory.setServiceClass(IHelloWorld.class);
//		factory.setServiceClass(HelloWorldImpl.class);
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		IHelloWorld client = (IHelloWorld) factory.create();
		String reply = client.sayHello("HanDou");
		System.out.println("Server said: " + reply);
		
		List<User> lstUser = client.getlstUser();
		System.out.println("lstUser: " + JSONArray.fromObject(lstUser));
		Map<String,Object> map = client.getMapVK();
		System.out.println("MapOutput: " + JSONObject.fromObject(map));
		
		System.exit(0);
	}
	/**
	 * 调用webservice testWebService02
	 */
	private static void testWebServiceClient02(){
		//测试： run webServiceApp.java 类来启动服务，然后 run HelloWorldClient.java 来访问服务。
		JaxWsProxyFactoryBean svr = new JaxWsProxyFactoryBean();
        svr.setServiceClass(IHelloWorld.class);
        svr.setAddress("http://localhost:8080/RestWebService/service/helloWorld");
        IHelloWorld hw = (IHelloWorld) svr.create();
        User user = new User();
        user.setUsername("Tony");
        user.setDescription("test");
        System.out.println(hw.sayHiToUser(user));
        
        
//        HelloWorld client = (HelloWorld)context.getBean("client");
//        User user1 = new User();
//        user1.setName("Tony");
//        user1.setDescription("test");
//        User user2 = new User();
//        user2.setName("freeman");
//        user2.setDescription("test");
//        List<User> userList= new ArrayList<User>();
//        userList.add(user1);
//        userList.add(user2);
//        String[] res = client.SayHiToUserList(userList);
//        System.out.println(res[0]);
//        System.out.println(res[1]);
	}
	
//	private static void testWeatherWebService() {
//		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//		factory.getInInterceptors().add(new LoggingInInterceptor());
//		factory.getOutInterceptors().add(new LoggingOutInterceptor());
//		factory.setAddress("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx");
//		factory.setServiceClass(WeatherWebServiceSoap.class);
//		WeatherWebServiceSoap client =
//		(WeatherWebServiceSoap) factory.create();
//		ArrayOfString o = client.getWeatherbyCityName("南阳");
//		List<String> strList = o.getString();
//		for (String str : strList) {
//			System.out.println(str);
//		}
//	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		try {
//			/**
//			 * init Spring context object
//			 */
//			ApplicationContext context = new ClassPathXmlApplicationContext(
//	                new String[]{"classpath:/applicationContext*.xml"});
//			HelloWorld clent=(HelloWorld)context.getBean("client");
//			System.out.println(clent.sayHello("胡晓亮"));
//			System.out.println(clent.sayBye("胡晓亮"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		testWebServiceClient01();
		
//		Client c = Client.create();
//		WebResource r = c
//				.resource("http://localhost:8080/RestWebService/service/helloWorld/getMongoDBAccount");
//		ClientResponse response = r.get(ClientResponse.class);
//		System.out.println(response.getStatus());
//		System.out.println(response.getHeaders().get("Content-Type"));
//		String entity = response.getEntity(String.class);
//		System.out.println(entity);
		
		/**
		 * 创建WebService代理
		 */
//		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//		factory.setServiceClass(IHelloWorld.class);
//		factory.setAddress("http://localhost:8080/helloWorld");//http://localhost:9999/hello
//
//		IHelloWorld service = (IHelloWorld) factory.create();
//		System.out.println(service.sayHello("jack"));
		
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		org.apache.cxf.endpoint.Client client = dcf
				.createClient("http://localhost:8080/helloWorld?wsdl");
		try {
			Object[] objects = client.invoke("sayHello", "张三");
			for(Object o : objects){
				System.out.println(o.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
