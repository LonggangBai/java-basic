package com.easyway.java.basic.threadlocal;
/**
 * <pre>
 *      多核CPU充分利用CPU性能，就需要使用多线程并行挖掘CPU的潜力，并行程序设计对常用的多线程结构进行抽象，总结出几种典型多线程开发设计模式。
一、future 模式——精彩无需等待
     当程序提交一个请求，服务器对这个请求的处理可能很慢，在传统串行程序中，函数调用时同步的，也就是说程序必须等着服务器返回结果才会进行下一步处理。而Future 模式采用异步调用，充分利用等待的时间段，执行其他业务逻辑处理，最后再执行返回较慢的Future 数据，从而提高系统的响应速度。
     1、Future 的核心结构
    
    2、Future 模式的代码实现

Main：启动系统，调用Client发出请求；

Client：返回Data对象，理解返回FutureData，并开启ClientThread线程装配RealData；

Data：返回数据的接口；

FutureData：Future数据，构造很快，但是是一个虚拟的数据，需要装配RealData；

RealData：真实数据，构造比较慢。
最后程序输出：

请求完毕！
it.max.thinking.FutureData@15db9742 --future对象
我也在被处理哦  --其他业务处理结果
真实数据：namenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamenamename
     3、Future模式总结

      Future模式的核心在于去除了main函数中的等待时间，使得原本等待的时间用于处理其他业务，充分利用计算机资源。

      主要通过代理future类实现和RealData同样的接口实现，所以在main调用getResult时两个类对于main调用是一样的，因为future中的线程wait，返回future数据后，唤醒线程，实例化RealData时调用构造方法，组装数据返回。在此过程中main利用等待时间调用其它业务方法。 在类似于商品购买等业务中Future模式应用广泛。

      一句话总结：精彩无需等待，西瓜太大先捡着芝麻等西瓜。

二、JDK1.6的内置Future实现

     在JDK源码中，关于并发的实现concurrent 包中，Future接口提供线程控制：取消任务、返回对象、设置超时时间，同样一个RealData类实现该接口，提供call方法组织真实业务数据；
(1)Data接口 ，提供getResult方法
 * 
 * </pre>
 * @author longgangbai
 *
 */
 interface Data {  
    public String getResult();  
} 


     /* 
      * Future类实现，RealData的代理类，用于返回RealData的包装对象，封装了RealData的等待过程
      * 实现了一个快速返回RealData 的包装，但并非真实的返回结果。  
      */  
 class RealData  implements Data{  
	    protected  final String result;  
	    public RealData(String para)  
	    {  
	        StringBuffer sb=new StringBuffer();  
	        //模拟一个很慢的构造过程  
	        for(int i=0;i<50;i++)  
	        {  
	            sb.append(para);  
	            try {  
	                Thread.sleep(100);//代替一个很慢的操作过程  
	            } catch (Exception e) {  
	                  
	            }  
	        }  
	        result=sb.toString();  
	    }  
	    public String getResult()  
	    {  
	        return result;  
	    }  
	}  
 /**
  * (4)Client实现，用于获取FutureData，开启RealData线程，在接收请求后，快速返回future
  */
  class Client    
 {  
     public Data request(final String queryString)  
     {  
         final FutureData future=new FutureData();  
         new Thread() //RealData构建很慢，放到一个单独的线程中进行  
         {  
             public void run() {  
                 RealData realData=new RealData(queryString);  
                 future.setRealData(realData);  
                    //调用future的set方法，直接return 并唤醒RealData线程进行数据构造
                 };  
         }.start();  
         return future;  //立即被返回  
     }  
 } 
     
 /*  
  * 实现了一个快速返回RealData 的包装，但并非真实的返回结果。  
  */  
  class FutureData  implements Data{  
     protected RealData realData=null; //FutureData是RealData的一个包装  
     protected boolean isReady=false;  
     public synchronized void setRealData(RealData realData)  
     {  
         if(isReady)  
         {  
             return;  
         }  
         this.realData=realData;  
         isReady=true;  
         notifyAll(); //当调用Future包装类的set方法时，线程RealData被唤醒，同个getResult()方法  
     }  
   
     @Override  
     public synchronized String getResult() { //会等待RealData构造完成  
         while(!isReady)  
         {  
             try{  
                 wait(); //一直等待，直到RealData被注入  
             }catch (Exception e)  
             {}  
         }  
         return realData.result; //RealData的真实实现  
     }  
 }  
public class FutureMain {
	/*  
	 * 主要负责调用client发起请求，并使用返回的数据  
	 */ 
	public static void main(String[] args) {  
        Client client =new Client();  
        //这里会立即返回结果，因为得到的是FutureData 而非RealData  
        Data data=client.request("name");  
        System.out.println("请求完毕！");  
        System.out.println(data.toString());  
          
        try{  
            //代表对其他业务的处理  
            //在处理过程中，RealData被传剑，充分利用了等待时间  
            //Thread.sleep(2000);  
            System.out.println("我也在被处理哦");  
        }catch(Exception e)  
        {}  
        System.out.println("真实数据："+data.getResult());  
    }  
}  
