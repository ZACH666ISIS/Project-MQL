package org.mql.java.test;

public class Test {
	
	public Test() {
		exp01();
	}
	
	void exp01() {
		int i= -1005;
		
		System.out.println(i & 0xfffffff);
	}
	public static void main(String[] args) {
		new Test();
	}
}
