package demo.mql.java.models;



import demo.mql.java.enums.EModificator;
import demo.mql.java.enums.Visibility;

public class Attribute {

	private long id;
	private String name;
	private String type;
	private EModificator modificator;
	private Visibility visibility;
	private boolean isList;
	private long idRef;
	
	public Attribute(long id) {
		super();
		this.id = id;
	}
	
	public Attribute(long id, String name, String type, EModificator modificator, Visibility visibility,
			boolean isList,
			long idRef) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.modificator = modificator;
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
	public EModificator getModificator() {
		return modificator;
	}
	public void setModificator(EModificator modificator) {
		this.modificator = modificator;
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
