package com.easyway.java.basic.sync;

import java.util.LinkedList;

/**
 * <pre>
 * 4 何时释放锁？
 * 一般是执行完毕同步代码块（锁住的代码块）后就释放锁，也可以用wait()方式半路上释放锁。wait()方式就好比蹲厕所到一半，
 * 突然发现下水道堵住了，不得已必须出来站在一边，好让修下水道师傅(准备执行notify的一个线程）进去疏通马桶，疏通完毕，
 * 师傅大喊一声: "已经修好了"(notify)，刚才出来的同志听到后就重新排队。注意啊，必须等师傅出来啊，师傅不出来，谁也进不去。
 * 也就是说notify后，不是其它线程马上可以进入封锁区域活动了，而是必须还要等notify代码所在的封锁区域执行完毕从而释放锁以后，
 * 其它线程才可进入。
 * 
 * 这里是wait与notify代码示例：
 * 
 * 
 * 再深入一些。
 * 
 * 由于wait()操作而半路出来的同志没收到notify信号前是不会再排队的，他会在旁边看着这些排队的人(其中修水管师傅也在其中）。注意，修水管的师傅不能插队，也得跟那些上厕所的人一样排队，不是说一个人蹲了一半出来后，修水管师傅就可以突然冒出来然后立刻进去抢修了，他要和原来排队的那帮人公平竞争，因为他也是个普通线程。如果修水管师傅排在后面，则前面的人进去后，发现堵了，就wait，然后出来站到一边，再进去一个，再wait，出来，站到一边，只到师傅进去执行notify. 这样，一会儿功夫，排队的旁边就站了一堆人，等着notify.
 * 
 * 终于，师傅进去，然后notify了，接下来呢？
 * 
 * 1. 有一个wait的人（线程）被通知到。
 * 2. 为什么被通知到的是他而不是另外一个wait的人？取决于JVM.我们无法预先
 *    判断出哪一个会被通知到。也就是说，优先级高的不一定被优先唤醒，等待
 *    时间长的也不一定被优先唤醒，一切不可预知！(当然，如果你了解该JVM的
 *    实现，则可以预知）。
 * 3. 他（被通知到的线程）要重新排队。
 * 4. 他会排在队伍的第一个位置吗？回答是：不一定。他会排最后吗？也不一定。
 *    但如果该线程优先级设的比较高，那么他排在前面的概率就比较大。
 * 5. 轮到他重新进入厕位时，他会从上次wait()的地方接着执行，不会重新执行。
 *    恶心点说就是，他会接着拉巴巴，不会重新拉。
 * 6. 如果师傅notifyAll(). 则那一堆半途而废出来的人全部重新排队。顺序不可知。
 * Java DOC 上说，The awakened threads will not be able to proceed until the current thread relinquishes the lock on this object(当前线程释放锁前，唤醒的线程不能去执行）。
 * 
 * 这用厕位理论解释就是显而易见的事。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class MyStackA {

	int idx = 0;
	LinkedList<Character> buffer = new LinkedList<Character>();

	public synchronized void push(char c) {
		this.notify(); // 通知那些wait()的线程重新排队。注意：仅仅是通知它们重新排队。
		Character charObj = new Character(c);
		buffer.add(charObj);
	}// 执行完毕，释放锁。那些排队的线程就可以进来了。

	public synchronized char pop() {
		char c;
		while (buffer.size() == 0) {
			try {
				this.wait(); // 从厕位里出来
			} catch (InterruptedException e) {
				// ignore it...
			}
		}
		c = ((Character) buffer.remove(buffer.size() - 1)).charValue();
		return c;
	}

	public static void main(String args[]) {
		MyStackA m = new MyStackA();
		/**
		 * 下面对象m被加锁。严格的说是对象m的所有synchronized块被加锁。
		 * 如果存在另一个试图访问m的线程T，那么T无法执行m对象的push和 pop方法。
		 */
		m.push('a');
		m.pop();// 对象m被加锁。
	}
}