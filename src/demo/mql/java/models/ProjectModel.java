package demo.mql.java.models;

import java.util.List;

public class ProjectModel {
	private List<PackageModel> packages;
	private List<Association> associations;
	
	public ProjectModel() {
		// TODO Auto-generated constructor stub
	}

	public List<PackageModel> getPackages() {
		return packages;
	}

	public void setPackages(List<PackageModel> packages) {
		this.packages = packages;
	}

	public List<Association> getAssociation() {
		return associations;
	}

	public void setAssociation(List<Association> assotiations) {
		this.associations = assotiations;
	}
	
	
	
}
