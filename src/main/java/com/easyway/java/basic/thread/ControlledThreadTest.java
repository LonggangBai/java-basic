/**
 * Project Name:java-basic
 * File Name:ControlledThread.java
 * Package Name:com.easyway.java.basic.thread
 * Date:2015-3-24上午10:13:23
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.thread;

/**
 * ClassName:ControlledThread <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-24 上午10:13:23 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
class ControlledThread extends Thread {
    public static final int SUSP = 1;
    public static final int STOP = 2;
    public static final int RUN = 0;
    private int state = RUN;


    public synchronized void setState(int state) {
        this.state = state;
        if (state == RUN) {
            notify();
        }
    }


    public synchronized boolean checkState() {
        while (state == SUSP) {
            try {
                System.out.println(Thread.currentThread().getName() + "....");
                wait();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (state == STOP) {
            return false;
        }
        return true;
    }
}

class ControlledThreadTest extends ControlledThread{
    private int count;
    public void run(){
        while(true){
            synchronized(this){
                count++;
                System.out.println(Thread.currentThread().getName()+" : run "+count +" times");
            }
            if(!checkState()){
                System.out.println(Thread.currentThread().getName()+" : stop ");
                break;
            }
        }
    }
    public synchronized int getCount(){
        return count;
    }
    
    public synchronized void reset(){
        count=0;
        System.out.println(Thread.currentThread().getName()+" : reset ");
    }
    public static void main(String[] args) {
        ControlledThreadTest machine=new ControlledThreadTest();
        machine.start();
        for (int i = 0; i < 200; i++) {
            if(machine.getCount()>5){
                machine.setState(ControlledThread.SUSP);//线程暂停
                yield();
                machine.reset();
                machine.setState(ControlledThread.RUN); //恢复运行
            }
            yield();
        }
        machine.setState(ControlledThread.STOP);//machine线程终止运行
    }

}
