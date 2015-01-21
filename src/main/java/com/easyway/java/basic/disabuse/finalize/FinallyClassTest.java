/**
 * Project Name:java-basic
 * File Name:FinallyClassTest.java
 * Package Name:com.easyway.java.basic.disabuse.finalize
 * Date:2015-1-21下午1:12:31
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.finalize;

/**
 * 
 * <pre>
 * 上面这段代码的运行结果如下： 
 * 执行了return语句
 * 执行了finally语句
 * 0
 * 执行了finally语句
 * 1
 * 执行了finally语句
 * 2
 * 执行了finally语句
 * 0
 * 执行了finally语句
 * 1
 * 执行了finally语句
 * 
 * 很明显，return、continue和break都没能阻止finally语句块的执行。从输出的结果来看，return语句似乎在finally语句块之前执行了，事实真的如此吗？我们来想想看，return语句的作用是什么呢？是退出当前的方法，并将值或对象返回。如果finally语句块是在return语句之后执行的，那么return语句被执行后就已经退出当前方法了，finally语句块又如何能被执行呢？因此，正确的执行顺序应该是这样的：编译器在编译return new ReturnClass();时，将它分成了两个步骤，new ReturnClass()和return，前一个创建对象的语句是在finally语句块之前被执行的，而后一个return语句是在finally语句块之后执行的，也就是说finally语句块是在程序退出方法之前被执行的。同样，finally语句块是在循环被跳过（continue）和中断（break）之前被执行的。 
 * 
 * finalize方法 
 * 
 * 最后，我们再来看看finalize，它是一个方法，属于java.lang.Object类，它的定义如下： 
 * Java代码 复制代码
 * protected void finalize() throws Throwable { }  
 * 
 * 众所周知，finalize()方法是GC（garbage collector）运行机制的一部分，关于GC的知识我们将在后续的章节中来回顾。 
 * 
 * 在此我们只说说finalize()方法的作用是什么呢？ 
 * 
 * finalize()方法是在GC清理它所从属的对象时被调用的，如果执行它的过程中抛出了无法捕获的异常（uncaught exception），GC将终止对改对象的清理，并且该异常会被忽略；直到下一次GC开始清理这个对象时，它的finalize()会被再次调用。 
 * 
 * 请看下面的示例：
 * </pre>
 * 
 * ClassName:FinallyClassTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午1:12:31 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public final class FinallyClassTest {

    // 测试return语句
    public ReturnClass testReturn() {
        try {
            return new ReturnClass();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("执行了finally语句");
        }
        return null;
    }


    // 测试continue语句
    public void testContinue() {
        for (int i = 0; i < 3; i++) {
            try {
                System.out.println(i);
                if (i == 1) {
                    continue;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                System.out.println("执行了finally语句");
            }
        }
    }


    // 测试break语句
    public void testBreak() {
        for (int i = 0; i < 3; i++) {
            try {
                System.out.println(i);
                if (i == 1) {
                    break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                System.out.println("执行了finally语句");
            }
        }
    }


    public static void main(String[] args) {
        FinallyClassTest ft = new FinallyClassTest();
        // 测试return语句
        ft.testReturn();
        System.out.println();
        // 测试continue语句
        ft.testContinue();
        System.out.println();
        // 测试break语句
        ft.testBreak();
    }
}


class ReturnClass {
    public ReturnClass() {
        System.out.println("执行了return语句");
    }
}
