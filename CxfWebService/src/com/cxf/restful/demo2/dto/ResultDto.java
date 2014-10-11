package com.cxf.restful.demo2.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlAccessorType(XmlAccessType.PROPERTY)
//java object 转换成 xml 的时候，name 不是属性（因为没有 get set方法），所以name不转换成标签。
public class ResultDto {
	public Integer totalCount;//总记录数
	public Integer totalPage;//总页数
	public List<StudentDto> students;//学生列表
	//@XmlElement
	public String returncode;//反回状态码
}
