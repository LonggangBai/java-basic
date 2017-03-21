package com.easyway.java.basic.threadlocal.masterwork;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <pre>
 * 二、Master-worker ——分而治之

     Master-worker常用的并行模式之一，核心思想是由两个进程协作工作，master负责接收和分配任务，worker负责处理任务，并把处理结果返回给Master进程，由Master进行汇总，返回给客户端。

     它的好处在于能把一个大任务分解成若干个小任务，并行执行，提高系统吞吐量。而对于客户端而言，一旦提交任务，mater进程立刻返回一个处理结果，并非等待系统处理完毕再返回。

     下面利用Master-Worker模型实现一个计算1-100立方和，思路如下：

1、将计算任务分配成100个子任务，每个子任务用于计算单独数字的立方和

2、master产生固定个数的worker用于处理这个子任务

3、worker开始计算，并把结果写入resultMap中

4、master负责汇总map中的数据，求和后将最终结果返回给客户端。
 * 
 * </pre>
 * @author longgangbai
 *
 */
public class Master {  
    //任务队列  
    protected Queue<Object> workQueue=new ConcurrentLinkedQueue<Object>();  
    //work进程队列  
    protected  Map<String,Thread> threadMap=new HashMap<String,Thread>();  
    //子任务处理结果集  
    protected Map<String,Object> resultMap=new ConcurrentHashMap<String,Object>();  
      
    //是否所有的子任务都结束了  
    public boolean isComplete()  
    {  
        for(Map.Entry<String, Thread> entry:threadMap.entrySet())  
        {  
            if(entry.getValue().getState()!=Thread.State.TERMINATED)  
            {  
                return false;  
            }  
        }  
        return true;  
    }  
      
    //master的构造，需要一个worker线程和worker的进程书香  
    public Master(Worker worker,int countWorker)  
    {  
        worker.setWorkQueue(workQueue);  
        worker.setResultMap(resultMap);  
        for(int i=0;i<countWorker;i++)  
        {  
            threadMap.put(Integer.toString(i), new Thread(worker,Integer.toString(i)));  
        }  
    }  
      
    //提交任务-放入进程队列中  
    public void submit(Object job)  
    {  
        workQueue.add(job);  
        System.out.println("任务队列size："+workQueue.size());  
    }  
      
    //返回子任务结果集  
    public Map<String,Object> getResultMap()  
    {  
        return resultMap;  
  
    }  
      
    //开始运行所有的worker进程  
    public void execute()  
    {  
        for(Map.Entry<String, Thread> entry:threadMap.entrySet())  
        {  
            entry.getValue().start();//调用子线程 worker.run  
            System.out.println(entry.getValue());  
        }  
    }  
}  