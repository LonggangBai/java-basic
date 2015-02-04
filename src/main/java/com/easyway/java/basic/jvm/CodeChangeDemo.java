package com.easyway.java.basic.jvm;

/**
 * 
 * <pre>
 * （一）java内存区域概况
 * jvm运行java程序时把所管理的内存分成几个部分：方法区、java栈、本地方法栈、java堆、pc程序计数器。
 * class字节码装载解析后，在多线程环境中，方法区和java堆数据共享，每个线程自带pc程序计数器和java栈，栈帧中包含方法的所有状态（局部变量、传参、返回值、运算中间结果等）。对共享数据需要考虑多线程并发问题。
 * 更详细内容可参考《深入理解JVM虚拟机》。
 * 
 * （二）计算机系统原型
 * 1.内存和处理器速度不同，缓存的出现，导致数据不同步
 * 多线程都有缓存备份，回写可能会导致数据错误
 * 2.处理器和编译器对代码和指令的优化，导致运算乱序
 * 只要不影响运算结果，处理器可以调整代码执行顺序，多线程操作中会导致数据错误
 * 
 * （三）JMM概况
 * JMM（java momery model）提供了线程进行通讯时，确保互斥性和可见性。
 * （1）解决内存可见性问题
 * 
 * 共享数据放主内存中，每个线程有自己的工作内存。
 * 线程A读取数据时，先从主内存拷贝数据到工作内存，进行运算后再从工作内存回写到主内存，线程间无法相互访问对方的数据，通过主内存实现通讯。
 * JMM可以控制指定共享数据实现内存可见性，写入主内存时，同步更新其他线程中的变量值，如volatile变量的使用。
 * （2）禁止重排序
 * 编译器优化代码，不改变运算结果的情况下，可以实现代码重排序；处理器执行指令，不改变运算结果的情况下，可以实现指令重排序。
 * JMM可以禁止编译器代码重排序和处理器执行指令重排序（通过加入内层屏蔽）。
 * 多线程并发问题代码剖析
 * 
 * 运行结果：
 * ChangeThread start
 * GetChangeThread start
 * change success
 * ChangeThread start
 * GetChangeThread start
 * change success
 * ChangeThread start
 * GetChangeThread start
 * change no success
 * 进程退出可能原因：
 * 1.线程ChangeThread中代码顺序调整，1和2调换，执行顺序1-3-2
 * 2.线程ChangeThread执行完，工作内存中的isChange没有回写回主内存，导致3中isChange不是最新值。
 * 
 * 参考文献：
 * 1.《深入理解JVM虚拟机》
 * 2.http://ifeve.com/java-memory-model-1/
 * 
 * 之前总是觉得掌握了Java的垃圾回收机制，但稍微讨论下，就发现自己了解的不够全面，现在重新整理一下，感觉还是不错： 
 * 
 * Java 垃圾回收
 * 关于finalizer(): 
 * 用于清理非正常开启的内存，一般情况下，只有你用native code时候，打开了内存区域，然后在finalizer方法里面进行关闭清理，但一般都不推荐这么做。打开了内存，不用就自己写方法关闭， finalize只用来检测bug，非正常的关闭，需要给出warning.
 * System.gc()的时候，会调用所有需要清理的对象的finalize方法，但这个时候并不会马上就开始清理， 需要等到jvm认为能够开始清理的时候，才会清理。过后，会将这些调用过finalize方法的对象全部清理掉。
 * System.gc的作用，在执行的时候， 会调用所有未清理对象的finalize方法， 这个时候并不会进行清理。 真正什么时候清理，由jvm自行决定。  
 * 如果没有gc, jvm清理的时候，它会先调用其finalize方法，然后再清理，  但不会清理所有的对象。 
 * jvm内存的使用方式： 
 * jvm在堆里面分配内存给对象，但是其速度很快。  在堆栈里面放置引用。 
 * jvm占据的内存会采用带状，分块的模式。一条很长的内存带，带子上分很多块。 
 * 每次分配对象内存的时候，就取一个块，在带子上移动一格，分配给这个对象。 相比较c++的管理，减少了查询可用空间的步骤和时间。 
 * 引用计数的方法是用来确定死对象，有一个引用就计数+1, 但这样效率低下，而且互相引用的情况下失效。 
 * 另外一种方法： 从堆栈的引用开始查， 一层层查， 有活的就标记。  剩下，没有标记的，全是死对象 
 * 
 * Jvm采用的自适应的方式来清理内存死对象： 
 * stop and copy:  停止复制 
 * 暂停程序， 在带子里面找到相应的块， 如果这些块的死对象较多， 就把活对象考到其他空闲的块， 然后把死对象回收，标记这个块为空闲。 
 * 采用块的目的在于，减少对象拷贝的次数， 比如，有些对象很大，占据了一个块，或者多个块， 那么这个对象是不需要被来回拷贝的。 
 * stop and sweep: 停止清扫 
 * 暂停程序， 找到死对象，清理掉。 但是不整理。   当确实很多碎片的时候，再启动stop and copy， 压缩，解放内存。
 * </pre>
 * 
 * 
 * @author peter_wang
 * @create-time 2015-1-8 下午3:06:02
 */
public class CodeChangeDemo {

    private static boolean isChange = false;

    private static class ChangeThread extends Thread {
        public void run() {
            System.out.println("ChangeThread start"); // 1
            isChange = true; // 2
        };
    };

    private static class GetChangeThread extends Thread {
        public void run() {
            System.out.println("GetChangeThread start");
            if (isChange) { // 3
                isChange = false;
                System.out.println("change success");
            }
            else {
                System.out.println("change no success");
                System.exit(0);
            }
        };
    };


    /**
     * @param args
     */
    public static void main(String[] args) {
        while (true) {
            ChangeThread changeThread = new ChangeThread();
            GetChangeThread getChangeThread = new GetChangeThread();
            changeThread.start();
            getChangeThread.start();
            // 防止线程过多，等执行完上面俩线程再继续
            while (Thread.activeCount() > 1) {

            }
        }
    }

}