package com.cxf.restful.demo2.dto;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ROOT") 
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseResponse {

	private ArrayList<HashMap<String, Object>> lstMap;
	private HashMap<String, Object> data;
	
	public ArrayList<HashMap<String, Object>> getLstMap() {
		return lstMap;
	}
	public void setLstMap(ArrayList<HashMap<String, Object>> lstMap) {
		this.lstMap = lstMap;
	}
	public HashMap<String, Object> getMap() {
		return data;
	}
	public void setMap(HashMap<String, Object> map) {
		this.data = map;
	}
	
}
