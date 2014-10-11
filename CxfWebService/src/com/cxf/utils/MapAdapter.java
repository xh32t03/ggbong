package com.cxf.utils;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author joly
 * @version 创建时间：2012-7-1 下午11:25:11
 * @DO Map适配器
 * 		在CXF服务端的接口程序中进行如下定义@WebMethod
 *        @XmlJavaTypeAdapter(MapAdapter.class)
 */
public class MapAdapter extends XmlAdapter<MapConvertor, Map<String, Object>> {

	/*marshal表示要将Map类型编组为MapConvertor类型*/
	@Override
	public MapConvertor marshal(Map<String, Object> map) throws Exception {
		MapConvertor convertor = new MapConvertor();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			MapConvertor.MapEntry e = new MapConvertor.MapEntry(entry);
			convertor.addEntry(e);
		}
		return convertor;
	}

	/*marshal表示要将MapConvertor类型解组为具体的类Map*/
	@Override
	public Map<String, Object> unmarshal(MapConvertor map) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		for (MapConvertor.MapEntry e : map.getEntries()) {
			result.put(e.getKey(), e.getValue());
		}
		return result;
	}
}
