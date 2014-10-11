package main.aop;

import java.lang.reflect.Method;

public interface ILoggerOperation
{

    /**
     * ����ִ��֮ǰ�Ĳ���
     * 
     * @param method
     */
    void start(Method method);

    /**
     * ����ִ��֮��Ĳ���
     * 
     * @param method
     */
    void end(Method method);
    
}
