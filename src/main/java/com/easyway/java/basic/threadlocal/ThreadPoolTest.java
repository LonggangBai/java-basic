package com.easyway.java.basic.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * <pre>
 * java线程池
什么是线程池？（摘抄自百度知道）

 假设一个服务器完成一项任务所需时间为：T1 创建线程时间，T2 在线程中执行任务的时间，T3 销毁线程时间。
    如果：T1 + T3 远大于 T2，则可以采用线程池，以提高服务器性能。
                一个线程池包括以下四个基本组成部分：
                1、线程池管理器（ThreadPool）：用于创建并管理线程池，包括 创建线程池，销毁线程池，添加新任务；
                2、工作线程（PoolWorker）：线程池中线程，在没有任务时处于等待状态，可以循环的执行任务；
                3、任务接口（Task）：每个任务必须实现的接口，以供工作线程调度任务的执行，它主要规定了任务的入口，任务执行完后的收尾工作，任务的执行状态等；
                4、任务队列（taskQueue）：用于存放没有处理的任务。提供一种缓冲机制。
    线程池技术正是关注如何缩短或调整T1,T3时间的技术，从而提高服务器程序性能的。它把T1，T3分别安排在服务器程序的启动和结束的时间段或者一些空闲的时间段，这样在服务器程序处理客户请求时，不会有T1，T3的开销了。
    线程池不仅调整T1,T3产生的时间段，而且它还显著减少了创建线程的数目，看一个例子：
    假设一个服务器一天要处理50000个请求，并且每个请求需要一个单独的线程完成。在线程池中，线程数一般是固定的，所以产生线程总数不会超过线程池中线程的数目，而如果服务器不利用线程池来处理这些请求则线程总数为50000。一般线程池大小是远小于50000。所以利用线程池的服务器程序不会为了创建50000而在处理请求时浪费时间，从而提高效率。

线程池的作用
现在服务器端的应用程序几乎都采用了“线程池”技术，这主要是为了提高系统效率。因为如果服务器对应每一个请求就创建一个线程的话，在很短的一段时间内就会产生很多创建和销毁线程动作，导致服务器在创建和销毁线程上花费的时间和消耗的系统资源要比花在处理实际的用户请求的时间和资源更多。线程池就是为了尽量减少这种情况的发生。
 
  下面我们来看看怎么用Java实现一个线程池。一个比较简单的线程池至少应包含线程池管理器、工作线程、任务队列、任务接口等部分。其中线程池管理器(ThreadPool Manager)的作用是创建、销毁并管理线程池，将工作线程放入线程池中；工作线程是一个可以循环执行任务的线程，在没有任务时进行等待；任务队列的作用是提供一种缓冲机制，将没有处理的任务放在任务队列中；任务接口是每个任务必须实现的接口，主要用来规定任务的入口、任务执行完后的收尾工作、任务的执行状态等，工作线程通过该接口调度任务的执行。
java中线程池的创建
1.newCachedThreadPool缓存线程池
java.util.concurrent.Executors;

Java通过Executors提供四种线程池，分别为：
newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)
执行。
newCachedThreadPool创建一个缓存线程池，这个缓存线程池的长度会根据我们需要执行的任务数的大小进行变化
缓存线程池与固定线程池的区别在于对于需要执行很多短期异步任务的程序来说，缓存线程池可以提高程序性能，因为长时间保持空闲的这种类型
的线程池不会占用任何资源，调用缓存线程池对象将重用以前构造的线程（线程可用状态），若线程没有可用的，则创建一个新线程添加到池中，缓存
线程池将终止并从池中移除60秒未被使用的线程。 
上面这段程序就是现在有10个任务需要执行，每个任务的内容是循环10次打印，此时我们使用newCachaedThreadPool这个缓存线程池，系统就会
自动为我们生成一个线程池，并且这个线程池中有10个线程分别取执行者10个任务。当然四种线程池如果在运行完任务后不结束线程，线程就会一直
处于等待状态，等待新的任务进来执行，所以我们可以使用executorService.shutdown()来结束当前线程池中的线程。
http://www.blogjava.net/vincent/archive/2008/09/03/226785.html这篇文章我感觉里面对缓存线程池的内存溢出解释不错。
 * 此时我们发现，当前程序中，永远只有一个线程去执行这10个任务。
在这里我们来看一下shutdown（）和shutdownow（）的区别，这里我就不粘打印结果了，
shutdown()是当这一个线程执行完10个任务之后才关闭线程池，而shutdownow()是当第一个任务执行完就马上关闭线程池。
4.newScheduledThreadPool
newScheduledThreadPool是一个定长的可以固定时间执行的线程池
Java 5 推出了基于线程池设计的 ScheduledExecutor。其设计思想是，每一个被调度的任务都会由线程池中一个线程去执行，因此任务是并发执行的，相互之间不会受到干扰。需要注意的是，只有当任务的执行时间到来时，ScheduedExecutor 才会真正启动一个线程，其余时间 ScheduledExecutor 都是在轮询任务的状态。
[java] view plain copy
print?在CODE上查看代码片派生到我的代码片

    public class ThreadPoolTest {  
        public static void main(String[] args) {  
            ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);  
            scheduledThreadPool.schedule(new Runnable() {  
                @Override  
                public void run() {  
                    System.out.println("a");  
                      
                }  
            }, 10, TimeUnit.SECONDS);//创建一个定长的线程池，当执行的任务多余线程池的长度，就排队执行  
        }  
    }  

创建一个定时的线程池，这个线程池中有一个线程，上面的10代表延迟10s进行输出，运行程序，我们会发现在10s后a打印在控制台上，那么我们能不能实现第一次延迟10s打印一个a，以后没延迟2s打印一个a呢 ？可以的，此时我们需要用到scheduleWithFixedDelay这个方法
[java] view plain copy
print?在CODE上查看代码片派生到我的代码片

    public class ThreadPoolTest {  
        public static void main(String[] args) {  
            ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);  
            scheduledThreadPool.scheduleWithFixedDelay( new Runnable() {  
            @Override  
            public void run() {  
                    System.err.println("a");  
            }  
        },10,   
            2,  
        TimeUnit.SECONDS);//创建一个定长的线程池，当执行的任务多余线程池的长度，就排队执行  
        }  
    }  

运行上一段代码，我们会发现我们就完成了我们的需求。

在这说一下 scheduleWithFixedDelay和scheduleFixedRate两个方法的区别

scheduleWithFixedDelay从字面意义上可以理解为就是以固定延迟（时间）来执行线程任务，它实际上是不管线程任务的执行时间的，每次都要把任务执行完成后再延迟固定时间后再执行下一次。

而scheduleFixedRate呢，是以固定频率来执行线程任务，固定频率的含义就是可能设定的固定时间不足以完成线程任务，但是它不管，达到设定的延迟时间了就要执行下一次了。
 * </pre>
 * @author longgangbai
 *
 */
public class ThreadPoolTest {  
    public static void main(String[] args) {  
    	 //ExecutorService executorService = Executors.newCachedThreadPool();//缓存线程池，线程池中的线程数随着任务的多少进行不断变化  
        //ExecutorService executorService = Executors.newFixedThreadPool(3);//固定线程大小的线程池  
        ExecutorService executorService = Executors.newSingleThreadExecutor();//（单一线程池）只有单个线程池，如果池子中的线程池的线程死了就自动生成一个新的线程，总是会保证线程池中会有一个线程  
        for (int i = 1; i <= 10; i++) {//一共有10个任务  
            int j= i;  
            executorService.execute(new Runnable() {//每个任务执行的内容是循环十次输出内容  
                @Override  
                public void run() {  
                    for (int a = 1; a <= 10; a++) {  
                        try {  
                            Thread.sleep(20);  
                        } catch (InterruptedException e) {  
                            // TODO Auto-generated catch block  
                            e.printStackTrace();  
                        }  
                    }  
                }  
            });  
        }  
        executorService.shutdown();//执行完所有任务结束线程  
        //executorService.shutdownNow();//执行完线程池内线程任务的大小，不管剩余线程是否执行完，直接结束线程  
    }  
}  
