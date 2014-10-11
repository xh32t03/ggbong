package com.mash5.dbprovider;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 
 * @author zhang ting
 * @data 11/05/09
 * @desc 这是部门数据提供商
 *
 */
public class DepartmentProvider {

	public static int save(JSONObject data) {
		return 0;
	}
	
	/**
	 * 获取部门集合
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject getDepList(){
		JSONObject jsObj = null;
		String str = "{" +
		"success:{}," +
		"message:{}," +
		"object:[{type: 2,isOrganization: 1,manager: [],member: [],owner: {},name: '设计部',icon: 'team_head01',parentUserCollection: []," +
				 "task: [{createTime: '1989-09-29',taskCreator: {},taskFormId: {},taskName: '通知单',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 1,taskIcon: 'team_head02',taskDesc: '这是通知单'}," +
				 		 //第二条任务数据
				 		"{createTime: '1989-09-29',taskCreator: {},taskFormId: {},taskName: '例会',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 2,taskIcon: 'team_head02',taskDesc: '例会'}," +
				 		 //第三条任务数据
				 		"{createTime: '1989-09-29',taskCreator: {},taskFormId: {},taskName: '请假条',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 3,taskIcon: 'team_head02',taskDesc: '请假条'}]," +
				 //设计部的子部门   UX,UI设计部
				"childrenUserCollection: []}," +
				//第二个部门数据
				"{type: 2,isOrganization: 1,manager: [],member: [],owner: {},parentUserCollection: [],childrenUserCollection: []" +
				 "task: [{createTime: '1990-01-27',taskCreator: {},tsskFormId: {},taskName: '张婷开发',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 1,taskIcon: 'team_head06',taskDesc: '这是张婷所开发的'}," +
				        //开发部下的第二条任务数据
					   "{createTime: '1990-01-27',taskCreator: {},tsskFormId: {},taskName: '小亮开发',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 2,taskIcon: 'team_head06',taskDesc: '这是小亮所开发的'}," +
					   //开发部下的第三条任务数据
					   "{createTime: '1990-01-27',taskCreator: {},tsskFormId: {},taskName: '彩娟开发',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 3,taskIcon: 'team_head06',taskDesc: '这是彩娟所开发的'}," +
					   //开发部下的第四条任务数据
					   "{createTime: '1990-01-27',taskCreator: {},tsskFormId: {},taskName: '马杰开发',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 4,taskIcon: 'eam_head06',taskDesc: '这是马杰所开发的'}]," +
				"name: '开发部',icon: 'team_head03',desc: '这是一个开发部'}," +
				//第三个部门数据
				"{type: 2,isOrganiztion: 1,manager: [],member: [],owner: {},name: '人事部',icon: 'team_head07',desc: '这是人事部的相关数据'," +
				  "task:[{createTime: '1990-03-23',taskCreator: {},taskFormId: {},taskName: '销售区1',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 1,taskIcon: 'team_head08',taskDesc: '这是销售区1的任务'}," +
				       //人事部下的第二个任务数据
					  "{createTime: '1990-03-23',taskCreator: {},taskFormId: {},taskName: '销售区22',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 2,taskIcon: 'team_head08',taskDesc: '这是销售区22的任务22'}," +
					  //人事部下的第三个任务数据
					  "{createTime: '1990-03-23',taskCreator: {},taskFormId: {},taskName: '销售区33',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 3,taskIcon: 'team_head08',taskDesc: '这是销售区33的任务33'}," +
					  //人事部下的第四个任务数据
					  "{createTime: '1990-03-23',taskCreator: {},taskFormId: {},taskName: '销售区44',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 4,taskIcon: 'team_head08',taskDesc: '这是销售区44的任务44'}]}," +
				//第四个部门数据
				"{type: 2,isOrganiztion: 1,manager: [],member: [],owner: {},parentUserCollection: [],name: '市场部',icon: 'team_head05',desc: '这是市场部的相关数据'," +
				 "task: [{createTime: '1990-03-23',taskCreator: {},taskFormId: {},taskName: '销售区1',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 1,taskIcon: 'team_head08',taskDesc: '这是销售区1的任务'}," +
				       //人事部下的第二个任务数据
					  "{createTime: '1990-03-23',taskCreator: {},taskFormId: {},taskName: '销售区22',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 2,taskIcon: 'team_head08',taskDesc: '这是销售区22的任务22'}," +
					  //人事部下的第三个任务数据
					  "{createTime: '1990-03-23',taskCreator: {},taskFormId: {},taskName: '销售区33',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 3,taskIcon: 'team_head08',taskDesc: '这是销售区33的任务33'}," +
					  //人事部下的第四个任务数据
					  "{createTime: '1990-03-23',taskCreator: {},taskFormId: {},taskName: '销售区44',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 4,taskIcon: 'team_head08',taskDesc: '这是销售区44的任务44'}]," +
				          //省内市场部
				 "childrenUserCollection: []"+
	"}";
		try {
			jsObj = new JSONObject(str);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsObj;
	}
	
	public static JSONObject getDepartment(){
		JSONObject jsObj = null;
		String str = "{success:{},message:{}," +
		"object:[{type: 2,isOrganiztion: 1,manager: [],member: [],owner: {},name: '设计部',icon: 'team_head01',parentUserCollection: []," +
		 "task: [{createTime: '1989-09-29',taskCreator: {},taskFormId: {},taskName: '通知单',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 1,taskIcon: 'team_head02',taskDesc: '这是通知单'}," +
		 		 //第二条任务数据
		 		"{createTime: '1989-09-29',taskCreator: {},taskFormId: {},taskName: '例会',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 2,taskIcon: 'team_head02',taskDesc: '例会'}," +
		 		 //第三条任务数据
		 		"{createTime: '1989-09-29',taskCreator: {},taskFormId: {},taskName: '请假条',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 3,taskIcon: 'team_head02',taskDesc: '请假条'}]," +
		 //设计部的子部门   UX,UI设计部
		"childrenUserCollection: []}," +
		//第二个部门数据
		"{type: 2,isOrganiztion: 1,manager: [],member: [],owner: {},parentUserCollection: [],childrenUserCollection: []," +
		 "task: [{createTime: '1990-01-27',taskCreator: {},taskFormId: {},taskName: '张婷开发',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 1,taskIcon: 'team_head06',taskDesc: '这是张婷所开发的'}," +
		        //开发部下的第二条任务数据
			   "{createTime: '1990-01-27',taskCreator: {},taskFormId: {},taskName: '小亮开发',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 2,taskIcon: 'team_head06',taskDesc: '这是小亮所开发的'}," +
			   //开发部下的第三条任务数据
			   "{createTime: '1990-01-27',taskCreator: {},taskFormId: {},taskName: '彩娟开发',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 3,taskIcon: 'team_head06',taskDesc: '这是彩娟所开发的'}," +
			   //开发部下的第四条任务数据
			   "{createTime: '1990-01-27',taskCreator: {},taskFormId: {},taskName: '马杰开发',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 4,taskIcon: 'eam_head06',taskDesc: '这是马杰所开发的'}]," +
		"name: '开发部',icon: 'team_head03',desc: '这是一个开发部'}," +
		//第三个部门数据
		"{type: 2,isOrganiztion: 1,manager: [],member: [],owner: {},name: '人事部',icon: 'team_head07',desc: '这是人事部的相关数据'," +
		  "task:[{createTime: '1990-03-23',taskCreator: {},taskFormId: {},taskName: '销售区1',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 1,taskIcon: 'team_head08',taskDesc: '这是销售区1的任务'}," +
		       //人事部下的第二个任务数据
			  "{createTime: '1990-03-23',taskCreator: {},taskFormId: {},taskName: '销售区22',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 2,taskIcon: 'team_head08',taskDesc: '这是销售区22的任务22'}," +
			  //人事部下的第三个任务数据
			  "{createTime: '1990-03-23',taskCreator: {},taskFormId: {},taskName: '销售区33',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 3,taskIcon: 'team_head08',taskDesc: '这是销售区33的任务33'}," +
			  //人事部下的第四个任务数据
			  "{createTime: '1990-03-23',taskCreator: {},taskFormId: {},taskName: '销售区44',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 4,taskIcon: 'team_head08',taskDesc: '这是销售区44的任务44'}]}," +
		//第四个部门数据
		"{type: 2,isOrganiztion: 1,manager: [],member: [],owner: {},parentUserCollection: [],name: '市场部',icon: 'team_head05',desc: '这是市场部的相关数据'," +
		 "task: [{createTime: '1990-03-23',taskCreator: {},taskFormId: {},taskName: '销售区1',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 1,taskIcon: 'team_head08',taskDesc: '这是销售区1的任务'}," +
		       //人事部下的第二个任务数据
			  "{createTime: '1990-03-23',taskCreator: {},taskFormId: {},taskName: '销售区22',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 2,taskIcon: 'team_head08',taskDesc: '这是销售区22的任务22'}," +
			  //人事部下的第三个任务数据
			  "{createTime: '1990-03-23',taskCreator: {},taskFormId: {},taskName: '销售区33',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 3,taskIcon: 'team_head08',taskDesc: '这是销售区33的任务33'}," +
			  //人事部下的第四个任务数据
			  "{createTime: '1990-03-23',taskCreator: {},taskFormId: {},taskName: '销售区44',taskTableName: {},taskTemplateId: {},userCollectionId: [],taskId: 4,taskIcon: 'team_head08',taskDesc: '这是销售区44的任务44'}]," +
		          //省内市场部
		 "childrenUserCollection: []}" +
		"]}";
		try {
			jsObj = new JSONObject(str);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsObj;
	}
	
	/**
	 * 向服务器发送请求
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static HttpURLConnection getConn(String path) throws Exception{
		URL url = new URL(path);
		// 实例化一个HTTP连接对象conn
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		// 定义请求时间，在Android中最好是不好超过10秒。否则将被系统回收。
		conn.setConnectTimeout(5000);
		// 定义请求方式为GET，其中GET的格式需要注意
		conn.setRequestMethod("GET");
		//请求成功
		if(conn.getResponseCode()==200){
			return conn;
		}
		return null;
	}
	
	/**
	 * 将得到的文件流转为字符数组
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream inputStream) throws Exception{
		byte[] buffer = new byte[1024];
		int length = 0;
		//ByteArrayOutputStream继承了OutputStream
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 将输入流不断的读，并放到缓冲区中去,直到读完
		while((length = inputStream.read(buffer)) != 0){
			// 将缓冲区的数据不断的写到内存中去。
			outStream.write(buffer, 0, length);
		}
		byte[] outByte = outStream.toByteArray();
		inputStream.close();
		outStream.close();
		return outByte;
	}
	
	/**
	 * 根据id获取部门
	 * @param id
	 * @return
	 */
	public static JSONObject getDepById(int id){
		
		return null;
	}
}