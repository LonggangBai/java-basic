package com.easyway.java.rpc.tps;

import com.easyway.java.rpc.secure.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.lang.reflect.InvocationHandler;
import java.util.concurrent.Semaphore;

public class ExecuteLimitFilter  {

    public void invoke(Map<String,Object> invocation) throws Exception {
        String methodName = StringUtils.objectToString(invocation.get("method_name"));
        Semaphore executesLimit = null;
        boolean acquireResult = false;
        String servicename= StringUtils.objectToString(invocation.get("service_name"));
        String maxstr=StringUtils.objectToString(invocation.get("max_execute"));
        String clazzstr=StringUtils.objectToString(invocation.get("service_class"));
        int max = Integer.valueOf(maxstr);
        if (max > 0) {
            RpcStatus count = RpcStatus.getStatus(servicename, methodName);
//            if (count.getActive() >= max) {
            /**
             * http://manzhizhen.iteye.com/blog/2386408
             * 通过信号量来做并发控制（即限制能使用的线程数量）
             * 2017-08-21 yizhenqiang
             */
            executesLimit = count.getSemaphore(max);
            if(executesLimit != null && !(acquireResult = executesLimit.tryAcquire())) {
                throw new Exception("Failed to invoke method " + methodName + " in provider " + servicename + ", cause: The service using threads greater than <dubbo:service executes=\"" + max + "\" /> limited.");
            }
        }
        long begin = System.currentTimeMillis();
        boolean isSuccess = true;
        RpcStatus.beginCount(servicename, methodName);
        try {
           //TODO
            //执行反射实现业务

            Class  clazz=Class.forName(clazzstr);
            final Class<?>[] interfaces = new Class[]{clazz};
            final InvocationHandler handler = new InvocationHandler(){
                @Override
               public  Object invoke(Object var1, Method var2, Object[] var3) throws Throwable
                {
                    return var2.invoke(var1,var3);
                }
            };

             Proxy.newProxyInstance( clazz.getClassLoader(), interfaces, handler);

        } catch (Throwable t) {
            isSuccess = false;
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new Exception("unexpected exception when ExecuteLimitFilter", t);
            }
        } finally {
            RpcStatus.endCount(servicename, methodName, System.currentTimeMillis() - begin, isSuccess);
            if(acquireResult) {
                executesLimit.release();
            }
        }
    }

}