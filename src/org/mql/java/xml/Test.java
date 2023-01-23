package org.mql.java.xml;


import demo.mql.java.models.EnumModel;
import demo.mql.java.models.MethodModel;
import demo.mql.java.models.PackageModel;
import demo.mql.java.parser.ProjectParser;
import demo.mql.java.parser.XMIParserS;
import demo.mql.java.xml.XMIPersister;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

public class Test {

	public Test() {
		exp1();
	}
	


	void exp1(){
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EPackage aPackage = ecoreFactory.createEPackage();
		aPackage.setName("somePackage");
		EClass aClass = ecoreFactory.createEClass();
		aClass.setName("SomeClass");
		aPackage.getEClassifiers().add(aClass);
		EAttribute anAttribute = ecoreFactory.createEAttribute();
		anAttribute.setName("someAttribute");
		anAttribute.setEType(ecoreFactory.getEcorePackage().
		getEString());
		aClass.getEStructuralFeatures().add(anAttribute);
		EReference aReference = ecoreFactory.createEReference();
		aReference.setName("someReference");
		aReference.setEType(aClass);
		aClass.getEStructuralFeatures().add(aReference);
		EPackage aPackage2 = ecoreFactory.createEPackage();
		aPackage2.setName("somePackage");
		try {
			Resource.Factory.Registry.INSTANCE.
			getExtensionToFactoryMap().put("ecore",new EcoreResourceFactoryImpl());
			ResourceSet resourceSet = new ResourceSetImpl();
			Resource resource = resourceSet.
			createResource(URI.createFileURI("test.ecore"));
			resource.getContents().add(aPackage);
			StringWriter stringWriter = new StringWriter();
			URIConverter.WriteableOutputStream outputStream =
			new URIConverter.WriteableOutputStream(stringWriter, "UTF-8");
			Map<String, String> options = new HashMap<String, String>();
			resource.save(outputStream, options);
			System.out.println(stringWriter.toString());
		} catch (Exception ioe) {
			ioe.printStackTrace(); 
		}


	}
		
	
	
	public static void main(String[] args) {
		new Test();
	}
	
}

