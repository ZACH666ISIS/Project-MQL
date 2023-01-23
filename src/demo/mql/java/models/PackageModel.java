package demo.mql.java.models;

import java.util.List;
import java.util.Vector;

public class PackageModel {

	private long id;
	private String name;
	private List<ClassModel> classes;
	private List<InterfaceModel> interfaces;
	private List<EnumModel> enumes;
	private List<PackageModel> packages;
	

	public PackageModel(long id) {
		this.id = id;
	}
	public PackageModel(long id,String name) {
		this.id = id;
		this.name = name;
		classes = new Vector<>();
		interfaces = new Vector<>();
		enumes = new Vector<>();
		packages = new Vector<>();
	}
	
	public PackageModel(long id,String name,List<ClassModel> classes, List<InterfaceModel> interfaces, List<EnumModel> enumes,
			List<PackageModel> packages) {
		this.id = id;
		this.name = name;
		this.classes = classes;
		this.interfaces =interfaces;
		this.enumes = enumes;
		this.packages= packages;
	}
	
	public boolean addPackage(PackageModel p) {
		return packages.add(p);
	}
	public boolean addPackagees(List<PackageModel> newPackages) {
		return packages.addAll(newPackages);
	}
	
	public boolean addClass(ClassModel c) {
		return classes.add(c);
	}
	public boolean addClasses(List<ClassModel> newClasses) {
		return classes.addAll(newClasses);
	}
	
	public boolean addEnum(EnumModel e) {
		return enumes.add(e);
	}
	public boolean addEnumes(List<EnumModel> newEnums) {
		return enumes.addAll(newEnums);
	}
	public boolean addInterface(InterfaceModel intrf) {
		return interfaces.add(intrf);
	}
	public boolean addInterfaces(List<InterfaceModel> newInterfaces) {
		return interfaces.addAll(newInterfaces);
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
	
	public List<ClassModel> getClasses() {
		return classes;
	}
	public void setClasses(List<ClassModel> classes) {
		this.classes = classes;
	}
	public List<InterfaceModel> getInterfaces() {
		return interfaces;
	}
	public void setInterfaces(List<InterfaceModel> interfaces) {
		this.interfaces = interfaces;
	}
	public List<EnumModel> getEnumes() {
		return enumes;
	}
	public void setEnumes(List<EnumModel> enumes) {
		this.enumes = enumes;
	}
	public List<PackageModel> getPackages() {
		return packages;
	}
	public void setPackages(List<PackageModel> packageModel) {
		this.packages = packageModel;
	}

	
}
