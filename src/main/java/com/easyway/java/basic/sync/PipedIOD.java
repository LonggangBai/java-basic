package com.easyway.java.basic.sync;

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;

public class PipedIOD { //程序运行后将sendFile文件的内容拷贝到receiverFile文件中
    public static void main(String args[]){       
        try{//构造读写的管道流对象       
            PipedInputStream pis=new PipedInputStream();       
            PipedOutputStream pos=new PipedOutputStream();       
            //实现关联       
            pos.connect(pis);       
            //构造两个线程，并且启动。           
            new Sender(pos,"c:\\text2.txt").start();           
            new Receiver(pis,"c:\\text3.txt").start();         
        }catch(IOException e){       
            System.out.println("Pipe Error"+ e);       
        }       
    }       
}       
//线程发送       
class Sender extends Thread{           
    PipedOutputStream pos;       
    File file;       
    //构造方法       
    Sender(PipedOutputStream pos, String fileName){       
        this.pos=pos;       
        file=new File(fileName);       
    }          
    //线程运行方法       
    public void run(){          
        try{       
            //读文件内容       
            FileInputStream fs=new FileInputStream(file);       
            int data;       
            while((data=fs.read())!=-1){       
                //写入管道始端       
                pos.write(data);       
            }       
        }       
        catch(IOException e) {       
            System.out.println("Sender Error" +e);       
        }finally {
			if (pos != null) {
				try {
					pos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}       
    }       
}
       
//线程读       
class Receiver extends Thread{       
    PipedInputStream pis;       
    File file;       
    //构造方法       
    Receiver(PipedInputStream pis, String fileName){         
        this.pis=pis;       
        file=new File(fileName);       
    }          
    //线程运行       
    public void run(){          
        try {       
            //写文件流对象       
            FileOutputStream fs=new FileOutputStream(file);       
            int data;       
            //从管道末端读       
            while((data=pis.read())!=-1){
       
                //写入本地文件       
                fs.write(data);       
            }       
        }       
        catch(IOException e){       
            System.out.println("Receiver Error" +e);       
        } finally {
			if (pis != null) {
				try {
					pis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}      
    }       
}