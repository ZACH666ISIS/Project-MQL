package demo.mql.java.models;

import java.util.HashMap;

import demo.mql.java.enums.EModificator;
import demo.mql.java.enums.Visibility;

public class MethodModel extends ECModel{

	private String returnType;
	
	public MethodModel(long id) {
		super(id);
	}

	public MethodModel(	long id,
						Visibility visibility,
					    EModificator modifier,
						HashMap<String, String> parameters,
						String returnType) {
		super(id, visibility,modifier, parameters);
		this.returnType = returnType;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	

	
}
