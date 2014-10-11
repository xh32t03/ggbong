package main.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class HelloWorldTest
{

    /**
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        IHelloWorld helloWorld = new HelloWorldImpl();
        InvocationHandler handler = new HelloWorldHandler(helloWorld);

        // 创建动态代理对象
        IHelloWorld proxy = (IHelloWorld) Proxy.newProxyInstance(helloWorld.getClass().getClassLoader(),
                                                               helloWorld.getClass().getInterfaces(), handler);
        proxy.sayHelloWorld();
        
//        IHello hello = (IHello) new DynaProxyHello().bind(new Hello(), new LoggerOperation());
//        hello.sayGoogBye("Java Proxy");
//        hello.sayHello("Java Proxy");
    }
}
