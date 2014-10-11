package com.mash5.dbprovider;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import android.util.Log;

/**
 * 
 * @author Yanpeng
 * @date 2011-05-09
 * @desc 提供用户数据的一个类
 * 
 */
public class UserProvider {

	/**
	 * @desc 得到一个用用户的JSON对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getUser(int id) throws Exception {
		JSONObject jsonObject = null;

		// String path = "";
		// InputStream inStream = getConn(path).getInputStream();
		// byte[] getByte = readStream(inStream);
		// String json = new String(getByte);
		// jsonObject = new JSONObject(json);

		String message = "{"
				+ "'_id':1, "
				+ "'success':yes, "
				+ "'message':'desc', "
				+ "'object':{"
				           + "'user':{"
		                            + "'id':1, "
									+ "'name':'李刚', "
									+ "'birth':'1991-11-15',"
									+ "'icon':'image.jpg', "
									+ "'tel':'13813716553',"
									+ "'hometel':'023-1745682',"
									+ "'role':'经理',"
									+ "'email':'Yanpeng@mash5.com', "
									+ "'address':'苏州高新区', "
									+ "'company':'mash5',"
									+ "'companyAddress':'苏州市高新区',"
									+ "'desc':'抗战1931',"
				                    + "'depart':{" 
				                    		   + "'id':'1'," 
				                    		   + "'name':'设计部'},"		
				                    + "'follow':[" 
				                               + "{'id':1, 'type':'user', 'icon':'image.jpg', 'name':'西门吹雪', 'isMustFollow':'true'}," 
				                               + "{'id':2, 'type':'team', 'icon':'image.jpg', 'name':'技术交流', 'isMustFollow':'false'}," 
				                               + "{'id':3, 'type':'task', 'icon':'image.jpg', 'name':'电梯报表', 'isMustFollow':'true'},"
				                               + "{'id':4, 'type':'user', 'icon':'image.jpg', 'name':'夏淳', 'isMustFollow':'true'}," 
				                               + "{'id':5, 'type':'user', 'icon':'image.jpg', 'name':'刘德华', 'isMustFollow':'false'}," 
				                               + "{'id':6, 'type':'task', 'icon':'image.jpg', 'name':'请假通知', 'isMustFollow':'false'}," 
				                               + "{'id':10, 'type':'team', 'icon':'image.jpg', 'name':'临时小组', 'isMustFollow':'false'}],"
				                    + "'team':[" 
				                               + "{'id':1, 'icon':'image.jpg', 'name':'技术交流'}," 
				                               + "{'id':2, 'icon':'image.jpg', 'name':'设计开发'}," 
				                               + "{'id':3, 'icon':'image.jpg', 'name':'临时小组'}]" 
				                    + "}}}";
		jsonObject = new JSONObject(message);

		if (jsonObject.getInt("_id") == id) {
			/**所有数据类型的取值方式(仅供参考)**/
//			Log.i("_id", jsonObject.getString("_id"));
//			Log.i("message", jsonObject.getString("success"));
//			Log.i("name",
//					jsonObject.getJSONObject("object").getJSONObject("user")
//							.getString("name"));
//			Log.i("icon",
//					jsonObject.getJSONObject("object").getJSONObject("user")
//							.getString("icon"));
//			Log.i("hometel",
//					jsonObject.getJSONObject("object").getJSONObject("user")
//							.getString("hometel"));
//			Log.i("tel",
//					jsonObject.getJSONObject("object").getJSONObject("user")
//							.getString("tel"));
//			Log.i("departName",
//					jsonObject.getJSONObject("object").getJSONObject("user")
//							.getJSONObject("depart").getString("name"));
//			Log.i("role",
//					jsonObject.getJSONObject("object").getJSONObject("user")
//							.getString("role"));
//			Log.i("company",
//					jsonObject.getJSONObject("object").getJSONObject("user")
//							.getString("company"));
//			Log.i("companyAddress", jsonObject.getJSONObject("object")
//					.getJSONObject("user").getString("companyAddress"));
//			Log.i("email",
//					jsonObject.getJSONObject("object").getJSONObject("user")
//							.getString("email"));
//			Log.i("address",
//					jsonObject.getJSONObject("object").getJSONObject("user")
//							.getString("address"));
//			Log.i("depart",
//					jsonObject.getJSONObject("object").getJSONObject("user")
//							.getJSONObject("depart").getString("name"));
			JSONArray followArray = jsonObject.getJSONObject("object")
					.getJSONObject("user").getJSONArray("follow");
			JSONArray teamArray = jsonObject.getJSONObject("object")
					.getJSONObject("user").getJSONArray("team");
			for (int i = 0; i < followArray.length(); i++) {
//				Log.i("Follow" + i,
//						followArray.getJSONObject(i).getString("type")
//								+ "-" + followArray.getJSONObject(i).getString(
//										"isMustFollow") + "-" + followArray.getJSONObject(i).getString(
//										"icon") + "-" + followArray.getJSONObject(i).getString(
//										"name")  );
			}
			for(int i = 0;i<teamArray.length();i++){
//				Log.i("Team" + i,
//						teamArray.getJSONObject(i).getString("id")
//								+"-" + teamArray.getJSONObject(i).getString(
//										"name")+ "-" + teamArray.getJSONObject(i).getString(
//										"icon"));
			}
			return jsonObject;
		}

		return null;
	}

	/**
	 * @desc 得到一组用户的JSON对象
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static List<JSONObject> getUserList(int[] params) throws Exception {
		List<JSONObject> list = new ArrayList<JSONObject>();

		// String path = null;
		// InputStream inStream = getConn(path).getInputStream();
		// byte[] getByte = readStream(inStream);
		// String json = new String(getByte);
		// JSONArray array = new JSONArray(json);
		// for(int i = 0; i<array.length(); i++){
		// JSONObject user = array.getJSONObject(i);
		// list.add(user);
		// }
		String users = "[{\"name\":\"小马\",\"sex\":\"男\"},{\"name\":\"顺利\",\"sex\":\"男\"}, {\"name\":\"张婷\",\"sex\":\"女\"}, {\"name\":\"彩娟\",\"sex\":\"女\"}]";
		JSONArray userArray = new JSONArray(users);
		for (int i = 0; i < userArray.length(); i++) {
			JSONObject user = userArray.getJSONObject(i);
			list.add(user);
		}
		for (JSONObject js : list) {
//			Log.i("name", js.get("name").toString());
		}
		return list;
	}

	/**
	 * desc 向服务器发送请求
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static HttpURLConnection getConn(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection Conn = (HttpURLConnection) url.openConnection();
		Conn.setConnectTimeout(5000);
		Conn.setRequestMethod("GET");
		if (Conn.getResponseCode() == 200)
			return Conn;
		return null;
	}
	/**
	 * @desc 将得到的文件流转为字符数组
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream inStream) throws Exception {
		byte[] buffer = new byte[1024];
		int length = 0;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		while ((length = inStream.read(buffer)) != 0) {
			outStream.write(buffer, 0, length);
		}
		byte[] outByte = outStream.toByteArray();
		inStream.close();
		outStream.close();
		return outByte;
	}
	
	public static JSONObject getUserItems() {
		JSONObject jsObj = null;
		String json = "{'_id':'1','success':'yes','message':'desc','object':["
			+ "{'_id':1, 'lable':'电       话', 'value':'023-5645656', 'groupId':'1', fieldId:'1', allowEdit:'flase'},"
			+ "{'_id':2, 'lable':'手       机', 'value':'1231321321', 'groupId':'1', fieldId:'2', allowEdit:'flase'},"
			+ "{'_id':3, 'lable':'家庭电话', 'value':'1231321321', 'groupId':'1', fieldId:'3', allowEdit:'flase'},"
			+ "{'_id':1, 'lable':'电       话', 'value':'023-5645656', 'groupId':'1', fieldId:'4', allowEdit:'flase'},"
			+ "{'_id':2, 'lable':'手       机', 'value':'1231321321', 'groupId':'1', fieldId:'5', allowEdit:'flase'},"
			+ "{'_id':3, 'lable':'家庭电话', 'value':'1231321321', 'groupId':'1', fieldId:'6', allowEdit:'flase'},"
			+ "{'_id':3, 'lable':'部       门', 'value':'设计部', 'groupId':'2', fieldId:'1', allowEdit:'flase'},"
			+ "{'_id':3, 'lable':'额外信息', 'value':'', 'groupId':'3', fieldId:'1', allowEdit:'flase'},"
			+ "{'_id':4, 'lable':'姓       名', 'value':'李彦朋',  'groupId':'2', fieldId:'2' , allowEdit:'flase'}]}";
		
		try {
			jsObj = new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsObj;
	}
}
