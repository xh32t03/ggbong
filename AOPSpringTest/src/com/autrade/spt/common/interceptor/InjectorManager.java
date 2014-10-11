package com.autrade.spt.common.interceptor;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.autrade.stage.utility.JsonUtility;

/**
 * ����������
 * 
 * @author liuxl
 */
public class InjectorManager implements MethodInterceptor
{

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable
    {
        System.out.println("���뵽�˷�����������������");
        
        System.out.println("���õ�service��");
        System.out.println(invocation.getThis());

        System.out.println("���õķ�����");
        System.out.println(invocation.getMethod());

//        Method method = invocation.getMethod();
//        if (!Modifier.isAbstract(method.getModifiers())) 
//            return invocation.proceed();
        
        String targetName = invocation.getThis().getClass().getName(); 
        String methodName = invocation.getMethod().getName(); 
        Object[] arguments = invocation.getArguments(); 
        System.out.println("targetName��"+targetName);
        System.out.println("methodName��"+methodName);
        
        Object result = null;
        
        // ���
        Class<?>[] args = invocation.getMethod().getParameterTypes();
        if(null==args || args.length==0)
        {
            System.out.println("========== service["+targetName+"]���÷���["+methodName+"] ����� ==========");
        }
        else
        {
            System.out.println("service["+targetName+"]���÷���["+methodName+"] �����ǣ�");
            
            for (int i = 0; i < arguments.length; i++)
            {
                Object[] objs = invocation.getArguments();
                System.out.println(JsonUtility.toJSONString(objs[i], true));
            }
        }
        
        result = invocation.proceed();

        // ����ֵ
        Class<?> returnClass = invocation.getMethod().getReturnType();
        if(returnClass==Void.TYPE)
        {
            System.out.println("========== ��������Void���޷���ֵ ==========");
        }
        else
        {
            System.out.println("���ؽ���ǣ�");
            System.out.println(JsonUtility.toJSONString(result));
        }

        System.out.println("����������ִ�н�����������");

        return result;
    }

}
