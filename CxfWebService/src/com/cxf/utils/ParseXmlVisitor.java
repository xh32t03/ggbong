package com.cxf.utils;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;

public class ParseXmlVisitor extends VisitorSupport{

	private String returnJsonData;
	
	@Override  
    public void visit(Attribute node) {  
        System.out.println("属性："+node.getName()+"="+node.getText());  
    }
	
	@Override
	public void visit(Element node) {
		System.out.println("---"+node.getPath());
		System.out.println("节点: "+node.getName()+"="+node.getTextTrim());
		//对于元素节点，判断是否只包含文本内容，如是，则打印标记的名字和 元素的内容。如果不是，则只打印标记的名字
		if (node.isTextOnly()) {  
            System.out.println("Element:---" + node.getName() + "="  
                    + node.getText());
            if ("return".equals(node.getName())) {
    			System.out.println(node.getText());
    			returnJsonData = node.getText();
    		}
        }else{  
            System.out.println("--------" + node.getName() + "-------");  
        }
	}
	
	/**
	 * 解析XML获得返回数据
	 * @return
	 */
	public String returnJSONData(){
		return returnJsonData;
	}
}
