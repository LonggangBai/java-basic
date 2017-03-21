package com.easyway.java.basic.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/**
 * <pre>
 * Callable与Future的应用
submit提交一个任务

 Callable接口代表一段可以调用并返回结果的代码;Future接口表示异步任务，是还没有完成的任务给出的未来结果。所以说Callable用于产生结果，Future用于获取结果。

 Java 5在concurrency包中引入了Java.util.concurrent.Callable 接口，它和Runnable接口很相似，但它可以返回一个对象或者抛出一个异常。

    Callable接口使用泛型去定义它的返回类型。Executors类提供了一些有用的方法在线程池中执行Callable内的任务。由于Callable任务是并行的（并行就是整体看上去是并行的，其实在某个时间点只有一个线程在执行），我们必须等待它返回的结果。

    java.util.concurrent.Future对象为我们解决了这个问题。在线程池提交Callable任务后返回了一个Future对象，使用它可以知道Callable任务的状态和得到Callable返回的执行结果。Future提供了get()方法让我们可以等待Callable结束并获取它的执行结果。
下面我们使用单线程池来做一个简单的代码测试 
 * 上面这个例子，我们不是通过executor来提交一个任务，而是通过submit这个接口来提交一Callable任务，callable这个接口里面有一个call方法，我们把要执行的任务写在这个call方法里面，然后会有一个return，然后我们通过Future这个对象就可以得到一个结果。

这个

  V get(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException;
}

这个方法就是，程序会等待long timeout这么长时间，如果在这么长时间内拿不到结果，程序就会抛出一个异常，

上面这个程序就会抛出一个异常，因为线程在执行时休眠了2s而future只等待了1秒，所以是拿不到结果的，抛出一个异常，如果我们把上面get方法中的1改成2就能正确拿到结果。get方法也可以不加参数，那么如果不加参数，如果future等待不到返回结果就会一直处于等待状态。
submit提交一组任务

上面这个方法我们是利用future提交一个任务，那么我们也可以使用submit提交一组callable任务，下面我们看代码
 * </pre>
 * @author longgangbai
 *
 */
public class CallableAndFuture {  
    
    public static void main(String[] args) throws TimeoutException {  
  
        ExecutorService executorService = Executors.newSingleThreadExecutor();  
        Future<Integer> future = executorService.submit(new Callable<Integer>(//使用submit提交一个任务，我们就能获得一个返回结果  
                ) {  
                    @Override  
                    public Integer call() throws Exception {  
                        Thread.sleep(2000);//我们让当前线程休眠2s  
                        return 111;  
                    }  
        });//submit返回的是一个future  
        System.out.println("等待结果");  
        try {  
            System.out.println(future.get(1, TimeUnit.SECONDS));//future这个get就可以获得上面任务的结果  
        } catch (InterruptedException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (ExecutionException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
    
    public void dd(){
    	ExecutorService ThreadPool = Executors.newFixedThreadPool(15);  
        CompletionService<String> completionService = new ExecutorCompletionService<String>(ThreadPool);  
        for(int i=1;i<=10;i++){  
            final int b = i;  
            completionService.submit(  
                    new Callable<String>() {  
                        @Override  
                        public String call() throws Exception {  
                            Thread.sleep(2000);  
                            return "任务"+b+"执行完了！";  
                        }  
                    });  
        }  
        try {  
            for(int i=0;i<10;i++){  
            System.out.println("拿到结果"+completionService.take().get());  
            }  
        } catch (InterruptedException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (ExecutionException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
}     