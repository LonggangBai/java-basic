/**
 * Project Name:java-basic
 * File Name:StringTest.java
 * Package Name:com.easyway.java.basic.disabuse.basic
 * Date:2015-1-21下午12:24:13
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.basic;

/**
 * 
 * <pre>
 * 我们首先来看一段代码： 
 * Java代码 复制代码
 * String str=new String("abc");  
 * 
 * 紧接着这段代码之后的往往是这个问题，那就是这行代码究竟创建了几个String对象呢？相信大家对这道题并不陌生，答案也是众所周知的，2个。接下来我们就从这道题展开，一起回顾一下与创建String对象相关的一些JAVA知识。 
 * 
 * 我们可以把上面这行代码分成String str、=、"abc"和new String()四部分来看待。String str只是定义了一个名为str的String类型的变量，因此它并没有创建对象；=是对变量str进行初始化，将某个对象的引用（或者叫句柄）赋值给它，显然也没有创建对象；现在只剩下new String("abc")了。那么，new String("abc")为什么又能被看成"abc"和new String()呢？我们来看一下被我们调用了的String的构造器： 
 * Java代码 复制代码
 * public String(String original) {   
 *     //other code ...   
 * }  
 * 
 * 大家都知道，我们常用的创建一个类的实例（对象）的方法有以下两种： 
 * 使用new创建对象。
 * 调用Class类的newInstance方法，利用反射机制创建对象。
 * 
 * 我们正是使用new调用了String类的上面那个构造器方法创建了一个对象，并将它的引用赋值给了str变量。同时我们注意到，被调用的构造器方法接受的参数也是一个String对象，这个对象正是"abc"。由此我们又要引入另外一种创建String对象的方式的讨论——引号内包含文本。 
 * 
 * 这种方式是String特有的，并且它与new的方式存在很大区别。 
 * Java代码 复制代码
 * String str="abc";  
 * 
 * 毫无疑问，这行代码创建了一个String对象。 
 * Java代码 复制代码
 * String a="abc";   
 * String b="abc";  
 * 
 * 那这里呢？答案还是一个。 
 * Java代码 复制代码
 * String a="ab"+"cd";  
 * 
 * 再看看这里呢？答案仍是一个。有点奇怪吗？说到这里，我们就需要引入对字符串池相关知识的回顾了。 
 * 
 * 在JAVA虚拟机（JVM）中存在着一个字符串池，其中保存着很多String对象，并且可以被共享使用，因此它提高了效率。由于String类是final的，它的值一经创建就不可改变，因此我们不用担心String对象共享而带来程序的混乱。字符串池由String类维护，我们可以调用intern()方法来访问字符串池。 
 * 
 * 我们再回头看看String a="abc";，这行代码被执行的时候，JAVA虚拟机首先在字符串池中查找是否已经存在了值为"abc"的这么一个对象，它的判断依据是String类equals(Object obj)方法的返回值。如果有，则不再创建新的对象，直接返回已存在对象的引用；如果没有，则先创建这个对象，然后把它加入到字符串池中，再将它的引用返回。因此，我们不难理解前面三个例子中头两个例子为什么是这个答案了。 
 * 
 * 对于第三个例子： 
 * Java代码 复制代码
 * String a="ab"+"cd";  
 * 
 * 由于常量的值在编译的时候就被确定了。在这里，"ab"和"cd"都是常量，因此变量a的值在编译时就可以确定。这行代码编译后的效果等同于： 
 * Java代码 复制代码
 * String a="abcd";  
 * 
 * 因此这里只创建了一个对象"abcd"，并且它被保存在字符串池里了。 
 * 
 * 现在问题又来了，是不是所有经过“+”连接后得到的字符串都会被添加到字符串池中呢？我们都知道“==”可以用来比较两个变量，它有以下两种情况： 
 * 如果比较的是两个基本类型（char，byte，short，int，long，float，double，boolean），则是判断它们的值是否相等。
 * 如果表较的是两个对象变量，则是判断它们的引用是否指向同一个对象。
 * 
 * 下面我们就用“==”来做几个测试。为了便于说明，我们把指向字符串池中已经存在的对象也视为该对象被加入了字符串池：
 * 
 * 
 * 代码如下：
 * 
 * 
 * 运行结果如下： 
 * String a = "ab";
 * String b = "cd";
 * "ab"+"cd" 创建的对象 "加入了" 字符串池中
 * a +"cd" 创建的对象 "没加入" 字符串池中
 * "ab"+ b 创建的对象 "没加入" 字符串池中
 * a + b 创建的对象 "没加入" 字符串池中
 * 
 * 从上面的结果中我们不难看出，只有使用引号包含文本的方式创建的String对象之间使用“+”连接产生的新
 * 对象才会被加入字符串池中。对于所有包含new方式新建对象（包括null）的“+”连接表达式，它所产生的
 * 新对象都不会被加入字符串池中，对此我们不再赘述。
 * </pre>
 * 
 * ClassName:StringTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午12:24:13 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class StringTest {
    public static void main(String[] args) {
        String a = "ab";// 创建了一个对象，并加入字符串池中
        System.out.println("String a = \"ab\";");
        String b = "cd";// 创建了一个对象，并加入字符串池中
        System.out.println("String b = \"cd\";");
        String c = "abcd";// 创建了一个对象，并加入字符串池中

        String d = "ab" + "cd";
        // 如果d和c指向了同一个对象，则说明d也被加入了字符串池
        if (d == c) {
            System.out.println("\"ab\"+\"cd\" 创建的对象 \"加入了\" 字符串池中");
        }
        // 如果d和c没有指向了同一个对象，则说明d没有被加入字符串池
        else {
            System.out.println("\"ab\"+\"cd\" 创建的对象 \"没加入\" 字符串池中");
        }

        String e = a + "cd";
        // 如果e和c指向了同一个对象，则说明e也被加入了字符串池
        if (e == c) {
            System.out.println(" a  +\"cd\" 创建的对象 \"加入了\" 字符串池中");
        }
        // 如果e和c没有指向了同一个对象，则说明e没有被加入字符串池
        else {
            System.out.println(" a  +\"cd\" 创建的对象 \"没加入\" 字符串池中");
        }

        String f = "ab" + b;
        // 如果f和c指向了同一个对象，则说明f也被加入了字符串池
        if (f == c) {
            System.out.println("\"ab\"+ b   创建的对象 \"加入了\" 字符串池中");
        }
        // 如果f和c没有指向了同一个对象，则说明f没有被加入字符串池
        else {
            System.out.println("\"ab\"+ b   创建的对象 \"没加入\" 字符串池中");
        }

        String g = a + b;
        // 如果g和c指向了同一个对象，则说明g也被加入了字符串池
        if (g == c) {
            System.out.println(" a  + b   创建的对象 \"加入了\" 字符串池中");
        }
        // 如果g和c没有指向了同一个对象，则说明g没有被加入字符串池
        else {
            System.out.println(" a  + b   创建的对象 \"没加入\" 字符串池中");
        }
    }
}
