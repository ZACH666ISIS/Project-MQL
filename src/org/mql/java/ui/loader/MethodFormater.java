package org.mql.java.ui.loader;

public class MethodFormater extends DataFormater{


	public String data(XMLNode method) {
		StringBuffer s = new StringBuffer(visibility(method.child("visibility").value())+" ");
		String params = "";
		if(method.child("parameters") != null) {
			params = parametersFormat(method.child("parameters").children());
		}
		s.append(method.attribute("name")+" ( "+params+") : "+method.attribute("returnType"));
		return s.toString();
	}
	

	private String parametersFormat(XMLNode[] params) {
		StringBuffer parameterz = new StringBuffer(" ");
		for(int i=0 ; i < params.length ; i++) {
			parameterz.append(params[i].value()+" ");
			if(i+1 !=params.length) {
				parameterz.append(", ");
			}
		}
		return parameterz.toString();
	}

}
