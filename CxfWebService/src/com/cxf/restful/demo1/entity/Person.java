package com.cxf.restful.demo1.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Person")//一定要在类的前边加上annotation ，这样才能让这个person的信息在POJO和XML之间转换。
public class Person {
	private String name;
	private String sex;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
