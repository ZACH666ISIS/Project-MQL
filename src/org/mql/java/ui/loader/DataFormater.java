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
	
	protected boolean modifier(String m) {
		m = m.toUpperCase();
		if(m.equals("STATIC")) {
			return true;
		}
		return false;
	}
	
	public boolean isStatic(XMLNode obj) {
		XMLNode n = obj.child("modifier");
		if(n != null) {
			return modifier(n.value());
		}
		return false;
	}

}
