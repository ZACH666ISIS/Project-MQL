package demo.mql.java.xml;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import demo.mql.java.parser.XMIParser;


public class XMIPersister {
		
	private List<EPackage> ePackages;
	private String output;
	public XMIPersister(String path,String output) {
		XMIParser p = new XMIParser(path);
		p.parse();
		this.output = output;
		ePackages = p.getPackages();
		persiste();
		
	}
	
	private void persiste() {
		try {
			Resource.Factory.Registry.INSTANCE.
			getExtensionToFactoryMap().put("ecore",new EcoreResourceFactoryImpl());
			ResourceSet resourceSet = new ResourceSetImpl();
			Resource resource = resourceSet.
			createResource(URI.createFileURI("test.ecore"));
			resource.getContents().addAll(ePackages);
			StringWriter stringWriter = new StringWriter();
			URIConverter.WriteableOutputStream outputStream =
			new URIConverter.WriteableOutputStream(stringWriter, "UTF-8");
			Map<String, String> options = new HashMap<String, String>();
			resource.save(outputStream, options);
			PrintWriter printer = new PrintWriter(new File(output));
			printer.print(stringWriter);
			printer.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	
	
	
}
