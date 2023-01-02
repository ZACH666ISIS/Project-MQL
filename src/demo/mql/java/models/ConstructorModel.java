package demo.mql.java.models;

import java.util.HashMap;

import demo.mql.java.enums.EModifiers;
import demo.mql.java.enums.Visibility;

public class ConstructorModel{
	
	private long id;
	private Visibility visibility;
    private EModifiers modifier;
	private HashMap<String,String> parameters;
	
	public ConstructorModel(long id) {
		this.id=id;
	}

	public ConstructorModel(long id, Visibility visibility, EModifiers modifier, HashMap<String, String> parameters) {
		super();
		this.id = id;
		this.visibility = visibility;
		this.modifier = modifier;
		this.parameters = parameters;
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
	
	

	




	
	

	
}
