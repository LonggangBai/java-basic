package com.easyway.java.basic.io.google;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * <pre>
 * 读取文件行的标准方式是在内存中读取，Guava 和Apache Commons IO都提供了如下所示快速读取文件行的方法：
 * 
 * Files.readLines(new File(path), Charsets.UTF_8);
 *  
 * FileUtils.readLines(new File(path));
 * 这种方法带来的问题是文件的所有行都被存放在内存中，当文件足够大时很快就会导致程序抛出OutOfMemoryError 异常。
 * 例如：读取一个大约1G的文件：
 * 
 * @Test
 * public void givenUsingGuava_whenIteratingAFile_thenWorks() throws IOException {
 *     String path = ...
 *     Files.readLines(new File(path), Charsets.UTF_8);
 * }
 * 这种方式开始时只占用很少的内存：（大约消耗了0Mb内存）
 * 
 * [main] INFO  org.baeldung.java.CoreJavaIoUnitTest - Total Memory: 128 Mb
 * [main] INFO  org.baeldung.java.CoreJavaIoUnitTest - Free Memory: 116 Mb
 * 然而，当文件全部读到内存中后，我们最后可以看到（大约消耗了2GB内存）：
 * 
 * [main] INFO  org.baeldung.java.CoreJavaIoUnitTest - Total Memory: 2666 Mb
 * [main] INFO  org.baeldung.java.CoreJavaIoUnitTest - Free Memory: 490 Mb
 * 这意味这一过程大约耗费了2.1GB的内存——原因很简单：现在文件的所有行都被存储在内存中。
 * 
 * 把文件所有的内容都放在内存中很快会耗尽可用内存——不论实际可用内存有多大，这点是显而易见的。
 * 
 * 此外，我们通常不需要把文件的所有行一次性地放入内存中——相反，我们只需要遍历文件的每一行，然后做相应的处理，处理完之后把它扔掉。
 * 所以，这正是我们将要做的——通过行迭代，而不是把所有行都放在内存中。
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class GuavaFileIOUtils {
	public void readFile(String filepath) throws IOException {
		List<String> context = Files.readLines(new File(filepath),
				Charsets.UTF_8);
		System.out.println("context=" + context);
	}
}
