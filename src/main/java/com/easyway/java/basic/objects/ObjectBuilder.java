/**
 * Project Name:java-basic
 * File Name:ObjectBuilder.java
 * Package Name:com.easyway.java.basic.objects
 * Date:2015-3-18下午4:56:59
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
*/

package com.easyway.java.basic.objects;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * ClassName:对象的生命周期 对象的创建
 *  1.new 2.反射 3.反序列化 4.clone 
 *  
 *  Object类的clone方法具有以下特点
 *  1.生命为protected类型，object子类如果希望对外公开clone方法，必须扩大访问权限。
 *  2.必须实现Cloneable接口。
 *  3.object类的clone方法的实现中会创建一个复制的对象，这个对象和原来的对象具有相同的内存地址，不过他们的属性值相同。
 *  <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-18 下午4:36:34 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
class Customer implements Cloneable,Serializable {
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;


    public Customer() {
        this("unknown", 0);
        System.out.println("call default constructor");
    }


    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (age != other.age)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        return true;
    }


    public String toString() {
        return super.toString()+"  name=" + name + " , age=" + age;
    }
}


public class ObjectBuilder {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, CloneNotSupportedException, IOException {
        // 反射
        Class objClass = Class.forName("com.easyway.java.basic.objects.Customer");
        Customer c1 = (Customer) objClass.newInstance();
        System.out.println("c1=" + c1);
        // new
        Customer c2 = new Customer("Tom", 20);
        System.out.println("c2=" + c2);
        // 克隆
        Customer c3 = (Customer) c2.clone();
        System.out.println("c2==c3 : " + (c2 == c3));
        System.out.println("c2.equals(c3) :" + (c2.equals(c3)));
        System.out.println("c3=" + c3);
        System.out.println("clone object " +c2.toString()+"   : "+c3.toString());

        FileOutputStream fos = new FileOutputStream("./tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(c3);
        oos.close();
        fos.close();

        FileInputStream fis = new FileInputStream("./tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Customer c4 = (Customer) ois.readObject();
        ois.close();
        fis.close();
        System.out.println("c2.equals(c4) :" + (c2.equals(c4)));
        System.out.println("c4=" + c4);
    }
}


