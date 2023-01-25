package demo.mql.java.parser;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.mql.java.parser.ClassExplorer;


/**
 * 
 * No Package Nested
 *
 */

@SuppressWarnings("rawtypes")
public class XMIParserS {
	private List<EPackage> packages;
	private List<Class> cls;
	private EcoreFactory ecoreFactory;
	public XMIParserS(String path) {
			cls = new ClassExplorer(path).getFoundedClasses();
			ecoreFactory = EcoreFactory.eINSTANCE;
			packages = new Vector<>();
	}	
	
	
	public boolean parse() {
		if(cls.isEmpty()) 
			return false;
		for(Class c : cls) {	
			EPackage ep = getEPackage(c.getPackage());
			ep.getEClassifiers().add(createEClass(c));
		}	
		return true;
	}
	
	
	
	
	private EPackage getEPackage(Package p) {
		EPackage ep = createEPackage(p);
		int index = isExist(p);
		if( index == -1) {
			packages.add(ep);
			return ep;
		}
		return packages.get(index);	
	}
	
	private int isExist(Package p) {
		for(EPackage pkg : packages) {
			if(isSame(pkg.getName(),p.getName()))
				return packages.indexOf(pkg);
		}
		return -1;
	}
	
	private EClass createEClass(Class c) {
		EClass ec = ecoreFactory.createEClass();
		ec.setName(c.getCanonicalName());
		for(Field f : c.getDeclaredFields()) {
			ec.getEStructuralFeatures().add(createField(f));
			EClassifier e = searchClassifier(f);
			if( e != null) {
				ec.getEStructuralFeatures().add(createRef(f, e));
			}
		}
		return ec;
	}
	
	
	private EAttribute createField(Field f) {
		EAttribute attr = ecoreFactory.createEAttribute();
		attr.setName(f.getName());
		return attr;
	}
	
	private EReference createRef(Field f, EClassifier ec) {
		EReference ref = ecoreFactory.createEReference();
		ref.setName(f.getName());
		ref.setEType(ec);
		return ref;
	}
	
	private EClassifier searchClassifier(Field f) {
		for(EPackage p : packages) {
			for(EClassifier cl : p.getEClassifiers()) {
				if(isSame(cl.getName(), f.getType().getCanonicalName()))
					return cl;
			}
		}
		return null;
	}
	
	
	private EPackage createEPackage(Package p) {
		EPackage ep = ecoreFactory.createEPackage(); 
		ep.setName(p.getName());
		return ep;
	}
	
	private boolean isSame(String s1, String s2) {
			return s1.equals(s2);		
	}
	
	public List<EPackage> getPackages() {
		return packages;
	}


	public void setPackages(List<EPackage> packages) {
		this.packages = packages;
	}
}
