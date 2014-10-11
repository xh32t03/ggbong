package main.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ʵ���ڷ�������ǰ�������̨��������ַ���
 * 
 * @author admin
 */
public class HelloWorldHandler implements InvocationHandler
{

    // Ҫ�����ԭʼ����
    private Object obj;

    public HelloWorldHandler(Object obj)
    {
        super();
        this.obj = obj;
    }

    /**
     * �ڴ���ʵ���ϴ��������ò����ؽ��
     * 
     * @param proxy ������
     * @param method ������ķ���
     * @param args �÷����Ĳ�������
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        Object result = null;
        // ����֮ǰ
        doBefore();
        // ����ԭʼ����ķ���
        result = method.invoke(obj, args);
        // ����֮��
        doAfter();
        return result;
    }

    private void doBefore()
    {
        System.out.println("before method invoke");
    }

    private void doAfter()
    {
        System.out.println("after method invoke");
    }

}
