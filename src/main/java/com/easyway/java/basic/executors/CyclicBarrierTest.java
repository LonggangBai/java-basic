package com.easyway.java.basic.executors;


import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 循环障碍CyclicBarrier，与CountDownLatch不同的是
该类是定义一个点。指定的线程到达某个点后，开始等待，直到所有的线程都到达这个点后。再一起继续向下执行。
（并且在所有线程到达该点后，可以实现某些操作。然后所有线程再继续向下执行）
在CyclicBarrier在释放等待线程之后 可以重用。
*/
public class CyclicBarrierTest {  
	  
    public static void main(String[] args) {  
      
        ExecutorService exec = Executors.newCachedThreadPool();       
        final Random random=new Random();  
          
        
        final CyclicBarrier barrier=new CyclicBarrier(4,new Runnable(){  
            @Override  
            public void run() {  
                System.out.println("大家都到齐了，开始happy去");  
            }}
        );  
          
        for(int i=0;i<4;i++){  
            exec.execute(new Runnable(){  
                @Override  
                public void run() {  
                    try {  
                        Thread.sleep(random.nextInt(1000));  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                    System.out.println(Thread.currentThread().getName()+"到了，其他哥们呢");  
                    try {  
                        barrier.await();//等待其他哥们  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    } catch (BrokenBarrierException e) {  
                        e.printStackTrace();  
                    }  
                    
                    System.out.println(Thread.currentThread().getName()+"准备去happy");  
                }});  
        }  
        exec.shutdown(); 
    }  
  
}  