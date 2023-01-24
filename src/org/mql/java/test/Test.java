package org.mql.java.test;

import org.mql.java.ui.loader.XMLLoader;

public class Test {
	
	public Test() {
		exp01();
	}
	
	void exp01() {
		XMLLoader loader = new XMLLoader("resources/document.xml");
	}
	public static void main(String[] args) {
		new Test();
	}
}
