/**
 * Project Name:java-basic
 * File Name:Participant.java
 * Package Name:com.easyway.java.basic.sync.countdownlatch
 * Date:2015-1-21下午4:57:04
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.sync.countdownlatch;

import java.util.concurrent.TimeUnit;


/**
 * ClassName:Participant <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午4:57:04 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
// 12. 创建 Participant 类并实现 Runnable 接口。这个类表示每个video conference的参与者。
public class Participant implements Runnable {

    // 13. 声明一个私有 Videoconference 属性名为 conference.
    private Videoconference conference;

    // 14. 声明一个私有 String 属性名为 name。
    private String name;


    // 15. 实现类的构造函数，初始化那2个属性。
    public Participant(Videoconference conference, String name) {
        this.conference = conference;
        this.name = name;
    }


    // 16. 实现参与者的run() 方法。
    @Override
    public void run() {

        // 17. 首先，让线程随机休眠一段时间。
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 18. 然后，使用Videoconference 对象的arrive() 方法来表明参与者的到达。
        conference.arrive(name);
    }
}
