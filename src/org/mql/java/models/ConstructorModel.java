package org.mql.java.models;

import java.util.List;

import org.mql.java.enums.EModifiers;
import org.mql.java.enums.Visibility;

public class ConstructorModel{
	
	private long id;
	private Visibility visibility;
    private EModifiers modifier;
	private List<String> parameters;
	
	public ConstructorModel(long id) {
		this.id=id;
	}

	public ConstructorModel(long id, Visibility visibility, EModifiers modifier, List<String> parameters) {
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

	public List<String> getParameters() {
		return parameters;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}
	
	

	




	
	

	
}
