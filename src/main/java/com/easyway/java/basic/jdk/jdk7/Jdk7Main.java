package com.easyway.java.basic.jdk.jdk7;

/**
 * <pre>
 * JDK 1.7 新特性 （转）
 * 1.对Java集合（Collections）的增强支持
 * 
 * 在JDK1.7之前的版本中，Java集合容器中存取元素的形式如下：
 * 
 * 以List、Set、Map集合容器为例：
 * 
 *     
 * 
 *  
 *  
 * 
 * 在JDK1.7中，摒弃了Java集合接口的实现类，如：ArrayList、HashSet和HashMap。而是直接采用[]、{}的形式存入对象，采用[]的形式按照索引、键值来获取集合中的对象，如下：
 * 
 *  
 *  
 *  
 * 
 * 2.在Switch中可用String
 * 
 * 在之前的版本中是不支持在Switch语句块中用String类型的数据的，这个功能在C#语言中早已被支持，好在JDK1.7中加入了。
 * 
 *  
 *  
 *  
 *  
 * 
 * 3.数值可加下划线
 * 
 * 例如：int one_million = 1_000_000;
 * 
 *  
 * 
 * 4.支持二进制文字
 * 
 * 例如：int binary = 0b1001_1001;
 * 
 *  
 * 
 * 5.简化了可变参数方法的调用
 * 
 * 当程序员试图使用一个不可具体化的可变参数并调用一个*varargs* （可变）方法时，编辑器会生成一个“非安全操作”的警告。
 * 原文：http://iteye.blog.163.com/blog/static/18630809620127136516641/?suggestedreading&wumii
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class Jdk7Main {

}
