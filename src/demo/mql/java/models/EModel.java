package demo.mql.java.models;


import demo.mql.java.enums.EModificator;
import demo.mql.java.enums.Visibility;


public class EModel {
	
	private long id;
	private String name,
				   simpleName;
	private Visibility visibility;
	private EModificator modificator;
	
	
	public EModel(long id) {
		super();
		this.id = id;
	}


	public EModel(long id, String name, String simpleName,  Visibility visibility,
			EModificator modificator) {
		super();
		this.id = id;
		this.name = name;
		this.simpleName = simpleName;
		this.visibility = visibility;
		this.modificator = modificator;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSimpleName() {
		return simpleName;
	}


	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}




	public Visibility getVisibility() {
		return visibility;
	}


	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}


	public EModificator getModificator() {
		return modificator;
	}


	public void setModificator(EModificator modificator) {
		this.modificator = modificator;
	}

	

}
