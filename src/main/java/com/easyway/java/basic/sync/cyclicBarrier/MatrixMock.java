/**
 * Project Name:java-basic
 * File Name:MatrixMock.java
 * Package Name:com.easyway.java.basic.sync.cyclicBarrier
 * Date:2015-1-21下午5:04:40
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.sync.cyclicBarrier;

import java.util.Random;


/**
 * 
 * <pre>
 * 线程同步工具（四）在同一个点同步任务
 * 声明：本文是《 Java 7 Concurrency Cookbook 》的第三章， 作者： Javier Fernández González 译者：郑玉婷
 * 
 * 在同一个点同步任务
 * Java 并发 API 提供了可以允许2个或多个线程在在一个确定点的同步应用。它是 CyclicBarrier 类。此类与在此章节的等待多个并发事件完成指南中的 CountDownLatch 类相似，但是它有一些特殊性让它成为更强大的类。
 * 
 * CyclicBarrier 类有一个整数初始值，此值表示将在同一点同步的线程数量。当其中一个线程到达确定点，它会调用await() 方法来等待其他线程。当线程调用这个方法，CyclicBarrier阻塞线程进入休眠直到其他线程到达。当最后一个线程调用CyclicBarrier 类的await() 方法，它唤醒所有等待的线程并继续执行它们的任务。
 * 
 * CyclicBarrier 类有个有趣的优势是，你可以传递一个外加的 Runnable 对象作为初始参数，并且当全部线程都到达同一个点时，CyclicBarrier类 会把这个对象当做线程来执行。此特点让这个类在使用 divide 和 conquer 编程技术时，可以充分发挥任务的并行性，
 * 
 * 在这个指南，你将学习如何使用 CyclicBarrier 类来让一组线程在一个确定点同步。你也将使用 Runnable 对象，它将会在全部线程都到达确定点后被执行。在这个例子里，你将在数字矩阵中查找一个数字。矩阵会被分成多个子集（使用divide 和 conquer 技术），所以每个线程会在一个子集中查找那个数字。一旦全部行程运行结束，会有一个最终任务来统一他们的结果。
 * 
 * 准备
 * 
 * 指南中的例子是使用Eclipse IDE 来实现的。如果你使用Eclipse 或者其他的IDE，例如NetBeans, 打开并创建一个新的java项目。
 * 
 * 怎么做呢…
 * 
 * 按照这些步骤来实现下面的例子：:
 * 
 * 
 * 
 * 它是怎么工作的…
 * 
 * 以下裁图是例子的运行结果：
 * 
 * 
 * 
 * 例子中解决的问题比较简单。我们有一个很大的随机的整数矩阵，然后你想知道这矩阵里面某个数字出现的次数。为了更好的执行，我们使用了 divide 和 conquer 技术。我们 divide 矩阵成5个子集，然后在每个子集里使用一个线程来查找数字。这些线程是 Searcher 类的对象。
 * 
 * 我们使用 CyclicBarrier 对象来同步5个线程的完成，并执行 Grouper 任务处理个别结果，最后计算最终结果。
 * 
 * 如我们之前提到的，CyclicBarrier 类有一个内部计数器控制到达同步点的线程数量。每次线程到达同步点，它调用 await() 方法告知 CyclicBarrier 对象到达同步点了。CyclicBarrier 把线程放入睡眠状态直到全部的线程都到达他们的同步点。
 * 
 * 当全部的线程都到达他们的同步点，CyclicBarrier 对象叫醒全部正在 await() 方法中等待的线程们，然后，选择性的，为CyclicBarrier的构造函数 传递的 Runnable 对象（例子里，是 Grouper 对象）创建新的线程执行外加任务。
 * 
 * 更多…
 * 
 * CyclicBarrier 类有另一个版本的 await() 方法:
 * 
 * await(long time, TimeUnit unit): 线程会一直休眠直到被中断；内部计数器到达0，或者特定的时间过去了。TimeUnit类有多种常量： DAYS, HOURS, MICROSECONDS, MILLISECONDS, MINUTES, NANOSECONDS, and SECONDS.
 * 此类也提供了 getNumberWaiting() 方法，返回被 await() 方法阻塞的线程数，还有 getParties() 方法，返回将与CyclicBarrier同步的任务数。
 * 
 * 重置 CyclicBarrier 对象
 * CyclicBarrier 类与CountDownLatch有一些共同点，但是也有一些不同。最主要的不同是，CyclicBarrier对象可以重置到它的初始状态，重新分配新的值给内部计数器，即使它已经被初始过了。
 * 
 * 可以使用 CyclicBarrier的reset() 方法来进行重置操作。当这个方法被调用后，全部的正在await() 方法里等待的线程接收到一个 BrokenBarrierException 异常。此异常在例子中已经用打印stack trace处理了，但是在一个更复制的应用，它可以执行一些其他操作，例如重新开始执行或者在中断点恢复操作。
 * 
 * 破坏 CyclicBarrier 对象 
 * CyclicBarrier 对象可能处于一个特殊的状态，称为 broken。当多个线程正在 await() 方法中等待时，其中一个被中断了，此线程会收到 InterruptedException 异常，但是其他正在等待的线程将收到 BrokenBarrierException 异常，并且 CyclicBarrier 会被置于broken 状态中。
 * 
 * CyclicBarrier 类提供了isBroken() 方法，如果对象在 broken 状态，返回true，否则返回false。
 * 
 * 参见
 * </pre>
 * 
 * ClassName:MatrixMock <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午5:04:40 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
// 1. 我们从实现2个辅助类开始。首先，创建一个类名为 MatrixMock。此类随机生成一个在1-10之间的 数字矩阵，我们将从中查找数字。
public class MatrixMock {

    // 2. 声明私有 int matrix，名为 data。
    private int data[][];


    // 3. 实现类的构造函数。此构造函数将接收矩阵的行数，行的长度，和我们将要查找的数字作为参数。3个参数全部int 类型。
    public MatrixMock(int size, int length, int number) {

        // 4. 初始化构造函数将使用的变量和对象。
        int counter = 0;
        data = new int[size][length];
        Random random = new Random();

        // 5. 用随机数字填充矩阵。每生成一个数字就与要查找的数字对比，如果相等，就增加counter值。
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt(10);
                if (data[i][j] == number) {
                    counter++;
                }
            }
        }

        // 6. 最后，在操控台打印一条信息，表示查找的数字在生成的矩阵里的出现次数。此信息是用来检查线程们获得的正确结果的。
        System.out.printf("Mock: There are %d ocurrences of number in generated data.\n", counter, number); // 译者注：把字符串里的number改为%d.
    }


    // 7. 实现 getRow() 方法。此方法接收一个 int为参数，是矩阵的行数。返回行数如果存在，否则返回null。
    public int[] getRow(int row) {
        if ((row >= 0) && (row < data.length)) {
            return data[row];
        }
        return null;
    }
}
