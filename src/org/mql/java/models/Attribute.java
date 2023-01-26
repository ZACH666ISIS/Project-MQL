package org.mql.java.models;

import org.mql.java.enums.EModifiers;
import org.mql.java.enums.Visibility;

public class Attribute {

	private long id;
	private String name;
	private String type;
	private EModifiers modifier;
	private Visibility visibility;
	private boolean isList;
	private long idRef;

	
	public Attribute(long id) {
		super();
		this.id = id;
	}
	
	public Attribute(long id, String name, String type, EModifiers modifier, Visibility visibility,
			boolean isList,
			long idRef) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.modifier =  modifier;
		this.visibility = visibility;
		this.isList = isList;
		this.idRef = idRef;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdRef() {
		return idRef;
	}
	public void setIdRef(long idRef) {
		this.idRef = idRef;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public EModifiers getModificator() {
		return modifier;
	}
	public void setModificator(EModifiers modifier) {
		this.modifier = modifier;
	}
	public Visibility getVisibility() {
		return visibility;
	}
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	public boolean isList() {
		return isList;
	}
	public void setList(boolean isList) {
		this.isList = isList;
	}

}
