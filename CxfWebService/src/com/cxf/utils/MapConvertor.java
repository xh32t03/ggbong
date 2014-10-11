package com.cxf.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author joly
 * @version 创建时间：2012-7-1 下午11:21:49
 * @DO Map转换器
 * CXF中不支持Map
 *     解决方案：
 *		通过适配器将数组转换成HashMap的方式支持。
 */
@XmlType(name = "MapConvertor")
@XmlAccessorType(XmlAccessType.FIELD)
public class MapConvertor {
	private List<MapEntry> map = new ArrayList<MapEntry>();

	public void addEntry(MapEntry entry) {
		map.add(entry);
	}

	public static class MapEntry {
		public MapEntry() {
			super();
		}

		public MapEntry(Map.Entry<String, Object> entry) {
			super();
			this.key = entry.getKey();
			this.value = entry.getValue();
		}

		public MapEntry(String key, Object value) {
			super();
			this.key = key;
			this.value = value;
		}

		private String key;
		private Object value;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}
	}

	public List<MapEntry> getEntries() {
		return map;
	}
	
}
