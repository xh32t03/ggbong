package main.aop;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggerOperation implements ILoggerOperation
{

    Log log = LogFactory.getLog(LoggerOperation.class);
    
    public void end(Method method)
    {
        log.debug(method.getName() + " Method end.");
    }

    public void start(Method method)
    {
        log.info(method.getName() + " Method Start!");
    }

}
