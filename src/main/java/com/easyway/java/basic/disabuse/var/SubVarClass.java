/**
 * Project Name:java-basic
 * File Name:SubVarClass.java
 * Package Name:com.easyway.java.basic.disabuse.var
 * Date:2015-1-21下午12:45:12
 * Copyright (c) 2015, longgangbai@sina.com EasyWay All Rights Reserved.
 *
 */

package com.easyway.java.basic.disabuse.var;

/**
 * JAVA面试题解惑系列（三）——变量（属性）的覆盖
 * <pre>
 * 这段代码的运行结果如下： 
 * ParentClass parentClass = new ParentClass();
 * 父类变量--private
 * 父类变量--friendly
 * 父类变量--protected
 * 父类变量--public
 * ParentClass subClass = new SubClass();
 * 子类变量--private
 * 父类变量--friendly
 * 父类变量--protected
 * 父类变量--public
 * SubClass subClazz = new SubClass();
 * 子类变量--private
 * 子类变量--friendly
 * 子类变量--protected
 * 子类变量--public
 * 
 * 从上面的结果中可以看出，private的变量与其它三种访问权限变量的不同，这是由于方法的重写（override）而引起的。关于重写知识的回顾留给以后的章节，
 * 这里我们来看一下其它三种访问权限下变量的覆盖情况。 
 * 
 * 分析上面的输出结果就会发现，变量的值取决于我们定义的变量的类型，而不是创建的对象的类型。 
 * 
 * 在上面的例子中，同名的变量访问权限也是相同的，那么对于名称相同但是访问权限不同的变量，情况又会怎样呢？事实胜于雄辩，我们继续来做测试。由于private
 * 变量的特殊性，在接下来的实验中我们都把它排除在外，不予考虑。 
 * 
 * 由于上面的例子已经说明了，当变量类型是父类（ParentClass）时，不管我们创建的对象是父类（ParentClass）的还是子类（SubClass）的，都不存在属性
 * 覆盖的问题，因此接下来我们也只考虑变量类型和创建对象都是子类（SubClass）的情况。
 * 
 * </pre>
 * 
 * ClassName:SubVarClass <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-1-21 下午12:45:12 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
class ParentVarClass {
    private String privateField = "父类变量--private";

    /* friendly */String friendlyField = "父类变量--friendly";

    protected String protectedField = "父类变量--protected";

    public String publicField = "父类变量--public";


    // private的变量无法直接访问，因此我们给他增加了一个访问方法
    public String getPrivateFieldValue() {
        return privateField;
    }
}


public class SubVarClass extends ParentVarClass {
    private String privateField = "子类变量--private";

    /* friendly */String friendlyField = "子类变量--friendly";

    protected String protectedField = "子类变量--protected";

    public String publicField = "子类变量--public";


    // private的变量无法直接访问，因此我们给他增加了一个访问方法
    public String getPrivateFieldValue() {
        return privateField;
    }


    public static void main(String[] args) {
        // 为了便于查阅，我们统一按照private、friendly、protected、public的顺序
        // 输出下列三种情况中变量的值

        // ParentClass类型，ParentClass对象
        ParentVarClass parentClass = new ParentVarClass();
        System.out.println("ParentClass parentClass = new ParentClass();");
        System.out.println(parentClass.getPrivateFieldValue());
        System.out.println(parentClass.friendlyField);
        System.out.println(parentClass.protectedField);
        System.out.println(parentClass.publicField);

        System.out.println();

        // ParentClass类型，SubClass对象
        ParentVarClass subClass = new SubVarClass();
        System.out.println("ParentClass subClass = new SubClass();");
        System.out.println(subClass.getPrivateFieldValue());
        System.out.println(subClass.friendlyField);
        System.out.println(subClass.protectedField);
        System.out.println(subClass.publicField);

        System.out.println();

        // SubClass类型，SubClass对象
        SubVarClass subClazz = new SubVarClass();
        System.out.println("SubClass subClazz = new SubClass();");
        System.out.println(subClazz.getPrivateFieldValue());
        System.out.println(subClazz.friendlyField);
        System.out.println(subClazz.protectedField);
        System.out.println(subClazz.publicField);
    }
}
