package demo.mql.java.parser;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.mql.java.parser.ClassExplorer;


/**
 * Nested Package (for organised our EPackages)
 *
 */
@SuppressWarnings("rawtypes")
public class XMIParser {
	private List<EPackage> packages;
	private List<Class> cls;
	private EcoreFactory ecoreFactory;
	
	public XMIParser(String path) {
		cls = new ClassExplorer(path).getFoundedClasses();
		ecoreFactory = EcoreFactory.eINSTANCE;
		packages = new Vector<>();
	}
	
	public boolean parse() {
		if(cls.isEmpty()) 
			return false;
		for(Class c : cls) {	
			addClassToPackage(c);
		}	
		return true;
	}
	
	
	
	private void addClassToPackage(Class c){
		EPackage p = createEPackage(c.getPackage());
		if(checkPackages(p,packages)){
			p = getPackage(p, packages);
		}
		else {
			addPackage(p, packages);
		}
		addClass(p,c);
	}
	
	
	private void addClass(EPackage p , Class c) {
			p.getEClassifiers().add(createEClass(c));
	}
	

	
	private EPackage addPackage(EPackage p, List<EPackage> packages) {
		 List<EPackage> pkgs = getSubPackages(p, packages);
		 pkgs.add(p);
		 return p;
	}
	
	
	
	private List<EPackage> getSubPackages(EPackage p, List<EPackage> packages) {
		for(EPackage pkg : packages) {
			//if is same class continue
			if(!p.getName().equals(pkg.getName())) {
				//if pkg subpackage of p
				if(p.getName().contains(pkg.getName())   ) {
					//if they have other subpackages
					if(otherSubPackages(pkg.getESubpackages(), p)) {
						return getSubPackages(p, pkg.getESubpackages());
					}
					return pkg.getESubpackages();
				}
				//if p subpackage of pkg
				else if(pkg.getName().contains(p.getName()) ) {
					p.getESubpackages().add(pkg);
					packages.remove(pkg);
					return packages;
				}
				else if(!pkg.getESubpackages().isEmpty()) {
						getSubPackages(p, pkg.getESubpackages());
				}
			}
		}
		return packages;
	}
	
	private boolean otherSubPackages(List<EPackage> base, EPackage p) {
		for(EPackage pkg :base) {
			if(p.getName().contains(pkg.getName()) || pkg.getName().contains(p.getName()))
					return true;
			return otherSubPackages(pkg.getESubpackages(), p);
		}
		return false;
	}
	

	private EPackage getPackage(EPackage p, EPackage pE){
		if(isSame(p, pE)) {
			return pE;
		}
		else if(!pE.getESubpackages().isEmpty()) {
			return getPackage(p, pE.getESubpackages());
		}
			return null;
		
	}
	
	private EPackage getPackage(EPackage p, List<EPackage> packages){
		for(EPackage pkg : packages) {
			EPackage newPkg = getPackage(p, pkg);
			if(newPkg != null) {
				return newPkg;
			}		
		}
		// throw Error
		return null;
	}
	
	
	
	private boolean checkIfExists(EPackage p, EPackage pE){
		if(isSame(p, pE)) {
			return true;
		}
		else if(!pE.getESubpackages().isEmpty()) {
			return checkPackages(p, pE.getESubpackages());
		}
		else {
			return false;
		}
	}
	
	private boolean checkPackages(EPackage p, List<EPackage> packages) {	
		for(EPackage pkg : packages) {
			if(checkIfExists(p, pkg)) {
				return true;
			}
		}
		return false;
	}
	


	private boolean isSame(EPackage p1 ,EPackage p2) {
			return (p1.getName().equals(p2.getName()));
	}
	private boolean isSame(String s1 ,String s2) {
		return (s1.equals(s2));
}
	
	private EClass createEClass(Class c) {
		EClass ec = ecoreFactory.createEClass();
		ec.setName(c.getCanonicalName());
		for(Field f : c.getDeclaredFields()) {
			ec.getEStructuralFeatures().add(createField(f));
			EClassifier e = searchClassifier(f.getType().getCanonicalName(),packages);
			if( e != null) {
				ec.getEStructuralFeatures().add(createRef(f, e));
			}
		}
		for(Method m : c.getDeclaredMethods()) {
			ec.getEOperations().add(createMethod(m));
		}
		return ec;
	}
	
	
	private EAttribute createField(Field f) {
		EAttribute attr = ecoreFactory.createEAttribute();
		attr.setName(f.getName());
		return attr;
	}
	
	private EOperation createMethod(Method m) {
		EOperation op = ecoreFactory.createEOperation();
		op.setName(m.getName());

		for(Parameter p : m.getParameters()) {
			op.getEParameters().add(createParameter(p));
		}
		return op;
	}
	
	private EParameter createParameter(Parameter param) {
		EParameter p = ecoreFactory.createEParameter();
		EClassifier e = searchClassifier(param.getType().getCanonicalName(),packages);
		if(e != null) {
			p.setEType(e);
		}
		else {
			p.setEType(Converter.nativeType(param.getType()));
		}

		return p;
	}
	



	
	private EReference createRef(Field f, EClassifier ec) {
		EReference ref = ecoreFactory.createEReference();
		ref.setName(f.getName());
		ref.setEType(ec);
		return ref;
	}
	
	private EClassifier searchClassifier(String s,List<EPackage> packages) {
		for(EPackage p : packages) {
			for(EClassifier cl : p.getEClassifiers()) {
				if(isSame(cl.getName(), s))
					return cl;
			}
			if(!p.getESubpackages().isEmpty()) {
				searchClassifier(s,p.getESubpackages());
			}
		}
		return null;
	}
	
	
	private EPackage createEPackage(Package p) {
		EPackage ep = ecoreFactory.createEPackage(); 
		ep.setName(p.getName());
		return ep;
	}
	

	public List<EPackage> getPackages() {
		return packages;
	}

}
