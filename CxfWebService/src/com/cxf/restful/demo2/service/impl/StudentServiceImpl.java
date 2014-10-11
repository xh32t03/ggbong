package com.cxf.restful.demo2.service.impl;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.cxf.restful.demo2.dto.BaseResponse;
import com.cxf.restful.demo2.dto.ResultDto;
import com.cxf.restful.demo2.dto.StudentDto;
import com.cxf.restful.demo2.dto.UserResponse;
import com.cxf.restful.demo2.service.IStudentService;

/**
 * 访问方法有3种, 可以实现获取不同格式的内容. 

	http://localhost:8080/CxfWebService/service/restful/student/3/info2.json?name=abcss 
	http://localhost:8080/CxfWebService/service/restful/student/3/info2.xml?name=abcss 
	
	http://localhost:8080/CxfWebService/service/restful/student/3/info2?name=abcss&_type=xml 
	http://localhost:8080/CxfWebService/service/restful/student/3/info2?name=abcss&_type=json
 
 *  http://localhost:8080/CxfWebService/service/student/getStudentPage/1/3?_type=json
 *
 */
public class StudentServiceImpl implements IStudentService {

	@POST  
    @Consumes({ "application/json", "application/xml" })   
    public Response doActions(InputStream is)
    {
        return Response.ok(Status.OK).build();
    }
    
    @POST 
    @Path("/doResponse")
    @Consumes({ "application/json", "application/xml" })
    public Response doResponse(String name) {
        StudentDto s = new StudentDto();
        s.setId(1L);
        s.setName(name);
        try {
            s.setBirthday(new SimpleDateFormat("yyyy-MM-dd")
                    .parse("1983-04-27"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (name == null) {  
        	ResponseBuilder builder = Response.status(Status.BAD_REQUEST);  
            builder.type("application/xml");  
            builder.entity("<error>name Not Found</error>");  
            throw new WebApplicationException(builder.build()); 
            
            //return Response.status(Status.NOT_FOUND).build();
            //return Response.status(Status.BAD_REQUEST).build();
        } else {  
            return Response.ok(s).build();  
        } 
    }
    
    // info
    public UserResponse getStudent(String name) {
        StudentDto s = new StudentDto();
        s.setId(1l);
        s.setName(name);
        try {
            s.setBirthday(new SimpleDateFormat("yyyy-MM-dd")
                    .parse("1983-04-28"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new UserResponse("ok", s);
    }
    
    // info2
	public StudentDto getStudent(long id, String name) {
		StudentDto s = new StudentDto();
		s.setId(id);
		s.setName(name);
		try {
			s.setBirthday(new SimpleDateFormat("yyyy-MM-dd")
					.parse("1983-04-26"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return s;
	}

	// info3
	@Override
	public BaseResponse getMapVals() {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("a", "a1");
		map.put("b", "b1");
		map.put("c", "c1");
		
		BaseResponse cc = new BaseResponse();
		cc.setMap(map);
		return cc;
	}
	
	// info4
	@Override
	public List<StudentDto> getlstVals() {
		// TODO Auto-generated method stub
		List<StudentDto> list = new ArrayList<StudentDto>();
		StudentDto aa = new StudentDto();
		aa.setId(1l);
		aa.setName("张三");
		aa.setAge(13);
		aa.setScroe(98l);
		
		StudentDto bb = new StudentDto();
		bb.setId(2l);
		bb.setName("李四");
		bb.setAge(19);
		bb.setScroe(66l);
		
		list.add(aa);
		list.add(bb);
		return list;
	}
	
	@Override
	public ResultDto getStudentPage(int page, int limit) {
		ResultDto result = new ResultDto();
		
		List<StudentDto> students=new ArrayList<StudentDto>();
		StudentDto s1=new StudentDto();
		s1.setId(1l);
		s1.setName("leiwuluan");
		s1.setAddress("北京市东城区");
		s1.setAge(22);
		s1.setScroe(100l);
		students.add(s1);
		
		s1=new StudentDto();
		s1.setId(1l);
		s1.setName("leiwuluan");
		s1.setAddress("北京市东城区");
		s1.setAge(22);
		s1.setScroe(100l);
		students.add(s1);
		
		s1=new StudentDto();
		s1.setId(1l);
		s1.setName("leiwuluan");
		s1.setAddress("北京市东城区");
		s1.setAge(22);
		s1.setScroe(100l);
		students.add(s1);
		
		s1=new StudentDto();
		s1.setId(1l);
		s1.setName("leiwuluan");
		s1.setAddress("北京市东城区");
		s1.setAge(22);
		s1.setScroe(100l);
		students.add(s1);
		
		s1=new StudentDto();
		s1.setId(1l);
		s1.setName("leiwuluan");
		s1.setAddress("北京市东城区");
		s1.setAge(22);
		s1.setScroe(100l);
		students.add(s1);
		
		s1=new StudentDto();
		s1.setId(1l);
		s1.setName("leiwuluan");
		s1.setAddress("北京市东城区");
		s1.setAge(22);
		s1.setScroe(100l);
		students.add(s1);
		
		result.students=students;
		result.totalCount=100;
		result.totalPage=12;
		result.returncode="success";
		return result;
	}

	@Override
	public ResultDto getStudentById(long id) {
		ResultDto result = new ResultDto();
		List<StudentDto> students=new ArrayList<StudentDto>();
		StudentDto s1=new StudentDto();
		s1.setId(1l);
		s1.setName("leiwuluan");
		s1.setAddress("北京市东城区");
		s1.setAge(22);
		s1.setScroe(100l);
		students.add(s1);
		
		result.students=students;
		result.returncode="success";
		return result;
	}

	@Override
	public ResultDto deleteStudentById(long id) {
		ResultDto result = new ResultDto();
		result.returncode="success";
		return result;
	}

	@Override
	public ResultDto addStudent(StudentDto student) {
		ResultDto result = new ResultDto();
		
		System.out.println("ID:"+student.getId()+",Name:"+student.getName()
				+",Age:"+student.getAge()+",Scroe:"+student.getScroe()+",Address:"+student.getAddress());
//		System.out.println("Address:"+student.getAddress()+",Name:"+student.getName()+",ID:"+student.getId());
		
		result.returncode="success";
		return result;
	}

}
