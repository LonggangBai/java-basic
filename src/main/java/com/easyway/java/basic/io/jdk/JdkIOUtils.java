package com.easyway.java.basic.io.jdk;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;


public class JdkIOUtils {
    public static void outputStreamWriter() throws IOException{
        OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream("C:/text.txt"),"UTF-8");
        InputStreamReader reader=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(reader);
        String data;
        while((data=br.readLine())!=null &&  data.length()!=0)
        {
            System.out.println(data);
        }
        br.close();
    }
    public static void  bufferReaderWriter() throws IOException{
        BufferedReader br=new BufferedReader(new FileReader("C:/text.txt"));
        BufferedWriter bw=new BufferedWriter(new FileWriter("c:/out.txt"));
        String data=null;
        while((data=br.readLine())!=null){
            System.out.println(" "+data);
           bw.write(data);
        }
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static void charArray() throws IOException{
        char[] buffer=new char[]{'a','你','好','K'};
        CharArrayReader reader=new CharArrayReader(buffer);
        int data;
        while((data=reader.read())!=-1){
            System.out.println((char)data+ " ");
        }
        reader.close();
    }
    public static void stringreader() throws IOException{
        StringReader reader=new StringReader("");
        int data;
        while((data=reader.read())!=-1){
            System.out.println((char)data+ " ");
        }
        reader.close();
    }
    public static void charset(){
        System.out.println(System.getProperty("file.encoding"));
        Charset cs=Charset.defaultCharset();
        System.out.println(cs);
    }
    public static void  bufferInputOutputStream() throws IOException{
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream("C:/text.txt"));
        BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("c:/out.txt"));
        int size=1024;
        byte[] bytes=new byte[size];
        while((bis.read(bytes))!=-1){
            System.out.println(" "+new String(bytes));
            bos.write(bytes);
        }
        bos.flush();
        bos.close();
        bis.close();
    }
    public static void stringBufferInputStream(){
        try {
        StringBufferInputStream in=new StringBufferInputStream("abcd1 好");
        int data;
        while ((data=in.read())!= -1) {
            System.out.println(data + " ");
        }
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 
     * fileInOutputStream:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author Administrator
     * @since JDK 1.6
     */
    public static void fileInOutputStream() {
        try {
            //InputStream in=JdkIOUtils.class.getResourceAsStream("");
            
            final int SIZE=1024;
            FileInputStream fis = new FileInputStream("./temp");
            FileOutputStream fos = new FileOutputStream("D:\\out.txt");
            byte[] buff=new byte[SIZE];
            int len=fis.read(buff);
            while (len!= -1) {
                fos.write(buff,0,len);
                len=fis.read(buff);
            }
            fis.close();
            fos.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void fileInputStream() {
        try {
            FileInputStream fis = new FileInputStream("./temp");
            int data;
            while ((data = fis.read()) != -1) {
                System.out.println(data + " ");
            }
            fis.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();

        }
    }


    public static void byteArrayTester() {

        try {
            byte[] buffer = new byte[] { 2, 15, 67, -1, -9, 5 };
            ByteArrayInputStream in = new ByteArrayInputStream(buffer, 1, 4);
            int data = in.read();
            while (data != -1) {
                System.out.print(data + " ");
                data = in.read();
            }
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 、文件流
     * 
     * 现在让我们看下这种解决方案——我们将使用java.util.Scanner类扫描文件的内容，一行一行连续地读取： Apache Commons
     * IO流
     * 
     * 同样也可以使用Commons IO库实现，利用该库提供的自定义LineIterator:
     * 
     * LineIterator it = FileUtils.lineIterator(theFile, "UTF-8"); try {
     * while(it.hasNext()) { String line = it.nextLine(); // do something with
     * line } } finally { LineIterator.closeQuietly(it); }
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
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
        }
    }
}
