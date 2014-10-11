package com.cxf.restful.demo2;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

import com.cxf.restful.demo2.service.impl.StudentServiceImpl;

public class RestfulServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        JAXRSServerFactoryBean bean = new JAXRSServerFactoryBean();// 声明JAXRS服务对象
		bean.setServiceBean(new StudentServiceImpl());// 加载服务类
		bean.setAddress("http://localhost:9000/service");// 声明地址，注意只声明地址和端口即可
		bean.getInInterceptors().add(new LoggingInInterceptor());
		bean.getOutInterceptors().add(new LoggingOutInterceptor());
		bean.create();
		System.err.println("JAX-RS 启动成功....");
	}

}
