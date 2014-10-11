<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:jaxrs="http://cxf.apache.org/jaxrs"
	xmlns:jaxws="http://cxf.apache.org/jaxws" xmlns:http-conf="http://cxf.apache.org/transports/http/configuration"
	xsi:schemaLocation="
                       http://www.springframework.org/schema/beans
                       http://www.springframework.org/schema/beans/spring-beans.xsd
                       http://cxf.apache.org/jaxrs
                       http://cxf.apache.org/schemas/jaxrs.xsd
                       http://cxf.apache.org/jaxws
                       http://cxf.apache.org/schemas/jaxws.xsd
                       http://cxf.apache.org/transports/http/configuration http://cxf.apache.org/schemas/configuration/http-conf.xsd">

	<!-- 针对CXF发布服务的需要的配置的加载的导入 -->
	<import resource="classpath:META-INF/cxf/cxf.xml"/>
    <import resource="classpath:META-INF/cxf/cxf-extension-soap.xml"/>
    <import resource="classpath:META-INF/cxf/cxf-extension-jaxws.xml"/>
    <import resource="classpath:META-INF/cxf/cxf-extension-http-jetty.xml"/>
	<import resource="classpath:META-INF/cxf/cxf-servlet.xml" />
	
</beans>


JAX-RS是一套用java实现REST服务的规范，提供了一些标注将一个资源类，一个POJOJava类，封装为Web资源。标注包括：
@Path，标注资源类或方法的相对路径
@GET，@PUT，@POST，@DELETE，标注方法是用的HTTP请求的类型
@Produces，标注返回的MIME媒体类型
@Consumes，标注可接受请求的MIME媒体类型
@PathParam，@QueryParam，@HeaderParam，@CookieParam，@MatrixParam，@FormParam,
分别标注方法的参数来自于HTTP请求的不同位置，例如@PathParam来自于URL的路径，@QueryParam来自于URL的查询参数，@HeaderParam来自于HTTP请求的头信息，@CookieParam来自于HTTP请求的Cookie。

一个好的实现方法是将REST服务的定义和实现分开，这样代码的结构简洁、清晰，在后期也可以很方便的进行实现的替换和服务定义的修改。

/** 
 * 一个基于JAX-RS的服务
 * [JAX-RS是无状态的服务]
 * 注意，必须要在JavaBean上添加@XMLRootElemet注解
 * 此项目启动成功以后，可以通过以下方式访问：
 * http://localhost:9004/student?_wadl&_type=xml
 * 注意是_wadl&_type=xml
 * 将返回一个如何调用RESTful ws的wsdl文件说明书
 */
@Produces("application/json,application/xml")
@Consumes("application/json,application/xml")
//consumes：指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
//produces: 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回;


CXF RESTful风格WebService

/*
     注释（Annotation）：在 javax.ws.rs.* 中定义，是 JAX-RS (JSR 311) 规范的一部分。 
    @Path：定义资源基 URI。由上下文根和主机名组成，资源标识符类似于 http://localhost:8080/RESTful/rest/hello。 
    @GET：这意味着以下方法可以响应 HTTP GET 方法。 
    @Produces：以纯文本方式定义响应内容 MIME 类型。
    
    @Context： 使用该注释注入上下文对象，比如 Request、Response、UriInfo、ServletContext 等。 
    @Path("{contact}")：这是 @Path 注释，与根路径 “/contacts” 结合形成子资源的 URI。 
    @PathParam("contact")：该注释将参数注入方法参数的路径，在本例中就是联系人 id。其他可用的注释有 @FormParam、@QueryParam 等。 
    @Produces：响应支持多个 MIME 类型。在本例和上一个示例中，APPLICATION/XML 将是默认的 MIME 类型。
 */