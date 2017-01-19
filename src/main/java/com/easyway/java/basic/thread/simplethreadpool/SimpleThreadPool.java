package com.easyway.java.basic.thread.simplethreadpool;
/**
 * <pre>
 *  MyThread任务对象 and Client调用

2、JDK1.6中的内置线程池——Executor框架

       JDK中提供了一套Executor框架支持更好的控制多线程操作。在concurrent包中是并发线程的核心，其中ThreadPoolExecutor表示一个线程池，Executors扮演者线程池工厂的角色，通过Executors可以创建特定功能的线程池。
[html] view plain copy
在CODE上查看代码片派生到我的代码片

                 //1、new一个固定线程数量的线程池,有一个新任务提交时，从该线程池中查找是否有空闲线程，如果没有新的任务会被暂存于一个任务队列中，待有空闲线程后再执行。  
    ExecutorService threadPoolFix=Executors.newFixedThreadPool(3);//每次3个线程，执行3个任务；其他任务等待。     
    //2、缓存线程池--返回一个跟任务数相等的线程数量，有多少任务就产生多少个线程进行处理。动态变化-干完了定期回收  
    ExecutorService threadPoolCache=Executors.newCachedThreadPool();  
      
    //3、只有一个线程的线程池，任务按照任务队列顺序FIFO执行，保证线程池始终中有一个线程，当前死了，立马搞一个顶替工作  
    ExecutorService threadOne=Executors.newSingleThreadExecutor();  
      
    //4、线程池大小为1，支持在给定时间执行某任务，如在某个固定的延时之后执行，或者周期性执行。-定时器效果  
    ExecutorService threadSingleScheduled=Executors.newSingleThreadScheduledExecutor();  
      
    //5、指定一定固定大小的线程池  
    ExecutorService threadScheduled=Executors.newScheduledThreadPool(5);  

3、线程池优化

     线程池的大小对系统性能有着很大影响，过大过小都不利于程序运行。具体多大的线程池比较合适，可以借鉴《Java并发编程实战》中提供的一个计算公式：最优线程数量=CPU的数量*CPU的使用率*[1+（等待时间/计算时间）]。在java中通过Runtime.getRuntime().availableProcessors();获取可用CPU

4、自定义线程池

      jdk创建线程池对象大多继承于一个核心线程池类ThreadPoolExecutor，
      当采用自定义方式创建线程池时，也主要对这个类进行再封装。下面是ThreadPoolExecutor的核心构造方法：

    public ThreadPoolExecutor(int corePoolSize,//线程池中线程数量  
                              int maximumPoolSize,//线程池中最大线程数  
                              long keepAliveTime,//当池中线程数超过corePoolSize时，多余线程多长时间内会被销毁  
                              TimeUnit unit,//keepAliveTime的时间单位  
                              BlockingQueue<Runnable> workQueue, //任务队列     
                              RejectedExecutionHandler handler //拒绝策略，当任务太多时，如何拒绝任务) {  
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,  
             Executors.defaultThreadFactory(), handler);  
    }    

任务队列workqueue用于存放Runnable对象，主要有以下几种实现：

1、直接提交的队列

2、有界的任务队列

3、无界的任务队列

4、优先任务队列

拒绝策略：

1、AbortPolicy：直接抛出异常，系统停止工作

2、CallerRunsPolicy：只要线程池未关闭，直接再运行被抛弃的任务

3、DiscardOledestPolicy:丢弃最老的一个任务请求

4、DiscardPolicy：丢弃无法处理的任务，不予处理
 * 
 * </pre>
 * @author longgangbai
 *
 */
public class SimpleThreadPool  implements Runnable,Comparable<SimpleThreadPool>{  
        protected String name;  
          
        /*  
         * 构造方法  
         */  
        public SimpleThreadPool() {  
              
        }  
        public SimpleThreadPool(String name) {  
            this.name=name;  
        }  
      
        /*  
         * 模拟任务       
         */  
        @Override  
        public void run() {  
            try {  
                Thread.sleep(1000);  
                System.out.println("模拟任务！！！！！！name："+name);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
          
        /*  
         * 比较任务优先级  
         */  
        @Override  
        public int compareTo(SimpleThreadPool o) {  
            //前提：线程名称标记优先级  
            int me=Integer.parseInt(this.name.split("_")[1]);  
            //System.out.println(me);  
              
            int other=Integer.parseInt(o.name.split("_")[1]);  
              
            if(me>other) return 1;  
            if(me<other) return -1;  
            else    return 0;  
        }  
    }  
