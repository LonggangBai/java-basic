/**
 * Project Name:java-basic
 * File Name:Adjustable.java
 * Package Name:com.easyway.java.basic.clazz.callback
 * Date:2015-3-23上午9:46:14
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.clazz.callback;
/**
 * ClassName:Adjustable <br/>
 * Function: 回调机制Callback <br/>
 * Reason:	 回调类实质上是指一个类尽管实际上实现了某种功能，但是没有直接提供相应的接口，
 * 客户类可以通过这个类的内部类的接口来获得这种功能，这个内部类本身并没有提供真正的实现，仅仅调用外部类的实现，
 * 可见回调充分发挥了内部类所具有的访问外部类的实现细节的优势。 <br/>
 * Date:     2015-3-23 上午9:46:14 <br/>
 * @author   longgangbai
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
interface Adjustable{
    public void adjust(int temperature);
}
class Base {
    private int speed;
    public void adjust(int speed){
        this.speed=speed;
    }
}
 class Sub extends Base {
    private int temperature;
    public void adjustTemperature(int temperature){
        this.temperature=temperature;
    }
    private class Closure implements Adjustable{

        @Override
        public void adjust(int temperature) {
            adjustTemperature(temperature);
        }
    }
    
    public Adjustable getCallBackReference(){
        return new Closure();
    }
}

public class AdjustableTest {

}

