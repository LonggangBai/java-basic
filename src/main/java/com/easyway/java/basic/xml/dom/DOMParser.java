package com.easyway.java.basic.xml.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 采用dom方式解析xml
 * 
 * @author Administrator
 * 
 */
public class DOMParser {

	/* build a DocumentBuilderFactory */
	DocumentBuilderFactory builderFactory = DocumentBuilderFactory
			.newInstance();

	public static void main(String[] args) {
		String xmlfile = DOMParser.class.getClassLoader()
				.getResource("./books.xml").getFile();
		System.out.println("xmlfile=" + xmlfile);
		DOMParser parser = new DOMParser();
		Document document = parser.parse(xmlfile);
		/* get root element */
		Element rootElement = document.getDocumentElement();

		/* get all the nodes whose name is book */
		NodeList nodeList = rootElement.getElementsByTagName("book");
		if (nodeList != null) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				/* get every node */
				Node node = nodeList.item(i);
				/* get the next lever's ChildNodes */
				NodeList nodeList2 = node.getChildNodes();
				for (int j = 0; j < nodeList2.getLength(); j++) {
					Node node2 = nodeList2.item(j);
					if (node2.hasChildNodes()) {
						System.out.println(node2.getNodeName() + ":"
								+ node2.getFirstChild().getNodeValue());
					}
				}
			}
		}
	}

	/* Load and parse XML file into DOM */
	public Document parse(String filePath) {
		Document document = null;
		try {
			/* DOM parser instance */
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			/* parse an XML file into a DOM tree */
			document = builder.parse(new File(filePath));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
}