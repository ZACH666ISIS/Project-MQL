package org.mql.java.ui.loader;

public abstract class DataFormater {
	
	public abstract String data(XMLNode infos);
	
	protected char visibility(String v) {
		v = v.toUpperCase();
		if(v.equals("PUBLIC"))
			return '+';
		else if(v.equals("PROTECTED"))
			return '#';
		else
			return '-';
	}

}
