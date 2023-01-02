package demo.mql.java.models;

import java.util.HashMap;

import demo.mql.java.enums.EModifiers;
import demo.mql.java.enums.Visibility;

public class MethodModel{

	private long id;
	private String name;
	private Visibility visibility;
    private EModifiers modifier;
	private HashMap<String,String> parameters;
	private String returnType;
	

	public MethodModel(long id,String name, Visibility visibility, EModifiers modifier,
			HashMap<String, String> parameters, String returnType) {
		this.id = id;
		this.name = name;
		this.visibility = visibility;
		this.modifier = modifier;
		this.parameters = parameters;
		this.returnType = returnType;
	}




	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public Visibility getVisibility() {
		return visibility;
	}




	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}




	public EModifiers getModifier() {
		return modifier;
	}




	public void setModifier(EModifiers modifier) {
		this.modifier = modifier;
	}




	public HashMap<String, String> getParameters() {
		return parameters;
	}




	public void setParameters(HashMap<String, String> parameters) {
		this.parameters = parameters;
	}




	public String getName() {
		return name;
	}
	
	

	
}
