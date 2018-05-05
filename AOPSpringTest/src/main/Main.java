package main;

import java.util.Arrays;

import main.test.dao.TestDao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;
public class Main
{

    public static ApplicationContext ctx;

    public static void test()
    {
        String[] a;
        String[] aa = {"1", "2", "4", "7"};
        
        a = aa;
        
        System.out.println(a[1]);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
//        String[] configLocations = { "classpath:config/applicationContext-datasource.xml" };
//
//        ctx = new ClassPathXmlApplicationContext(configLocations);
//
//        String[] beanList = ctx.getBeanDefinitionNames();
//        for (String bean : beanList)
//        {
//            System.err.println("bean Name =" + bean);
//        }
//        
//        TestDao testDao = (TestDao) ctx.getBean("testDao");
//        
//        assertEquals(ctx.getBean("mysqlDataSourceBean"), testDao.getMysqlDataSource()); 
    
//        int[] dataArr = {1, 9, 7, 0, 8, 3};
//        
//        int len = dataArr.length;
//        int tmp;
//        
//        for (int i = 0; i < len; i++)
//        {
//            for (int j = 0; j < len - i - 1; j++)
//            {
//                if (dataArr[j] > dataArr[j + 1])
//                {
//                    tmp = dataArr[j];
//                    dataArr[j] = dataArr[j + 1];
//                    dataArr[j + 1] = tmp;
//                }
//            }
//        }
//        
//        System.out.println(Arrays.toString(dataArr));
//        
//        int iTemp;
//        for (int i = 0; i < dataArr.length; i++) {
//            for (int j = dataArr.length - 1; j > i; j--) {
//                //‘>’升序（从小到大），‘<’降序（从大到小）
//                if (dataArr[i] > dataArr[j]) {
//                    iTemp = dataArr[i];
//                    dataArr[i] = dataArr[j];
//                    dataArr[j] = iTemp;
//                }
//            }
//        }

        
//        test();
        
        String a = "hello";
        String b = "hello";
        
        String c = new String("hello");
        
        System.out.println(a.equals(b));
        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(a.equals(c));
    }

}
