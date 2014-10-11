package com.test;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;
import org.dom4j.io.SAXReader;

//这里提供两种遍历方法，一种是基于迭代的遍历，一种是基于Visitor模式的遍历。
public class MyVisitor extends VisitorSupport {
	
	private String str;
	
	@Override  
    public void visit(Attribute node) {  
        System.out.println("属性："+node.getName()+"="+node.getText());  
    } 
	@Override
	public void visit(Element node) {
		System.out.println(node.getPath());
		System.out.println("节点: "+node.getName()+"="+node.getTextTrim());
		//对于元素节点，判断是否只包含文本内容，如是，则打印标记的名字和 元素的内容。如果不是，则只打印标记的名字
		if (node.isTextOnly()) {  
            System.out.println("Element:---" + node.getName() + "="  
                    + node.getText());
        }else{  
            System.out.println("--------" + node.getName() + "-------");  
        }
		if ("return".equals(node.getName())) {
			System.out.println(node.getText());
			str = node.getText();
		}
	}

	public String getReturnData(){
		return str;
	}
	
	public static void main(String[] args) throws DocumentException {
		SAXReader reader = new SAXReader();
		String url = "http://localhost:8080/CxfWebService/service/helloWorld/getlstObject";
		Document doc = reader.read(url);//doc=DocumentHelper.parseText(text);
		MyVisitor returnValue = new MyVisitor();
		doc.accept(returnValue);
		System.out.println(returnValue.getReturnData()+"====================");
	}
}
