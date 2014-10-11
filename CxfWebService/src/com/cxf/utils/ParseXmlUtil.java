package com.cxf.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;


public class ParseXmlUtil {

	/**
	 * 解析调用Webservice后返回的XML文本信息，截取return字符串
	 * 
	 * 		<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
				<soap:Body>
					<ns2:sayHelloResponse xmlns:ns2="http://service.cxf.com/">
						<return>XXX say: Hello World!</return> 
					</ns2:sayHelloResponse>
				</soap:Body>
			</soap:Envelope>
	 * 
	 * @param url
	 * @return
	 */
	public String parseSOAPXML(String url){
		SAXReader reader = new SAXReader();
		Document doc = null;//doc=DocumentHelper.parseText(text);
		try {
			doc = reader.read(url);
			ParseXmlVisitor myVisitor = new ParseXmlVisitor();
			doc.accept(myVisitor);
			
			return myVisitor.returnJSONData();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
