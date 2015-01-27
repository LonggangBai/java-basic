package com.easyway.java.basic.xml.dom;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * <pre>
 * 
 * XML 与 HTML 的主要差异
 * 
 * XML 不是 HTML 的替代，XML 和 HTML 为不同的目的而设计，XML 被设计为传输和存储数据，其焦点是数据的内容。
 * HTML 被设计用来显示数据，其焦点是数据的外观。HTML 旨在显示信息，而 XML 旨在传输信息。HTML的标记不是所
 * 有的都需要成对出现，XML则要求所有的标记必须成对出现，HTML标记不区分大小写，XML则 大小敏感,即区分大小.
 * 
 * XML的解析技术
 * 
 * 1、大名鼎鼎的DOM技术
 * 
 * 说它大名鼎鼎可是一点不为过，DOM 是 W3C 处理 XML 的标准 API，它是许多其它与 XML 处理相关的标准的基础，不仅是 Java，其它诸如 Javascript，PHP，MS .NET 等等语言都实现了该标准， 成为了应用最为广泛的 XML 处理方式。当然，为了能提供更多更加强大的功能，Java 对于 DOM 直接扩展工具类有很多，比如很多 Java 程序员耳熟能详的 JDOM，DOM4J 等等， 它们基本上属于对 DOM 接口功能的扩充，保留了很多 DOM API 的特性，许多原本的 DOM 程序员甚至都没有任何障碍就熟练掌握了另外两者的使用，直观、易于操作的方式使它深受广大 Java 程序员的喜爱。
 * 
 * 2、绿色环保的 SAX
 * 
 * SAX 的应运而生有它特殊的需要，为什么说它绿色环保呢，这是因为 SAX 使用了最少的系统资源和最快速的解析方式对 XML 处理提供了支持。 但随之而来繁琐的查找方式也给广大程序员带来许多困扰，常常令人头痛不已，同时它对 XPath 查询功能的支持，令人们对它又爱又恨。
 * 
 * 二种技术的比较：
 * 
 * DOM
 * 
 * 优缺点：实现 W3C 标准，有多种编程语言支持这种解析方式，并且这种方法本身操作上简单快捷，十分易于初学者掌握。其处理方式是将 XML 整个作为类似树结构的方式读入内存中以便操作及解析，因此支持应用程序对 XML 数据的内容和结构进行修改，但是同时由于其需要在处理开始时将整个 XML 文件读入到内存中去进行分析，因此其在解析大数据量的 XML 文件时会遇到类似于内存泄露以及程序崩溃的风险，请对这点多加注意。
 * 
 * 适用范围：小型 XML 文件解析、需要全解析或者大部分解析 XML、需要修改 XML 树内容以生成自己的对象模型
 * 
 * SAX
 * 
 * SAX 从根本上解决了 DOM 在解析 XML 文档时产生的占用大量资源的问题。其实现是通过类似于流解析的技术，通读整个 XML 文档树，通过事件处理器来响应程序员对于 XML 数据解析的需求。由于其不需要将整个 XML 文档读入内存当中，它对系统资源的节省是十分显而易见的，它在一些需要处理大型 XML 文档以及性能要求较高的场合有起了十分重要的作用。支持 XPath 查询的 SAX 使得开发人员更加灵活，处理起 XML 来更加的得心应手。但是同时，其仍然有一些不足之处也困扰广大的开发人员：首先是它十分复杂的 API 接口令人望而生畏，其次由于其是属于类似流解析的文件扫描方式，因此不支持应用程序对于 XML 树内容结构等的修改，可能会有不便之处。
 * 
 * 适用范围：大型 XML 文件解析、只需要部分解析或者只想取得部分 XML 树内容、有 XPath 查询需求、有自己生成特定 XML 树对象模型的需求
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class DomGeneratorXml {
	public static void main(String[] args) {
		String outputPath = "persons.xml";
		generateXml(outputPath);
	}

	public static void generateXml(String outputPath) {
		try {
			Person[] arr = new Person[] { new Person("egg", 22),
					new Person("niu", 21) };
			List<Person> list = Arrays.asList(arr);// 将数组转换成List
			Document doc = generateXml(list);// 生成XML文件
			outputXml(doc, outputPath);// 将文件输出到指定的路径
		} catch (Exception e) {
			System.err.println("出现异常");
		}
	}

	/**
	 * 将XML文件输出到指定的路径
	 * 
	 * @param doc
	 * @param fileName
	 * @throws Exception
	 */
	private static void outputXml(Document doc, String fileName)
			throws Exception {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMSource source = new DOMSource(doc);
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");// 设置文档的换行与缩进
		PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
		StreamResult result = new StreamResult(pw);
		transformer.transform(source, result);
		System.out.println("生成XML文件成功!");
	}

	/**
	 * 生成XML文件
	 * 
	 * @param list
	 * @return
	 */
	public static Document generateXml(List<Person> list) {
		Document doc = null;
		Element root = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
			root = doc.createElement("person");
			doc.appendChild(root);
		} catch (Exception e) {
			e.printStackTrace();
			return null;// 如果出现异常，则不再往下执行
		}

		int len = list.size();
		Element element;
		for (int i = 0; i < len; i++) {
			Person person = list.get(i);
			element = doc.createElement("person" + (i + 1));
			element.setAttribute("age", "" + person.getAge());
			element.setAttribute("name", person.getUsername());
			root.appendChild(element);
		}
		return doc;
	}
}