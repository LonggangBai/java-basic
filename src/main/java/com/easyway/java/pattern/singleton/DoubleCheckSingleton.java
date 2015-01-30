/**
 * 
 */
package com.easyway.java.pattern.singleton;

/**
 * A journey to write double checked locking of Singleton class in Java.
 * 
 * 
 * 单例类在Java开发者中非常常用，但是它给初级开发者们造成了很多挑战。他们所面对的其中一个关键挑战是，怎样确保单例类的行为是单例？也就是说，无论任何原因，
 * 如何防止单例类有多个实例。在整个应用生命周期中，要保证只有一个单例类的实例被创建，双重检查锁（Double checked locking of
 * Singleton
 * ）是一种实现方法。顾名思义，在双重检查锁中，代码会检查两次单例类是否有已存在的实例，一次加锁一次不加锁，一次确保不会有多个实例被创建。顺便提一下
 * ，在JDK1
 * .5中，Java修复了其内存模型的问题。在JDK1.5之前，这种方法会有问题。本文中，我们将会看到怎样用Java实现双重检查锁的单例类，为什么Java
 * 5之前的版本双重检查锁会有问题
 * ，以及怎么解决这个问题。顺便说一下，这也是重要的面试要点，我曾经在金融业和服务业的公司面试被要求手写双重检查锁实现单例模式、相信我
 * ，这很棘手，除非你清楚理解了你在做什么。你也可以阅读我的完整列表“单例模式设计问题”来更好的准备面试。
 * 
 * 当你指出这段代码在超过一个线程并行被调用的时候会创建多个实例的问题时，他很可能会把整个getInstance()方法设为同步（synchronized），
 * 就像我们展示的第二段示例代码getInstanceTS
 * ()方法一样。尽管这样做到了线程安全，并且解决了多实例问题，但并不高效。在任何调用这个方法的时候，你都需要承受同步带来的性能开销
 * ，然而同步只在第一次调用的时候才被需要，也就是单例类实例创建的时候。这将促使我们使用双重检查锁模式（double checked locking
 * pattern），一种只在临界区代码加锁的方法。程序员称其为双重检查锁，因为会有两次检查 _instance ==
 * null，一次不加锁，另一次在同步块上加锁。这就是使用Java双重检查锁的示例：
 * 
 * 这个方法表面上看起来很完美，你只需要付出一次同步块的开销，但它依然有问题。除非你声明_instance变量时使用了volatile关键字。
 * 没有volatile修饰符
 * ，可能出现Java中的另一个线程看到个初始化了一半的_instance的情况，但使用了volatile变量后，就能保证先行发生关系
 * （happens-before
 * relationship）。对于volatile变量_instance，所有的写（write）都将先行发生于读（read），在Java
 * 5之前不是这样，所以在这之前使用双重检查锁有问题。现在，有了先行发生的保障（happens-before
 * guarantee），你可以安全地假设其会工作良好
 * 。另外，这不是创建线程安全的单例模式的最好方法，你可以使用枚举实现单例模式，这种方法在实例创建时提供了内置的线程安全
 * 。另一种方法是使用静态持有者模式（static holder pattern）。 ClassName: DoubleCheckSingleton <br/>
 * 
 * 这是个用Java创建线程安全单例模式的有争议的方法，使用枚举实现单例类更简单有效。我并不建议你像这样实现单例模式，因为用Java有许多更好的方式。但是，
 * 这个问题有历史意义
 * ，也教授了并发是如何引入一些微妙错误的。正如之前所说，这是面试中非常重要的一点。在去参加任何Java面试之前，要练习手写双重检查锁实现单例类
 * 。这将增强你发现Java程序员们所犯编码错误的洞察力。另外，在现在的测试驱动开发中，单例模式由于难以被模拟其行为而被视为反模式（anti
 * pattern），所以如果你是测试驱动开发的开发者，最好避免使用单例模式。 Function: TODO ADD FUNCTION. <br/>
 * 　注意：在java1.4及以前版本中，很多JVM对于volatile关键字的实现的问题，会导致“双重检查加锁”的失败，因此“双重检查加锁”机制只只能用在java5及以上的版本。
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2015-1-20 下午4:58:42 <br/>
 * 
 * @author Administrator
 * @version
 * @since JDK 1.6
 */
public class DoubleCheckSingleton {

    private volatile static DoubleCheckSingleton _instance;


    /*
     * 3rd version : An implementation of double checked locking of Singleton.
     * Intention is to minimize cost of synchronization and improve performance,
     * by only locking critical section of code, the code which creates instance
     * of Singleton class. By the way this is still broken, if we don't make
     * _instance volatile, as another thread can see a half initialized instance
     * of Singleton.
     */

    public static DoubleCheckSingleton getInstanceDC() {
        if (_instance == null) { // Single Checked
            synchronized (DoubleCheckSingleton.class) {
                if (_instance == null) {// Double checked
                    _instance = new DoubleCheckSingleton();
                }
            }
        }
        return _instance;
    }
}