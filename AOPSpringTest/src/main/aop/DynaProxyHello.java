package main.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynaProxyHello implements InvocationHandler
{

    /**
     * ������
     */
    private Object proxy;
    /**
     * Ҫ����Ķ���(Ҳ��������Ҫ�ڷ�����ǰ�����ҵ���߼��Ķ���,�������е�Hello)
     */
    private Object delegate;

    /**
     * ��̬���ɷ������������Ķ��� (д���̶�)
     * 
     * @param delegate
     * @param proxy
     * @return
     */
    public Object bind(Object delegate, Object proxy)
    {
        this.proxy = proxy;
        this.delegate = delegate;
        return Proxy.newProxyInstance(this.delegate.getClass().getClassLoader(),
                                      this.delegate.getClass().getInterfaces(), this);
    }

    /**
     * Ҫ����Ķ����е�ÿ�������ᱻ�˷�����ȥJVM����,Ҳ����˵,Ҫ����Ķ���ķ���ֻ��ͨ���˷������� �˷����Ƕ�̬��,�����ֶ����õ�
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        Object result = null;
        try
        {
            // ����õ������ߵ�ʵ��
            Class<?> clazz = this.proxy.getClass();
            // ����õ������ߵ�Start����
            Method start = clazz.getDeclaredMethod("start", new Class[] { Method.class });
            // ����ִ��start����
            start.invoke(this.proxy, new Object[] { method });
            // ִ��Ҫ��������ԭ������
            result = method.invoke(this.delegate, args);
            // ����õ������ߵ�end����
            Method end = clazz.getDeclaredMethod("end", new Class[] { Method.class });
            // ����ִ��end����
            end.invoke(this.proxy, new Object[] { method });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

}
