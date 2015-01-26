package com.easyway.java.basic.thread;
/**
 * <pre>
 * 所以经过两个对比，建议还是使用实现Runnable接口，简单总结一下用后者的好处：
1.  由于是实现接口，就避免了Java单继承带来的局限性，比如我们要把一个继承了一个父类的类放到多线程中，就不能继承Thread了，可以用实现Runnable方式。

2.  我们可以看到，后者可以实现票信息的共享，适合多个相同程序代码的线程去处理同一资源的情况，将线程同程序的代码，数据进行有效的分离，更好的体现了面向对象的设计思想。

3.  健壮性更好，因为那个卖票的类TicketSaleRun的代码能被多个线程共享，就是多个线程执行的代码来自同一个类的实例时，就是多个线程可以共享相同的数据，这点其实与2有点相似。

 

那么我们确定首选实现Runnable接口的方式启动线程，翻了JDK的文档，看到了一些方法，如setDaemon(boolean flag),join(),wait(),sleep(),yield()等方法，当然向其他的方法，
如getName(),isAlive()等见名知意，就不多说了，还有就是一些线程优先级的设置，线程的优先级1到10，数字越大，优先级越高，但是在window系统只，
优先级别并不可靠，所以就一般不管，也就不说了。

setDaemon(boolean flag)方法：

daemon英文意思是后台程序，守护程序，在这里，就是后台(守护)进程，一个线程创建时是前台线程，如果在start()方法调用之前，注意在start()之后设置是没有用的，
如果在之前调用了setDeamon(true)方法，这个线程就编程了后台线程。
 * </pre>
 * @author Administrator
 *
 */
public class TicketSale{
    public static void main(String[]args) {
        TicketSaleRun t = new TicketSaleRun(1000);
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        //这样就可以达到预期效果了，因为这里TicketSaleRun只是创建了一个对象，共享               1000张票的数据
    }
}
class TicketSaleRun implements Runnable {
    private int tickets;
    public TicketSaleRun(int tickets) {
        this.tickets = tickets;
    }
    @Override
    public void run() {
        while (tickets > 0) {
            System.out.println(Thread.currentThread().getName()+"窗口：还有余票-->"
                    + (--tickets) +"张");
        }
    }
}