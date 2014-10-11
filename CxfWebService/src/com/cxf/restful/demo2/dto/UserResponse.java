package com.cxf.restful.demo2.dto;

import javax.xml.bind.annotation.*;  

@XmlRootElement(name = "User")  
@XmlAccessorType(XmlAccessType.FIELD)  
public class UserResponse  
{  
    private String status;  
  
    private StudentDto data;  
  
    public UserResponse()  
    {  
    }  
  
    public UserResponse(String status, StudentDto data)  
    {  
        this.status = status;  
        this.data = data;  
    }  
  
    public String getStatus()  
    {  
        return status;  
    }  
  
    public void setStatus(String status)  
    {  
        this.status = status;  
    }  
  
    public Object getData()  
    {  
        return data;  
    }  
  
    public void setData(StudentDto data)  
    {  
        this.data = data;  
    }  
} 