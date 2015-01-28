package com.easyway.java.basic.io.jdk;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class JdkIOUtils {
	/**
	 * 、文件流
	 * 
	 * 现在让我们看下这种解决方案——我们将使用java.util.Scanner类扫描文件的内容，一行一行连续地读取： Apache Commons
	 * IO流
	 * 
	 * 同样也可以使用Commons IO库实现，利用该库提供的自定义LineIterator:
	 * 
	 * LineIterator it = FileUtils.lineIterator(theFile, "UTF-8");
	 *  try { 
	 *  while(it.hasNext()) {
	 *   String line = it.nextLine(); // do something with line 
	 *   }
	 * } finally { LineIterator.closeQuietly(it); }
	 * 由于整个文件不是全部存放在内存中，这也就导致相当保守的内存消耗：（大约消耗了150MB内存）
	 * 
	 * [main] INFO o.b.java.CoreJavaIoIntegrationTest - Total Memory: 752 Mb
	 * [main] INFO o.b.java.CoreJavaIoIntegrationTest - Free Memory: 564 Mb 5、结论
	 * 
	 * 这篇短文介绍了如何在不重复读取与不耗尽内存的情况下处理大文件——这为大文件的处理提供了一个有用的解决办法。
	 * 
	 * @param filepath
	 */
	public void scanReadFile(String filepath) {
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			inputStream = new FileInputStream(filepath);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				// System.out.println(line);
			}
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (sc != null) {
				sc.close();
			}
		}
	}
}
