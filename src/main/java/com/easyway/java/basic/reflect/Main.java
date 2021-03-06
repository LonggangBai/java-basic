package com.easyway.java.basic.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 下面代码演示如何通过反射调用方法。
 * 
 * 首先通过Class实例的getDeclaredMethods()获得所有方法的定义，然后通过Method的invoke方法触发方法调用，
 * invoke方法的第一个参数是方法所属对象，第二个参数是方法调用的参数列表。
 * 
 */
public class Main {

	/**
	 * Invokes the methods of an object using the Reflection api.
	 */
	public void invokeMethodsUsingReflection() {

		// Obtain the Class instance
		Class computerClass = Computer.class;

		// Get the methods
		Method[] methods = computerClass.getDeclaredMethods();

		// Create the object that we want to invoke the methods on
		Computer computer = new Computer();

		// Loop through the methods and invoke them
		for (Method method : methods) {
			Object result;
			try {
				// Call the method. Since none of them takes arguments we just
				// pass an empty array as second parameter.
				result = method.invoke(computer, new Object[0]);
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
				return;
			} catch (InvocationTargetException ex) {
				ex.printStackTrace();
				return;
			} catch (IllegalAccessException ex) {
				ex.printStackTrace();
				return;
			}
			System.out.println(method.getName() + ": " + result);
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		new Main().invokeMethodsUsingReflection();
	}

	class Computer {

		private String brand = "DELL";
		private String type = "Laptop";
		private int harddiskSize_GB = 300;
		private boolean AntiVirusInstalled = true;

		public String getBrand() {
			return brand;
		}

		public String getType() {
			return type;
		}

		public int getHarddiskSize_GB() {
			return harddiskSize_GB;
		}

		public boolean isAntiVirusInstalled() {
			return AntiVirusInstalled;
		}
	}
}