package com.cxf.restful.demo2.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.cxf.restful.demo2.dto.BaseResponse;
import com.cxf.restful.demo2.dto.ResultDto;
import com.cxf.restful.demo2.dto.StudentDto;
import com.cxf.restful.demo2.dto.UserResponse;

/**
 * 一个基于JAX-RS的服务
 * [JAX-RS是无状态的服务]
 * 注意，必须要在JavaBean上添加@XMLRootElemet注解
 * 此项目启动成功以后，可以通过以下方式访问：
 * http://localhost:9004/student?_wadl&_type=xml
 * 注意是_wadl&_type=xml
 * 将返回一个如何调用RESTful ws的wsdl文件说明书
 * @author liuxl
 * @version 1.0 2011-11-06
 */
@Path(value="/student") //声明uri路径
//@Produces("*/*") 
@Produces(value={MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})//声明支持的类型
public interface IStudentService {
	
    @GET
    @Path(value = "/info")
    UserResponse getStudent(@QueryParam("name") String name);
    
	@GET
	@Path(value = "/{id}/info2")
	StudentDto getStudent(@PathParam("id") long id, @QueryParam("name") String name);

	@GET
	@Path(value = "/info3")
	BaseResponse getMapVals();
	
	@GET
	@Path(value = "/info4")
	List<StudentDto> getlstVals();
	
	/**
	 * 获取一页学生列表
	 * @param page
	 * @param limit
	 * @return
	 */
	@GET
	@Path(value="/getStudentPage/{page}/{limit}")
	ResultDto getStudentPage(@PathParam("page") int page,@PathParam("limit") int limit);
	
	/**
	 * 得到一个学生
	 * @param id
	 * @return
	 */
	@GET
	@Path(value="/getStudentById/{id}")
	ResultDto getStudentById(@PathParam("id") long id);
	
	/**
	 * 删除一个学生
	 * @param id
	 * @return
	 */
	@GET
	@Path(value="/deleteDeleteStudentById/{id}")
	ResultDto deleteStudentById(@PathParam("id") long id);
	
	/**
	 * 添加一个学生
	 * @param student
	 * @return
	 */
	@GET
	@Path(value="/addStudent/{name}/{age}/{scroe}/{address}")
	ResultDto addStudent(@PathParam("")StudentDto student);
}
