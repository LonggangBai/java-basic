package com.easyway.java.basic.files;

import java.io.File;
import java.io.FilenameFilter;

public class FileLists {
 
	public static  void dispayDir(String filepath) {
		File dir = new File(filepath);
		if (dir.isDirectory()) {
			System.out.println("dir=" + dir.getPath());
			File[] fileLists = dir.listFiles();
			for (File file : fileLists) {
				if (file.isDirectory()) {
					dispayDir(file.getAbsolutePath());
				} else {
					System.out.println("file=" + dir.getPath());
				}
			}
		} else {
			System.out.println("file=" + dir.getPath());
		}

	}
	public static void main(String[] args) {
		FileLists.dispayDir("D:/git/repo/java-basic");
	}

}
