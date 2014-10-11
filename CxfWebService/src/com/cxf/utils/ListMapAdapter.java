package com.cxf.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author joly
 * @version 创建时间：2012-7-1 下午11:25:11
 * @DO Map适配器
 * 		在CXF服务端的接口程序中进行如下定义@WebMethod
 *        @XmlJavaTypeAdapter(ListMapAdapter.class)
 */
public class ListMapAdapter extends XmlAdapter<List<MapConvertor>, List<Map<String, Object>>> {

	/*marshal表示要将List<Map<String, Object>>类型编组为List<MapConvertor>类型*/
	@Override
	public List<MapConvertor> marshal(List<Map<String, Object>> lstMaps) throws Exception {
		List<MapConvertor> lstMapConvertor = new ArrayList<MapConvertor>();
		MapConvertor convertor = new MapConvertor();
		for(Map<String, Object> map : lstMaps){
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				MapConvertor.MapEntry e = new MapConvertor.MapEntry(entry);
				convertor.addEntry(e);
			}
			lstMapConvertor.add(convertor);
		}
		
		return lstMapConvertor;
	}

	/*marshal表示要将List<MapConvertor>类型解组为具体的类List<Map<String, Object>>*/
	@Override
	public List<Map<String, Object>> unmarshal(List<MapConvertor> lstMapConvertor) throws Exception {
		List<Map<String, Object>> lstMaps = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		for(MapConvertor entry : lstMapConvertor){
			for (MapConvertor.MapEntry e : entry.getEntries()) {
				map.put(e.getKey(), e.getValue());
			}
			lstMaps.add(map);
		}
		
		return lstMaps;
	}
}
