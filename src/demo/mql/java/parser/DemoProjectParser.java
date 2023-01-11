package demo.mql.java.parser;


import java.util.List;
import java.util.Vector;
import org.mql.java.parser.ClassExplorer;
import static demo.mql.java.parser.Converter.*;
import demo.mql.java.models.PackageModel;

@SuppressWarnings("rawtypes")
public class DemoProjectParser {

	private List<PackageModel> packageModels;
	private List<Class> cls;
	
	public DemoProjectParser(String path) {
		cls = new ClassExplorer(path).getFoundedClasses();
		packageModels = new Vector<>();
	}
	
	public boolean parse() {
		if(cls==null) 
			return false;
		for(Class c : cls) {	
			addClass(c);
		}	
		return true;
	}
	
	
	private void addClass(Class c){
		int index = packageIndex(c.getPackage());
		if(index == -1) {
				packageModels.add(toPackageModel(c.getPackage()));
				index = packageIndex(c.getPackage());
		}
		setEClass(packageModels.get(index),c);			
	}
	
	
	private int packageIndex(Package p) {
		long id = idGenerator(p.getName());
		for(int i=0;i<packageModels.size();i++) {
			if(packageModels.get(i).getId() == id)
				return i;
		}
		return -1;		
	}
	
	
	
	private boolean setEClass(PackageModel p , Class c) {
		if(c.isEnum()) {
			return p.addEnum(toEnumModel(c));
		}
		else if(c.isInterface()) {
			return p.addInterface(toInterfaceModel(c));	
		}
		else {
			return p.addClass(toClassModel(c));
		}
	}
	

	public List<PackageModel> getPackages() {
		return packageModels;
	}
}
