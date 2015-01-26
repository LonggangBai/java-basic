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