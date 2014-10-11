package com.mash5.dbprovider;

/**
 * @author chunmeng
 * @date 11/05/10
 * @desc 服务器上数据库的常量
 */ 
public class Constants {
	
	public static String realPath="";
	
	public static final String Table_TABLE = "Table";//整表的定义，如表名等
	public static final String Table_FIELD = "Field";//表元数据的定义
	public static final String Table_USER = "User";//用户表名
	public static final String Table_ACCOUNT = "Account";//帐号表名，嵌套到其他表的属性里，属性名也是这个
	public static final String Table_SESSION = "Session";//Session表名
	public static final String Table_USERCOLLECTION = "UserCollection";//团队
	public static final String Table_ORGANIZATION = "Organizaion";//机构
	public static final String Table_DEPART = "Depart";//部门
	public static final String Table_DYNAMICINFO = "DynamicInfo";//动态信息
	public static final String Table_TASK = "Task";//业务登记列表
	public static final String Table_CONCERN = "Concern";//关注表
	public static final String Table_LOG = "Log";//日志信息
	public static final String Table_TASKFORM = "TaskForm";//业务表单
	public static final String Table_TASKTEMPLE = "TaskTemple";//业务模板
	
	public static final String Field_TABLE_TABLENAME = "tableName";//表名
	public static final String Field_FIELD_TABLEID = "tableId";//所属Table
	public static final String Field_FIELD_FIELDNAME = "fieldName";//元数据字段名
	public static final String Field_FIELD_TYPE = "type";//元数据类型
	
	public static final String Field_USER_ID = "_id";//用户id
	
	public static final String Field_ACCOUNT_USERID = "userId";//用户id
	public static final String Field_ACCOUNT_LONGINNAME = "loginName";//登录名
	public static final String Field_ACCOUNT_PASSWORD = "password";//登录密码
	public static final String Field_ACCOUNT_EMAIL = "email";//用户注册email
	
	public static final String Field_SESSION_USERID = "userId";//用户id
	public static final String Field_SESSION_STATUS = "status";//登录状态
	public static final String Field_SESSION_LONGINTIME = "loginTime";//登录时间
	public static final String Field_SESSION_OPERATETIME = "operateTime";//上次操作时间
	
	public static final String Field_USERCOLLECTION_DEPARTID = "departId";//所属部门
	public static final String Field_USERCOLLECTION_MANAGER = "manager";//团队管理者
	public static final String Field_USERCOLLECTION_OWNER = "owner";//团队拥有者（创建者）
	public static final String Field_USERCOLLECTION_MEMBER = "member";//团队成员
	
	public static final String Field_ORGANIZATION_PARENTORGANIZATIONID = "parentOrganizationId";//所属机构
	
	public static final String Field_DEPART_MANAGER = "manager";//部门管理者
	public static final String Field_DEPART_MEMBER = "member";//部门成员
	public static final String Field_DEPART_ORGANIZATIONID = "organizationId";//所属机构
	public static final String Field_DEPART_PARENTDEPARTID = "parentDepartId";//所属部门
	
	public static final String Field_DYNAMICINFO_BELONGTOID = "belongToId";//动态信息所属对象id
	public static final String Field_DYNAMICINFO_BELONGTODETAILID = "belongToDetailId";//所属对象详细id（如：具体业务表里的id）
	public static final String Field_DYNAMICINFO_BELONGTOTYPE = "belongToType";//所属对象类型，如：1-部门、2-团队、3-业务、4-人员
	public static final String Field_DYNAMICINFO_CONTENT = "content";//动态信息内容。内容是个Object，文本、图片、视频...
	public static final String Field_DYNAMICINFO_TYPE = "type";//动态内容类型。如：1-文本、2-图片、3-文件、4-视频、.....
	public static final String Field_DYNAMICINFO_DATE = "date";//动态内容时间
	
	public static final String Field_TASK_DEPARTID = "departId";//业务所属部门
	public static final String Field_TASK_USERCOLLECTION = "userCollection";//业务所属团队
	public static final String Field_TASK_TASKCREATOR = "taskCreator";//业务创建者
	public static final String Field_TASK_TASKNAME = "taskName";//业务名称
	public static final String Field_TASK_TASKTABLE = "taskTable";//业务表名
	public static final String Field_TASK_CREATETIME = "createTime";//业务创建时间
	public static final String Field_TASK_TASKTEMPLEID = "taskTempleId";//业务模板id
	public static final String Field_TASK_TASKFORMID = "taskFormId";//业务表单id
	
	public static final String Field_TASKFORM_TASKFORM = "taskForm";//业务表单
	public static final String Field_TASKTEMPLE_TASKTEMPLE = "taskTemple";//业务表单
	
	public static final String Field_CONCERN_BELONGTOID = "belongToId";//关注对象所属id
	public static final String Field_CONCERN_TYPE = "type";//关注对象所属类型。1-业务、2-团队、3-个人
	public static final String Field_CONCERN_USERID = "userId";//用户id
	
	public static final String Field_LOG_BELONGTOID = "belongToId";//操作对象所属id，如：业务id、团队id、部门id、机构id
	public static final String Field_LOG_CONTENT = "content";//操作内容
	public static final String Field_LOG_TYPE = "type";//操作对象类型。1-业务、2-团队、3-部门、4-机构
	public static final String Field_LOG_USERID = "userId";//操作人员
	public static final String Field_LOG_OPERATETIME = "operateTime";//操作时间
	
}