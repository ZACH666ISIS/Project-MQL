package demo.mql.java.models;

import java.util.HashMap;

import demo.mql.java.enums.EModificator;
import demo.mql.java.enums.Visibility;

public class ECModel {
	
	private long id;
	private Visibility visibility;
    private EModificator modifier;
	private HashMap<String,String> parameters;
	
	
	public ECModel(long id) {
		super();
		this.id = id;
	}
	
	public ECModel( long id,
				   Visibility visibility,
				   EModificator modifier, HashMap<String, String> parameters) {
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
	public EModificator getModifier() {
		return modifier;
	}
	public void setModifier(EModificator modifier) {
		this.modifier = modifier;
	}
	
	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public HashMap<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(HashMap<String, String> parameters) {
		this.parameters = parameters;
	}
	
}
