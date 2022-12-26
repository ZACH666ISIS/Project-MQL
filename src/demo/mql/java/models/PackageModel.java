package demo.mql.java.models;

import java.util.List;
import java.util.Vector;

public class PackageModel {

	private long id;
	private String name;
	private List<EModel> classes;
	
	
	
	public PackageModel(long id) {
		this.id = id;
	}
	public PackageModel(long id,String name) {
		this.id = id;
		this.name = name;
		classes = new Vector<EModel>();
	}
	
	public PackageModel(long id,String name,List<EModel> classes) {
		this.id = id;
		this.name = name;
		this.classes = classes;
	}
	
	public boolean addClass(EModel c) {
		return classes.add(c);
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
	
	public List<EModel> getClasses() {
		return classes;
	}
	public void setClasses(List<EModel> classes) {
		this.classes = classes;
	}
	
	

	
}
