package org.mql.java.ui.loader;

import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLNode {
	private Node node;

	public XMLNode (Node node) {
		super();
		this.node=node;
	}
	public XMLNode(String source) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
		try {
			DocumentBuilder builder =  factory.newDocumentBuilder();
			Document document =builder.parse(source);
			node = document.getFirstChild();			
		} catch (Exception e) {
			System.out.println("Error : "+e.getMessage());
		}
		
	}

	public XMLNode[] children() {
		NodeList list = node.getChildNodes();
		Vector<XMLNode> nodes = new Vector<XMLNode>();
		for(int i=0;i<list.getLength();i++) {
			if(list.item(i).getNodeType() == Node.ELEMENT_NODE)
				nodes.add(new XMLNode(list.item(i)));
					
		}
		return nodes.toArray(new XMLNode[nodes.size()]);

	}
	public XMLNode child(String name) {
		NodeList list = node.getChildNodes();
		for(int i=0;i<list.getLength();i++) {
			if(list.item(i).getNodeName().equals(name))
				return new XMLNode(list.item(i));
					
		}
		return null;

	}
	public String name() {
		return node.getNodeName();
	}
	
	boolean isElement() {
		return node.getNodeType() == Node.ELEMENT_NODE;
	}
	
	public String value() {
		if(node.getNodeType() == Node.ELEMENT_NODE)
			return node.getFirstChild().getNodeValue();
		return node.getNodeValue();

	}
	public String attribute(String name) {
		NamedNodeMap atts =node.getAttributes();
		return atts.getNamedItem(name).getNodeValue();
	}
	
	public long longAttribute(String name) {
		NamedNodeMap atts =node.getAttributes();
		try {
			return Long.parseLong(atts.getNamedItem(name).getNodeValue());
		}catch(Exception e) {
			return -1;
		}
	}
	
	public boolean hasChilds() {
		
		return node.hasChildNodes();
		
	}
	

}
