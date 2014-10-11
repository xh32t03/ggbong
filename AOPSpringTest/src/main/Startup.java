package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.autrade.stage.serverimpl.ServerWrapper;
import com.autrade.stage.utility.LogUtility;

/**
 * 服务器启动类
 * 
 * @author liuxl
 *
 */
public class Startup 
{
    /**
     * 
     * @param args
     */
	public static void main(String[] args)
	{
		startUp();
	}
	
	// default
	private static String configLocation = "classpath:resources/config/applicationContext.xml";
	
	public static void startUp()
	{
		try
		{
		    // druid
//		    configLocation = "classpath:config/applicationContext-druid.xml";
		    // proxool
//		    configLocation = "classpath:config/applicationContext-proxool.xml";
		    
		    // test
		    configLocation = "classpath:config/applicationContext-test.xml";
		    
			//初始化Spring容器
			ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocation);
			
			//启动服务器
			ServerWrapper.startServer(ctx);
		}
		catch (Exception e)
		{
			LogUtility.getSysLogger().error(e.getMessage(), e);
		}
	}
}
