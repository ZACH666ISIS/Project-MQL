package demo.mql.java.parser;


import java.util.List;
import java.util.Vector;
import org.mql.java.parser.ClassExplorer;
import static demo.mql.java.parser.Converter.*;
import demo.mql.java.models.PackageModel;

@SuppressWarnings("rawtypes")
public class ProjectParser {
	
	private List<PackageModel> packageModels;
	private List<Class> cls;
	
	public ProjectParser(String path) {
		cls = new ClassExplorer(path).getFoundedClasses();
		packageModels = new Vector<>();
	}
	
	public boolean parse() {
		if(cls.isEmpty()) 
			return false;
		for(Class c : cls) {	
			addClassPackage(c);
		}	
		return true;
	}
	
	
	
	private void addClassPackage(Class c){
		PackageModel p = toPackageModel(c.getPackage());
		if(checkPackages(p,packageModels)){
			p = getPackage(p, packageModels);
		}
		else {
			addPackage(p, packageModels);
		}
		addClass(p,c);
	}
	
	
	private void addClass(PackageModel p , Class c) {
		if(c.isEnum()) {
			p.addEnum(toEnumModel(c));
			return ; 
		}
		else if(c.isInterface()) {
			 p.addInterface(toInterfaceModel(c));	
			 return ;
		}
		else {
			p.addClass(toClassModel(c));
			return ;
		}
	}
	

	
	private PackageModel addPackage(PackageModel p, List<PackageModel> packages) {
		 List<PackageModel> pkgs = getSubPackages(p, packages);
		 pkgs.add(p);
		 return p;
	}
	
	
	private List<PackageModel> getSubPackages(PackageModel p, List<PackageModel> packages) {
		for(PackageModel pkg : packages) {
			//if is same class continue
			if(!p.getName().equals(pkg.getName())) {
				//if pkg subpackage of p
				if(p.getName().contains(pkg.getName())   ) {
					//if they have other subpackages
					if(otherSubPackages(pkg.getPackages(), p)) {
						return getSubPackages(p, pkg.getPackages());
					}
					return pkg.getPackages();
				}
				//if p subpackage of pkg
				else if(pkg.getName().contains(p.getName()) ) {
					p.addPackage(pkg);
					packages.remove(pkg);
					return packages;
				}
				else {
					if(!pkg.getPackages().isEmpty())
						getSubPackages(p, pkg.getPackages());
				}
			}
		}
		return packages;
	}
	
	private boolean otherSubPackages(List<PackageModel> base, PackageModel p) {
		for(PackageModel pkg :base) {
			if(p.getName().contains(pkg.getName()) || pkg.getName().contains(p.getName()))
					return true;
			return otherSubPackages(pkg.getPackages(), p);
		}
		return false;
	}
	

	private PackageModel getPackage(PackageModel p, PackageModel pE){
		if(isSame(p, pE)) {
			return pE;
		}
		else if(!pE.getPackages().isEmpty()) {
			return getPackage(p, pE.getPackages());
		}
			return null;
		
	}
	
	private PackageModel getPackage(PackageModel p, List<PackageModel> packages){
		for(PackageModel pkg : packages) {
			PackageModel newPkg = getPackage(p, pkg);
			if(newPkg != null) {
				return newPkg;
			}		
		}
		// throw Error
		return null;
	}
	
	
	
	private boolean checkIfExists(PackageModel p, PackageModel pE){
		if(isSame(p, pE)) {
			return true;
		}
		else if(!pE.getPackages().isEmpty()) {
			return checkPackages(p, pE.getPackages());
		}
		else {
			return false;
		}
	}
	
	private boolean checkPackages(PackageModel p, List<PackageModel> packages) {	
		for(PackageModel pkg : packages) {
			if(checkIfExists(p, pkg)) {
				return true;
			}
		}
		return false;
	}
	


	private boolean isSame(PackageModel p1 ,PackageModel p2) {
			return (p1.getId() == p2.getId());
	}

	public List<PackageModel> getPackages() {
		return packageModels;
	}

	
//	private void showPackages(List<PackageModel> packages) {
//	for(PackageModel pkg : packages) {
//		System.out.println("Name : "+pkg.getName() + " |  Id : : "+pkg.getId());
//		if(!pkg.getPackages().isEmpty()) {
//			showPackages(pkg.getPackages());
//		}
//	}
//}




}
