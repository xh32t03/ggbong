package com.cxf.restful.demo3;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

/**
 * 一个基于JAX-RS的服务 
 * JAX-RS是无状态的服务 
 * 注意，必须要在JavaBean上添加@XMLRootElemet注解
 * 此项目启动成功以后，可以通过以下方式访问： http://localhost:9000/users?_wadl&_type=xml
 * 注意是_wadl&_type=xml 将返回一个如何调用RESTful ws的wsdl文件说明书
 * 
 * @author
 */
@Path(value = "/users/") // 声明uri路径
@Produces(value = { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })// 声明支持的类型
public class UserServiceRS {
	private List<User> users = new ArrayList<User>();

	public UserServiceRS() {
		User u = new User();
		u.setAge(90);
		u.setName("李四");
		users.add(u);
	}

	/**
	 * 以下代码，请在地址栏这样访问： http://localhost:9000/users/all/ 即会以XML形式显示所有用户信息
	 * 
	 * @return
	 */
	@GET
	@Path(value = "/all/")
	public List<User> getUsers() {
		System.err.println(" 调用了users方法");
		return users;
	}

	/**
	 * 以下在地址栏输入： http://localhost:9000/users/save/Tom/34 其中：Tom为要保存的用户名,34为年龄
	 * 即会保存成功
	 */
	@GET
	@Path(value = "/save/{name}/{age}/")
	public User save(@PathParam("name") String name,
			@PathParam("age") String age) {
		User u = new User();
		u.setAge(Integer.parseInt(age));
		u.setName(name);
		System.err.println(" 保存成功" + u);
		users.add(u);
		return u;
	}

	/**
	 * 提供第二种保存方式 使用@FormParam方式设置接收表单的参数 通过HttpClient调用，并设置请求参数
	 */
	@POST
	@Path(value = "/add/")
	public User add(@FormParam("name") String name, @FormParam("age") String age) {
		User u = new User();
		u.setAge(Integer.parseInt(age));
		u.setName(name);
		System.err.println(" 使用POST保存成功" + u);
		users.add(u);
		return u;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JAXRSServerFactoryBean bean = new JAXRSServerFactoryBean();// 声明JAXRS服务对象
		bean.setServiceBean(new UserServiceRS());// 加载服务类
		bean.setAddress("http://localhost:9000/");// 声明地址，注意只声明地址和端口即可
		bean.getInInterceptors().add(new LoggingInInterceptor());
		bean.getOutInterceptors().add(new LoggingOutInterceptor());
		bean.create();// 启动
		System.err.println("JAX-RS 启动成功....");
	}
}
