package com.easyway.java.basic.thread;

import java.util.*;
import java.text.NumberFormat;

/**
 * <pre>
 * 从本篇文章起，我将在Java多线程方面进行深入剖析，这是很大的一部分内容，也是我们作为Java开发者必须要跨过的一道坎儿！因为Java本身就是多线程的语言，
 * 想要真正掌握Java，那么请先掌握多线程！文章会整理为一个系列，包含若干篇文章，因为在笔者看来，多线程的问题是不能用几篇文章来说清楚的，也许理论知识并
 * 没有那么夸张，但是实际情况会有很多，我会从基本概念开始，逐渐深入，争取能以最简单的表述让不同层次的读者看懂！本章系Java之美[从菜鸟到高手演变]系列之
 * 多线程简介，在本章，笔者将带领大家从理方面完全过一遍Java多线程开发，整个系列会是一种从理论到实践的学习过程！
 * 
 * 一、多线程编程简介
 * 
 * 1、为什么需要多线程编程
 * 
 * 我们知道目前我们计算机基本是基于X86架构的，而基于X86架构的机器主频超不过4GHz，随着信息时代的来临，我们需要处理的数据越来越大，因此对程序的性能也
 * 要求越来越高，提高程序的性能，一方面需要提高运行环境的配置，也就是配性能更好的机器，更快的CPU更大的内存，另一方面就是优化自己的程序，从前者的角度考
 * 虑，我们说当前计算机的CPU主频已经接近顶级，一段时间内不可能再高，也就是说想通过提高机器的性能来加快程序的运行是一个不好的选择，因为你需要投入更多的
 * 硬件。同时其实我们的程序在运行的时候，CPU很多时候都是空闲的状态，因为程序不光有CPU调度，而大多数耗时的操作都在于IO上，我们知道程序的运行速度取决于
 * 木桶原理，CPU再快IO跟不上也没有用。所以我们需要合理利用空闲的CPU，当IO在处理其它的时候，CPU可以继续工作，这就是多线程编程。多线程编程的好处，
 * 总结一句话就是：合理利用CPU的空闲时间，来提高程序的性能。用我自己喜欢的一句话就是：有了多线程编程，我们可以告别摩尔定律，在这样一个多核的时代，
 * 写一个多线程的应用，总觉得比购置大量的多核硬件来的轻松、实在！
 * 
 * 2、什么是Java多线程编程
 * 
 * Java语言本身就是多线程的，因此为我们开发者提供了语言级的多线程支持，这个要比C/C++中使用多线程方便的多，同时Java为我们提供了很多便利的条件，使
 * 得我们开发多线程应用方便了很多，但是这也是相对而言的，想要开发出一个真正意义上的多线程应用很难，因为有太多的问题需要考虑，看得见的和看不见的。所以
 * 我们必须从理论上打好基础，然后做大量的练习，真正的了解多线程的机制，不断实践，才能写出漂亮的并发应用来。
 * 
 * 3、利用多线程开发解决哪些问题
 * 
 * 在Java里有很多地方都在用多线程实现，尽管有时你没有发现，如Swing和SWT开发，当我们点击一个按钮时，我们总不能等待后台的数据处理完在给我们跳转吧，
 * 这样用户体验实在是太差了，我们可以先完成跳转，再加一些可以让用户等待的业务（如缓冲条等），然后再呈现结果。还有如我们开发Web时必用的Servlet，天
 * 生具有多线程性质，因为对于网站来说，同时又多个访问是最起码的需求，我们绝对不可能一个接一个的处理请求，那样根本不符合现实。这些地方就用到了多线程，
 * 并发地处理多个请求，提高程序的响应速度。
 * 
 * 4、多线程编程的利弊
 * 
 * 说了这么多，也许会给大家带来一个印象：多线程总会让程序跑的更快，所以我们应该多多使用。其实任何事物具有两面性，多线程也会带来一些负面的东西，如完美
 * 的解决并发带来的线程安全（这个问题很不好解决，也是处理多线程问题最棘手的事情，而且解决现程安全就会用到同步，同步的话又会带来性能的下降），所以从开
 * 发难度、硬件需求、维护成本等方面综合考虑，我们还是得根据实际情况来决定，到底需不需要多线程，或者哪部分需要，都是值得商榷的事儿。
 * 
 * 5、进程和线程的关系
 * 
 * 这个问题很经典，无数面试官想从这个问题得到你对线程和进程的理解程度，想完全讲清楚有点儿难度，同时也需要篇幅。从程序开发角度来讲，进程是资源分配的基本
 * 单位，是一个程序或者服务的基本单位。我们可以说进程就是程序的执行过程，这个过程包括很多东西，如CPU执行时间、运行内存、数据等，而且是一个动态的过程。
 * 线程是轻量级的进程，它们是共享在父进程拥有的资源下，每个线程在父进程的环境中顺序的独立的执行一个活动，每个CPU核心在同一时刻只能执行一个线程，尽管
 * 我们有时感觉自己的计算机同时开着多个任务，其实他们每个的执行都是走走停停的，CPU轮流给每个进程及线程分配时间。总结一下二者关系：
 * 
 * a>.一个线程只能属于一个进程，而一个进程可以有多个线程，但至少有一个线程。线程是操作系统可识别的最小执行和调度单位。
 * b>.资源分配给进程，同一进程的所有线程共享该进程的所有资源。 同一进程中的多个线程共享代码段(代码和常量)，数据段(全局变量和静态变量)，扩展段(堆存储)。
 * 但是每个线程拥有自己的栈段，栈段又叫运行时段，用来存放所有局部变量和临时变量。
 * c>.处理机分给线程，即真正在处理机上运行的是线程。
 * d>.线程在执行过程中，需要协作同步。不同进程的线程间要利用消息通信的办法实现同步。
 * 更为详细的解释，请有兴趣的TX去读操作系统原理相关方面的书籍，来更深层次的理解，此处此点非重点！
 * 
 * 二、多线程开发简单示例
 * 
 * 其实程序很多时候就是现实生活的具体体现，没有一种程序模型在生活中找不到原型的，因为程序就是用来解决生活问题的。举一个非常贴切生活的例子：如果你是一个
 * 很有生活规律的人，每天起床你要干这么几件事情：起床——穿衣——洗漱——烧水煮面（或泡茶）——吃早点——看报纸等。如果顺序来完成这些事儿的话，你需要一件一件
 * 的挨着做，没有任何问题，你都可以完成。但是，我们来看烧水这件事情，把人看作CPU的话，在烧水的过程中，CPU是空闲的，在等待条件的满足（水被烧开），我们
 * 似乎可以对这个流程做个改良，在烧水的同时干点儿别的事情！此处引入了异步操作，我们可以在等待水被烧开的这段时间内做一些其它的事情，如洗漱、看报纸。这样
 * 一来，当完成所有需要完成的事儿时，我们的时间节省了很多！这就是多线程的模型：当CPU在运行时等待IO等其他操作的时候，可以继续做别的事情！
 * 
 * 三、Java实现多线程的基本方式
 * 
 * 多线程最基本的实现方式有两种：继承Thread类和实现Runnable接口。此处我们先简单介绍，在后面的文章中我们逐渐深入。Runnable接口中内容很简单，只有一个
 * 抽象方法：public abstract void run();而且Thread类也是实现了Runnable接口，此处说明如果想要用实现Runnable接口这种方式，必须得借助Thread
 * 来实现。但为什么要这样设计呢？我们知道Java是没有多重继承的，所以如果我们采用继承Thread类的方式来实现多线程，那个这个类就不能在继承别的类了，这显然有
 * 些时候不太方便，所以我们可以采用实现Runnable接口。
 * </pre>
 * 
 * @author Administrator
 * 
 */
class Synch {
	private static long[] locking_time = new long[100];
	private static long[] not_locking_time = new long[100];
	private static final long ITERATIONS = 10000000;

	synchronized long locking(long a, long b) {
		return a + b;
	}

	long not_locking(long a, long b) {
		return a + b;
	}

	private void test(int id) {
		long start = System.currentTimeMillis();
		for (long i = ITERATIONS; --i >= 0;) {
			locking(i, i);
		}
		locking_time[id] = System.currentTimeMillis() - start;
		start = System.currentTimeMillis();
		for (long i = ITERATIONS; --i >= 0;) {
			not_locking(i, i);
		}
		not_locking_time[id] = System.currentTimeMillis() - start;
	}

	static void print_results(int id) {
		NumberFormat compositor = NumberFormat.getInstance();
		compositor.setMaximumFractionDigits(2);
		double time_in_synchronization = locking_time[id]
				- not_locking_time[id];
		System.out
				.println("Pass "
						+ id
						+ ": Time lost: "
						+ compositor.format(time_in_synchronization)
						+ " ms. "
						+ compositor
								.format(((double) locking_time[id] / not_locking_time[id]) * 100.0)
						+ "% increase");
	}

	static public void main(String[] args) throws InterruptedException {
		final Synch tester = new Synch();
		tester.test(0);
		print_results(0);
		tester.test(1);
		print_results(1);
		tester.test(2);
		print_results(2);
		tester.test(3);
		print_results(3);
		tester.test(4);
		print_results(4);
		tester.test(5);
		print_results(5);
		tester.test(6);
		print_results(6);
		final Object start_gate = new Object();
		Thread t1 = new Thread() {
			public void run() {
				try {
					synchronized (start_gate) {
						start_gate.wait();
					}
				} catch (InterruptedException e) {
				}
				tester.test(7);
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				try {
					synchronized (start_gate) {
						start_gate.wait();
					}
				} catch (InterruptedException e) {
				}
				tester.test(8);
			}
		};
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		t1.start();
		t2.start();
		synchronized (start_gate) {
			start_gate.notifyAll();
		}
		t1.join();
		t2.join();
		print_results(7);
		print_results(8);
	}
}