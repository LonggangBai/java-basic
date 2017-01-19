package com.easyway.java.basic.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 * <pre>
 * 一、并发List

1、Vector

在java核心数据结构（一）——List类族一文中已经提到List类簇中ArrayList和Vector的主要区别就是Vector的大部分实现都支持线程安全，代码也展示了一部分。另外如果使用到ArrayList有需要进行线程安全控制，可以调用Collections.synchronizedList(arrayList)传入ArrayList。

2、CopyOnWriteArrayList

    实现机制：当对象进行读操作时，由于对象未发生改变，无需进行加锁同步；执行写操作，试图改变对象时，则复制该对象先获取该对象的副本，对副本进行修改，最后将副本写回。这种实现方法减少了锁竞争，提高了高并发时读的性能，但写操作有所牺牲。

对比CopyOnWriteArrayList和Vector的get方法实现可以得知：

    public E get(int index) {  
           return get(getArray(), index);  
       }   
       final Object[] getArray() {  
           return array;  
       }    
       //Vector  
    public synchronized E get(int index) {  
           if (index >= elementCount)  
               throw new ArrayIndexOutOfBoundsException(index);  
           return elementData(index);  
       }    

      Vector采用synchronized 关键字，所有的get操作都需要先获取对象锁才能进行。在高并发情况下，大量的锁竞争也会降低系统性能。所以在多读少写的需求中应优先使用CopyOnWriteArrayList，而少读多写时使用vector，做好合理选择就行。

二、并发Set

1、CopyOnWriteArraySet-内部实现完全依赖于CopyOnWriteArrayList，同样也适用于读多写少的并发场合。


    private final CopyOnWriteArrayList<E> al;    
    public int size() {  
           return al.size();  
       }    
    public void clear() {  
           al.clear();  
       }    

       应对并发写时，可以调用Collections.sysnchronizedSet方法得到一个线程安全的set集合。

三、并发Map

1、ConcurrentHashMap

    Map类簇中的HashTable是底层添加了线程安全控制的map数据结构，但在高并发环境下效率低下，因为所有访问HashTable的线程都必须竞争同一把锁，造成大量线程竞争锁。

   而ConcurrentHashMap把数据进行分块加锁，也就是使用锁分离技术，添加多把锁，每一把锁作用于一部分数据，那么当多线程访问不同数据段的数据时，线程间会减少锁竞争，从而可以有效提高并发访问效率，ConcurrentHashMap使用锁分段技术提高并发读写效率。

  

具体查看源码可知，ConcurrentHashMap的get方法都是无锁的，为多线程并发下的性能提供了保证。


    public V get(Object key) {  
           Node<K,V>[] tab; Node<K,V> e, p; int n, eh; K ek;  
           int h = spread(key.hashCode());  
           if ((tab = table) != null && (n = tab.length) > 0 &&  
               (e = tabAt(tab, (n - 1) & h)) != null) {  
               if ((eh = e.hash) == h) {  
                   if ((ek = e.key) == key || (ek != null && key.equals(ek)))  
                       return e.val;  
               }  
               else if (eh < 0)  
                   return (p = e.find(h, key)) != null ? p.val : null;  
               while ((e = e.next) != null) {  
                   if (e.hash == h &&  
                       ((ek = e.key) == key || (ek != null && key.equals(ek))))  
                       return e.val;  
               }  
           }  
           return null;  
       }    
       static final int spread(int h) {  
           return (h ^ (h >>> 16)) & HASH_BITS;  
       }    

        这也警示开发人员，在高并发下，既要保证线程安全，又需要合理是用安全控制技术，例如添加关键字、各种Lock类型。否则造成线程恶性竞争锁，或者死锁现象，程序最终溃堤。

2、Collections.synchronizedMap(map)

对于未进行线程安全控制的map结果，也可通过Collections类提供的synchronizedMap进行控制。

四、并发Queue

1、ConcurrentLinkedQueue-高性能队列

ConcurrentLinkedQueue是适用于高并发下的队列类型。通过无锁的方式实现。

2、BlockingQueue-阻塞队列

BlockingQueue的应用场景主要是用于线程间数据共享，在生产者-消费者模式中（java线程深度解析（五）——并发模型（生产者-消费者）文中有介绍），作为一个数据仓库，生产者往里put数据，消费者poll数据。当队列被装满了，当生产者在试图往里放数据时，就会被阻塞等待。这里就涉及到相关的任务处理策略和拒绝服务策略。在上文中都有具体讲述。 

五、总结

并发的数据结构主要就是这些，List的 Vector（synchronized 关键字，读少写多） 和CopyOnWriteArrayList（复制副本，读多写少），Set的CopyOnWriteArraySet（特点同CopyOnWriteArrayList），Map的 ConcurrentHashMap（锁分离，减少锁竞争），还有队列Queue的ConcurrentLinkedQueue（并发），BlockingQueue（数据共享）；另外还有Collections提供的一系列synchroniz...方法用于对普通数据结果进行线程安全控制。


 * 生产者 消费者  LinkedBlockingQueue 实现 
 * 该列队取出对象时，如果列队为空，就一直wait等待。直到列队非空。
 * 存入对象时，如果列队已满，就一直wait等待。直到列队非满
 * 
 * </pre>
 */
public class LinkedBlockingQueueTest {

	public int MAX_SIZE;

	private BlockingQueue<String> queue;

	public LinkedBlockingQueueTest(int max_size) {
		this.MAX_SIZE = max_size;
		queue = new LinkedBlockingQueue<String>(MAX_SIZE);
	}

	public void buildProducer() {
		new Thread(new Producer(queue)).start();
	}

	public void buildConsumer() {
		new Thread(new Consumer(queue)).start();
	}

	/* 生产者 */
	class Producer implements Runnable {
		BlockingQueue<String> queue;

		public Producer(BlockingQueue<String> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			int i = 0;
			while (true) {
				try {
					System.out.println("即将存");
					queue.put("包子" + ++i); // 存
					System.out.println("生产了一个包子 ，包子数量：" + queue.size());
					if (queue.size() == MAX_SIZE) {
						System.out.println("已满");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/* 消费者 */
	class Consumer implements Runnable {

		BlockingQueue<String> queue;

		public Consumer(BlockingQueue<String> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			try {
				System.out.println("即将取");
				queue.take(); // 取
				if (queue.size() == 0) {
					System.out.println("取出了一个包子 ，包子数量：" + queue.size());
					System.out.println("已空");
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		LinkedBlockingQueueTest pac = new LinkedBlockingQueueTest(5);
		pac.buildProducer();
		pac.buildConsumer();
	}

}
