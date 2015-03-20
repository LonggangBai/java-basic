/**
 * Project Name:java-basic
 * File Name:ResourceMap.java
 * Package Name:com.easyway.java.basic.collection
 * Date:2015-3-20上午10:15:13
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.collection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;


/**
 * ClassName:ResourceMap <br/>
 * Function:
 * 
 * <pre>
 *  ConcurrentHashMap是由Segment数组结构和HashEntry数组结构组成。Segment是一种可重入锁ReentrantLock，
 * 在ConcurrentHashMap里扮演锁的角色，HashEntry则用于存储键值对数据。一个ConcurrentHashMap里包含一个Segment数组，
 * Segment的结构和HashMap类似，是一种数组和链表结构， 一个Segment里包含一个HashEntry数组，每个HashEntry是一个链表结构
 * 的元素， 每个Segment守护者一个HashEntry数组里的元素,当对HashEntry数组的数据进行修改时，必须首先获得它对应的Segment锁。
 *  ConcurrentHashMap 类中包含两个静态内部类 HashEntry 和 Segment。HashEntry 用来封装映射表的键 / 值对；Segment 
 *  用来充当锁的角色，每个 Segment 对象守护整个散列映射表的若干个桶。每个桶是由若干个 HashEntry 对象链接起来的链表。一个 
 *  ConcurrentHashMap 实例中包含由若干个 Segment 对象组成的数组。
 *  Hashtable是JDK 5之前Map唯一线程安全的内置实现（Collections.synchronizedMap不算）。Hashtable继承的是Dictionary
 *  （Hashtable是其唯一公开的子类），并不继承AbstractMap或者HashMap。尽管Hashtable和HashMap的结构非常类似，但是他们之间并没有多大联系。
 * ConcurrentHashMap是HashMap的线程安全版本，ConcurrentSkipListMap是TreeMap的线程安全版本。
 * 最终可用的线程安全版本Map实现是ConcurrentHashMap/ConcurrentSkipListMap/Hashtable/Properties四个，但是Hashtable是
 * 过时的类库，因此如果可以的应该尽可能的使用ConcurrentHashMap和ConcurrentSkipListMap。
 * 二、concurrentHashMap的结构
 * 
 * 我们通过ConcurrentHashMap的类图来分析ConcurrentHashMap的结构。
 * 
 *  
 * 
 *  
 * 
 * ConcurrentHashMap是由Segment数组结构和HashEntry数组结构组成。Segment是一种可重入锁ReentrantLock，在ConcurrentHashMap
 * 里扮演锁的角色，HashEntry则用于存储键值对数据。一个ConcurrentHashMap里包含一个Segment数组，Segment的结构和HashMap类似，是一种
 * 数组和链表结构， 一个Segment里包含一个HashEntry数组，每个HashEntry是一个链表结构的元素， 每个Segment守护者一个HashEntry数组里的
 * 元素,当对HashEntry数组的数据进行修改时，必须首先获得它对应的Segment锁。
 * 
 *  
 * 
 *  
 * 
 * ConcurrentHashMap 类中包含两个静态内部类 HashEntry 和 Segment。HashEntry 用来封装映射表的键 / 值对；Segment 用来充当锁的角色，
 * 每个 Segment 对象守护整个散列映射表的若干个桶。每个桶是由若干个 HashEntry 对象链接起来的链表。一个 ConcurrentHashMap 实例中包含由若
 * 干个 Segment 对象组成的数组。
 * 
 * 三、ConcurrentHashMap实现原理
 * 
 * 锁分离 (Lock Stripping)
 *  
 * 比如HashTable是一个过时的容器类，通过使用synchronized来保证线程安全，在线程竞争激烈的情况下HashTable的效率非常低下。原因是所有访问
 * HashTable的线程都必须竞争同一把锁。那假如容器里有多把锁，每一把锁用于锁容器其中一部分数据，那么当多线程访问容器里不同数据段的数据时，线程
 * 间就不会存在锁竞争，从而可以有效的提高并发访问效率，这就是ConcurrentHashMap所使用的锁分段技术。
 * 
 * ConcurrentHashMap内部使用段(Segment)来表示这些不同的部分，每个段其实就是一个小的hash table，它们有自己的锁。只要多个修改操作发生
 * 在不同的段上，它们就可以并发进行。同样当一个线程占用锁访问其中一个段数据的时候，其他段的数据也能被其他线程访问。
 *  
 * ConcurrentHashMap有些方法需要跨段，比如size()和containsValue()，它们可能需要锁定整个表而而不仅仅是某个段，这需要按顺序锁定所有段，
 * 操作完毕后，又按顺序释放所有段的锁。这里“按顺序”是很重要的，否则极有可能出现死锁，在ConcurrentHashMap内部，段数组是final的，并且其成
 * 员变量实际上也是final的，但是，仅仅是将数组声明为final的并不保证数组成员也是final的，这需要实现上的保证。这可以确保不会出现死锁，因为获
 * 得锁的顺序是固定的。不变性是多线程编程占有很重要的地位，下面还要谈到。
 *  
 * [java]  
 * The segments, each of which is a specialized hash table 
 * final Segment<K,V>[] segments;   
 * 
 *  不变(Immutable)和易变(Volatile)
 *  
 * ConcurrentHashMap完全允许多个读操作并发进行，读操作并不需要加锁。如果使用传统的技术，如HashMap中的实现，如果允许可以在hash链的中间添加
 * 或删除元素，读操作不加锁将得到不一致的数据。ConcurrentHashMap实现技术是保证HashEntry几乎是不可变的。HashEntry代表每个hash链中的一个
 * 节点，其结构如下所示：
 *  
 * [java] 
 * static final class HashEntry<K,V> {   
 *     final K key;   
 *     final int hash;   
 *     volatile V value;   
 *     final HashEntry<K,V> next;   
 * } 
 * 
 *  
 * 可以看到除了value不是final的，其它值都是final的，这意味着不能从hash链的中间或尾部添加或删除节点，因为这需要修改next引用值，所有的节点的
 * 修改只能从头部开始。对于put操作，可以一律添加到Hash链的头部。但是对于remove操作，可能需要从中间删除一个节点，这就需要将要删除节点的前面所有
 * 节点整个复制一遍，最后一个节点指向要删除结点的下一个结点。这在讲解删除操作时还会详述。为了确保读操作能够看到最新的值，将value设置成volatile
 * ，这避免了加锁。
 * 
 * 四、ConcurrentHashMap具体实现
 * ConcurrentHashMap的初始化
 * 
 * ConcurrentHashMap初始化方法是通过initialCapacity，loadFactor, concurrencyLevel几个参数来初始化segments数组，段偏移量
 * segmentShift，段掩码segmentMask和每个segment里的HashEntry数组 。
 * 
 * 初始化segments数组。让我们来看一下初始化segmentShift，segmentMask和segments数组的源代码。
 * 
 * 
 * [java] 
 * if (concurrencyLevel > MAX_SEGMENTS) 
 *     concurrencyLevel = MAX_SEGMENTS; 
 *  
 * // Find power-of-two sizes best matching arguments 
 * int sshift = 0; 
 * int ssize = 1; 
 * while (ssize < concurrencyLevel) { 
 *     ++sshift; 
 *     ssize <<= 1; 
 * } 
 * segmentShift = 32 - sshift; 
 * segmentMask = ssize - 1; 
 * this.segments = Segment.newArray(ssize); 
 * 
 * 
 * 由上面的代码可知segments数组的长度ssize通过concurrencyLevel计算得出。为了能通过按位与的哈希算法来定位segments数组的索引，必须保证
 * segments数组的长度是2的N次方（power-of-two size），所以必须计算出一个是大于或等于concurrencyLevel的最小的2的N次方值来作为segments
 * 数组的长度。假如concurrencyLevel等于14，15或16，ssize都会等于16，即容器里锁的个数也是16。注意concurrencyLevel的最大大小是65535，
 * 意味着segments数组的长度最大为65536，对应的二进制是16位。
 * 
 *  
 * 
 * 初始化segmentShift和segmentMask。这两个全局变量在定位segment时的哈希算法里需要使用，sshift等于ssize从1向左移位的次数，在默认情况下
 * concurrencyLevel等于16，1需要向左移位移动4次，所以sshift等于4。segmentShift用于定位参与hash运算的位数，
 * segmentShift等于32减sshift，所以等于28，这里之所以用32是因为ConcurrentHashMap里的hash()方法输出的最大数是32位的，后面的
 * 测试中我们可以看到这点。segmentMask是哈希运算的掩码，等于ssize减1，即15，掩码的二进制各个位的值都是1。因为ssize的最大长度是65536，
 * 所以segmentShift最大值是16，segmentMask最大值是65535，对应的二进制是16位，每个位都是1。
 * 
 * 初始化每个Segment。输入参数initialCapacity是ConcurrentHashMap的初始化容量，loadfactor是每个segment的负载因子，在构造方法
 * 里需要通过这两个参数来初始化数组中的每个segment。
 * 
 *  
 * 
 * 上面代码中的变量cap就是segment里HashEntry数组的长度，它等于initialCapacity除以ssize的倍数c，如果c大于1，
 * 就会取大于等于c的2的N次方值，所以cap不是1，就是2的N次方。segment的容量threshold＝(int)cap*loadFactor，
 * 默认情况下initialCapacity等于16，loadfactor等于0.75，通过运算cap等于1，threshold等于零。
 * 
 *  
 * 
 * [java] 
 * if (initialCapacity > MAXIMUM_CAPACITY) 
 *     initialCapacity = MAXIMUM_CAPACITY; 
 * int c = initialCapacity / ssize; 
 * if (c * ssize < initialCapacity) 
 *     ++c; 
 * int cap = 1; 
 * while (cap < c) 
 *     cap <<= 1; 
 * for (int i = 0; i < this.segments.length; ++i) 
 *     this.segments[i] = new Segment<K,V>(cap, loadFactor); 
 * 
 * 定位Segment
 * 
 * 既然ConcurrentHashMap使用分段锁Segment来保护不同段的数据，那么在插入和获取元素的时候，必须先通过哈希算法定位到Segment。可以看到
 * ConcurrentHashMap会首先使用Wang/Jenkins hash的变种算法对元素的hashCode进行一次再哈希。
 * 
 * 
 * [java]
 * private static int hash(int h) { 
 *         h += (h << 15) ^ 0xffffcd7d; 
 *         h ^= (h >>> 10); 
 *         h += (h << 3); 
 *         h ^= (h >>> 6); 
 *         h += (h << 2) + (h << 14); 
 *         return h ^ (h >>> 16); 
 *     } 
 * 
 * 
 * 之所以进行再哈希，其目的是为了减少哈希冲突，使元素能够均匀的分布在不同的Segment上，从而提高容器的存取效率。假如哈希的质量差到极点，那么所有
 * 的元素都在一个Segment中，不仅存取元素缓慢，分段锁也会失去意义。我做了一个测试，不通过再哈希而直接执行哈希计算。
 * 
 * 
 * [java] 
 * System.out.println(Integer.parseInt("0001111", 2) & 15); 
 * System.out.println(Integer.parseInt("0011111", 2) & 15); 
 * System.out.println(Integer.parseInt("0111111", 2) & 15); 
 * System.out.println(Integer.parseInt("1111111", 2) & 15); 
 * 
 * 计算后输出的哈希值全是15，通过这个例子可以发现如果不进行再哈希，哈希冲突会非常严重，因为只要低位一样，无论高位是什么数，其哈希值总是一样。
 * 我们再把上面的二进制数据进行再哈希后结果如下，为了方便阅读，不足32位的高位补了0，每隔四位用竖线分割下。
 * 
 * 
 * [java] 
 * 0100｜0111｜0110｜0111｜1101｜1010｜0100｜1110 
 * 1111｜0111｜0100｜0011｜0000｜0001｜1011｜1000 
 * 0111｜0111｜0110｜1001｜0100｜0110｜0011｜1110 
 * 1000｜0011｜0000｜0000｜1100｜1000｜0001｜1010 
 * 
 * 可以发现每一位的数据都散列开了，通过这种再哈希能让数字的每一位都能参加到哈希运算当中，从而减少哈希冲突。ConcurrentHashMap通过以下哈希算法定位segment。
 * 
 * 
 * [java] 
 * final Segment<K,V> segmentFor(int hash) { 
 *         return segments[(hash >>> segmentShift) & segmentMask]; 
 *     } 
 * 
 * 默认情况下segmentShift为28，segmentMask为15，再哈希后的数最大是32位二进制数据，向右无符号移动28位，意思是让高4位参与到hash运算中，
 *  (hash >>> segmentShift) & segmentMask的运算结果分别是4，15，7和8，可以看到hash值没有发生冲突。
 * 
 * 
 * ConcurrentHashMap的get操作
 * 
 * 前面提到过ConcurrentHashMap的get操作是不用加锁的，我们这里看一下其实现：
 * 
 * [java] 
 * public V get(Object key) { 
 *     int hash = hash(key.hashCode()); 
 *     return segmentFor(hash).get(key, hash); 
 * } 
 * 
 * 看第三行，segmentFor这个函数用于确定操作应该在哪一个segment中进行，几乎对ConcurrentHashMap的所有操作都需要用到这个函数，我们看下这个函数的实现：
 * 
 * [java]
 * final Segment<K,V> segmentFor(int hash) { 
 *     return segments[(hash >>> segmentShift) & segmentMask]; 
 * } 
 * 
 * 这个函数用了位操作来确定Segment，根据传入的hash值向右无符号右移segmentShift位，然后和segmentMask进行与操作，结合我们之前说的
 * segmentShift和segmentMask的值，就可以得出以下结论：假设Segment的数量是2的n次方，根据元素的hash值的高n位就可以确定元素到底在哪一个Segment中。
 * 
 * 在确定了需要在哪一个segment中进行操作以后，接下来的事情就是调用对应的Segment的get方法：
 * 
 * [java] 
 * V get(Object key, int hash) { 
 *     if (count != 0) { // read-volatile 
 *         HashEntry<K,V> e = getFirst(hash); 
 *         while (e != null) { 
 *             if (e.hash == hash && key.equals(e.key)) { 
 *                 V v = e.value; 
 *                 if (v != null) 
 *                     return v; 
 *                 return readValueUnderLock(e); // recheck 
 *             } 
 *             e = e.next; 
 *         } 
 *     } 
 *     return null; 
 * } 
 * 
 * 
 * 先看第二行代码，这里对count进行了一次判断，其中count表示Segment中元素的数量，我们可以来看一下count的定义：
 * 
 * 
 * transient volatile int count; 
 * 
 * 可以看到count是volatile的，实际上这里里面利用了volatile的语义：
 * 
 *  
 * 
 * 对volatile字段的写入操作happens-before于每一个后续的同一个字段的读操作。
 * 
 *  
 * 
 * 因为实际上put、remove等操作也会更新count的值，所以当竞争发生的时候，volatile的语义可以保证写操作在读操作之前，也就保证了写操作对后续的
 * 读操作都是可见的，这样后面get的后续操作就可以拿到完整的元素内容。
 * 
 * 然后，在第三行，调用了getFirst()来取得链表的头部：
 * 
 * HashEntry<K,V> getFirst(int hash) { 
 *     HashEntry<K,V>[] tab = table; 
 *     return tab[hash & (tab.length - 1)]; 
 * } 
 * 
 * 同样，这里也是用位操作来确定链表的头部，hash值和HashTable的长度减一做与操作，最后的结果就是hash值的低n位，其中n是HashTable的长度以2为底的结果。
 * 
 * 在确定了链表的头部以后，就可以对整个链表进行遍历，看第4行，取出key对应的value的值，如果拿出的value的值是null，则可能这个key，value对正在put
 * 的过程中，如果出现这种情况，那么就加锁来保证取出的value是完整的，如果不是null，则直接返回value。
 * 
 *  
 * 
 * ConcurrentHashMap的put操作
 * 
 * 看完了get操作，再看下put操作，put操作的前面也是确定Segment的过程，这里不再赘述，直接看关键的segment的put方法：
 * 
 * 
 * V put(K key, int hash, V value, boolean onlyIfAbsent) { 
 *     lock(); 
 *     try { 
 *         int c = count; 
 *         if (c++ > threshold) // ensure capacity 
 *             rehash(); 
 *         HashEntry<K,V>[] tab = table; 
 *         int index = hash & (tab.length - 1); 
 *         HashEntry<K,V> first = tab[index]; 
 *         HashEntry<K,V> e = first; 
 *         while (e != null && (e.hash != hash || !key.equals(e.key))) 
 *             e = e.next; 
 *   
 *         V oldValue; 
 *         if (e != null) { 
 *             oldValue = e.value; 
 *             if (!onlyIfAbsent) 
 *                 e.value = value; 
 *         } 
 *         else { 
 *             oldValue = null; 
 *             ++modCount; 
 *             tab[index] = new HashEntry<K,V>(key, hash, first, value); 
 *             count = c; // write-volatile 
 *         } 
 *         return oldValue; 
 *     } finally { 
 *         unlock(); 
 *     } 
 * } 
 * 
 * 首先对Segment的put操作是加锁完成的，然后在第五行，如果Segment中元素的数量超过了阈值（由构造函数中的loadFactor算出）这需要进行对Segment扩容
 * ，并且要进行rehash，关于rehash的过程大家可以自己去了解，这里不详细讲了。
 * 
 * 第8和第9行的操作就是getFirst的过程，确定链表头部的位置。
 * 
 * 第11行这里的这个while循环是在链表中寻找和要put的元素相同key的元素，如果找到，就直接更新更新key的value，如果没有找到，则进入21行这里，生成一个
 * 新的HashEntry并且把它加到整个Segment的头部，然后再更新count的值。
 * 
 * 
 * ConcurrentHashMap的remove操作
 * 
 * Remove操作的前面一部分和前面的get和put操作一样，都是定位Segment的过程，然后再调用Segment的remove方法：
 * 
 * 
 * V remove(Object key, int hash, Object value) { 
 *     lock(); 
 *     try { 
 *         int c = count - 1; 
 *         HashEntry<K,V>[] tab = table; 
 *         int index = hash & (tab.length - 1); 
 *         HashEntry<K,V> first = tab[index]; 
 *         HashEntry<K,V> e = first; 
 *         while (e != null && (e.hash != hash || !key.equals(e.key))) 
 *             e = e.next; 
 *   
 *         V oldValue = null; 
 *         if (e != null) { 
 *             V v = e.value; 
 *             if (value == null || value.equals(v)) { 
 *                 oldValue = v; 
 *                 // All entries following removed node can stay 
 *                 // in list, but all preceding ones need to be 
 *                 // cloned. 
 *                 ++modCount; 
 *                 HashEntry<K,V> newFirst = e.next; 
 *                 for (HashEntry<K,V> p = first; p != e; p = p.next) 
 *                     newFirst = new HashEntry<K,V>(p.key, p.hash, 
 *                                                   newFirst, p.value); 
 *                 tab[index] = newFirst; 
 *                 count = c; // write-volatile 
 *             } 
 *         } 
 *         return oldValue; 
 *     } finally { 
 *         unlock(); 
 *     } 
 * } 
 * 
 * 首先remove操作也是确定需要删除的元素的位置，不过这里删除元素的方法不是简单地把待删除元素的前面的一个元素的next指向后面一个就完事了，我们之前已经说
 * 过HashEntry中的next是final的，一经赋值以后就不可修改，在定位到待删除元素的位置以后，程序就将待删除元素前面的那一些元素全部复制一遍，然后再一个
 * 一个重新接到链表上去，看一下下面这一幅图来了解这个过程：
 * 
 * 
 * 假设链表中原来的元素如上图所示，现在要删除元素3，那么删除元素3以后的链表就如下图所示：
 * 
 *  
 * 
 * 
 * ConcurrentHashMap的size操作
 * 
 * 在前面的章节中，我们涉及到的操作都是在单个Segment中进行的，但是ConcurrentHashMap有一些操作是在多个Segment中进行，比如size操作，
 * ConcurrentHashMap的size操作也采用了一种比较巧的方式，来尽量避免对所有的Segment都加锁。
 * 
 * 前面我们提到了一个Segment中的有一个modCount变量，代表的是对Segment中元素的数量造成影响的操作的次数，这个值只增不减，size操作就是遍历
 * 了两次Segment，每次记录Segment的modCount值，然后将两次的modCount进行比较，如果相同，则表示期间没有发生过写入操作，就将原先遍历的结果
 * 返回，如果不相同，则把这个过程再重复做一次，如果再不相同，则就需要将所有的Segment都锁住，然后一个一个遍历了，具体的实现大家可以看
 * ConcurrentHashMap的源码，这里就不贴了。
 * </pre>
 * 
 * <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-20 上午10:15:13 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class ResourceMap {

    private static Map<String, String> resourceMap = init();
    private static Logger log = Logger.getLogger(ResourceMap.class.getName());


    private static Map<String, String> init() {
        Map<String, String> initMap = new ConcurrentHashMap<String, String>();
        List<File> fileList = getAllPropertiesFile("TestProperties");
        for (File f : fileList) {
            Properties prop = getProperties(f);
            propTransIntoMap(initMap, prop);
        }
        return initMap;
    }


    public String get(String key) {
        return resourceMap.get(key);
    }


    private static void propTransIntoMap(Map<String, String> initMap, Properties prop) {
        Set<Object> keySet = prop.keySet();
        for (Object key : keySet) {
            String propKey = ((String) key).trim();
            String propValue = prop.getProperty((String) key).trim();
            initMap.put(propKey, propValue);
        }
    }


    private static Properties getProperties(File file) {
        // TODO Auto-generated method stub
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
        }
        catch (FileNotFoundException e) {
            log.error("properties file not found");
            e.printStackTrace();
        }
        catch (IOException e) {
            log.error("properties file IO exception");
            e.printStackTrace();
        }
        return properties;
    }


    private static List<File> getAllPropertiesFile(String dir) {
        return getAllPropertiesFile(dir, ".properties");
    }


    private static List<File> getAllPropertiesFile(String dir, String endStr) {
        List<File> list = new ArrayList<File>();
        File file = new File(ResourceMap.class.getClassLoader().getResource(dir).getPath());
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                System.out.println("......Name:" + f.getName() + "......");
                if (f.getName().endsWith(endStr)) {
                    list.add(f);
                }
            }
        }
        return list;
    }

}
