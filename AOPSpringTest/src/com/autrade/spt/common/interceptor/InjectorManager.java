package com.autrade.spt.common.interceptor;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.autrade.stage.utility.JsonUtility;

/**
 * 方法拦截器
 * 
 * @author liuxl
 */
public class InjectorManager implements MethodInterceptor
{

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable
    {
        System.out.println("进入到了方法拦截器。。。。");
        
        System.out.println("调用的service：");
        System.out.println(invocation.getThis());

        System.out.println("调用的方法：");
        System.out.println(invocation.getMethod());

//        Method method = invocation.getMethod();
//        if (!Modifier.isAbstract(method.getModifiers())) 
//            return invocation.proceed();
        
        String targetName = invocation.getThis().getClass().getName(); 
        String methodName = invocation.getMethod().getName(); 
        Object[] arguments = invocation.getArguments(); 
        System.out.println("targetName："+targetName);
        System.out.println("methodName："+methodName);
        
        Object result = null;
        
        // 入参
        Class<?>[] args = invocation.getMethod().getParameterTypes();
        if(null==args || args.length==0)
        {
            System.out.println("========== service["+targetName+"]调用方法["+methodName+"] 无入参 ==========");
        }
        else
        {
            System.out.println("service["+targetName+"]调用方法["+methodName+"] 参数是：");
            
            for (int i = 0; i < arguments.length; i++)
            {
                Object[] objs = invocation.getArguments();
                System.out.println(JsonUtility.toJSONString(objs[i], true));
            }
        }
        
        result = invocation.proceed();

        // 返回值
        Class<?> returnClass = invocation.getMethod().getReturnType();
        if(returnClass==Void.TYPE)
        {
            System.out.println("========== 返回类型Void，无返回值 ==========");
        }
        else
        {
            System.out.println("返回结果是：");
            System.out.println(JsonUtility.toJSONString(result));
        }

        System.out.println("方法拦截器执行结束。。。。");

        return result;
    }

}
