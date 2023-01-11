package org.mql.java.test;

public class Test {
	
	public Test() {
		exp01();
	}
	
	void exp01() {
		String s1 = "org.mql.java.inter",
			   s2 = "org.mql.java.inter.wst",
			   s3 = "org.mql.java.inter.wst.inter";
		System.out.println(s1.contains(s2));
		System.err.println(s2.contains(s3));
	}
	public static void main(String[] args) {
		new Test();
	}
}
