package com.easyway.java.basic.thread;

import java.util.ArrayList;
import java.util.List;

public class DebugLogger {
	private static class ThreadLocalList extends ThreadLocal<List<String>> {
		public List<String> initialValue() {
			return new ArrayList<String>();
		}

		public List<String> getList() {
			return (List<String>) super.get();
		}
	}

	private ThreadLocalList list = new ThreadLocalList();
	private static String[] stringArray = new String[0];

	public void clear() {
		list.getList().clear();
	}

	public void put(String text) {
		list.getList().add(text);
	}

	public String[] get() {
		return list.getList().toArray(stringArray);
	}
}