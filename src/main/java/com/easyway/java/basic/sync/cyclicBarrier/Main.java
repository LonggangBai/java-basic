/**
 * Project Name:java-basic
 * File Name:Main.java
 * Package Name:com.easyway.java.basic.sync.cyclicBarrier
 * Date:2015-1-21下午5:07:41
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.sync.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;


/**
 * ClassName:Main <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午5:07:41 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */

// 32. 最后， 实现例子的 main 类，通过创建一个类，名为 Main 并为其添加 main() 方法。
public class Main {

    public static void main(String[] args) {

        // 33. 声明并初始5个常熟来储存应用的参数。
        final int ROWS = 10000;
        final int NUMBERS = 1000;
        final int SEARCH = 5;
        final int PARTICIPANTS = 5;
        final int LINES_PARTICIPANT = 2000;

        // 34. Create a MatrixMock 对象，名为 mock. 它将有 10,000
        // 行，每行1000个元素。现在，你要查找的数字是5。
        MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);

        // 35. 创建 Results 对象，名为 results。它将有 10,000 元素。
        Results results = new Results(ROWS);

        // 36. 创建 Grouper 对象，名为 grouper。
        Grouper grouper = new Grouper(results);

        // 37. 创建 CyclicBarrier 对象，名为 barrier。此对象会等待5个线程。当此线程结束后，它会执行前面创建的
        // Grouper 对象。
        CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);

        // 38. 创建5个 Searcher 对象，5个执行他们的线程，并开始这5个线程。
        Searcher searchers[] = new Searcher[PARTICIPANTS];
        for (int i = 0; i < PARTICIPANTS; i++) {
            searchers[i] =
                    new Searcher(i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT, mock, results, 5,
                        barrier);
            Thread thread = new Thread(searchers[i]);
            thread.start();
        }
        System.out.printf("Main: The main thread has finished.\n");
    }
}
