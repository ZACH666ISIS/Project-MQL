package org.mql.java.ui.loader;

public class FieldFormater extends DataFormater {


	public String data(XMLNode field) {
		StringBuffer s = new StringBuffer(visibility(field.child("visibility").value())+" ");
		s.append(field.attribute("name")+" : "+field.attribute("type"));
		boolean b = Boolean.valueOf(field.attribute("isList"));
		if(b) {
			s.append("[]");
		}

		return s.toString();
	}
	

	
	

}
