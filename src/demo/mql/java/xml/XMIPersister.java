package demo.mql.java.xml;




import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Text;


public class XMIPersister {
	
	private Document document;
	Element root;
	
	public XMIPersister(List<?> o) {
		this("UML_Diagram",o);

	}
	public XMIPersister(Object o) {
		this("UML_Diagram",o);

	}
	public XMIPersister(String project,List<?> o) {
		init(project);
		root.appendChild(createObjects(o));
		createFile();
	}
	
	public XMIPersister(String project,Object o) {
		init(project);
		root.appendChild(createObject(o));
		createFile();
	}
	private void init(String project) {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder =  factory.newDocumentBuilder();
			document =builder.newDocument();
			root = document.createElement("uml:Model");
		} catch (Exception e) {}
	
	}
	
	public void createFile() {
		try {
			root.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xmi","http://www.omg.org/XMI");
			root.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:uml","http://www.eclipse.org/uml2/1.0.0/UML");
			document.appendChild(root);
			Result res = new StreamResult(new File("resources/XMIdiagramDEMO.xml"));
			DOMSource source = new DOMSource(document);
			TransformerFactory trsFormFactory = TransformerFactory.newInstance();
			Transformer transformer = trsFormFactory.newTransformer();
			transformer.transform(source, res);
		} catch (Exception e) {}
		
	}

	
	public Element createObject(Object o) {	
		Element e = document.createElement(o.getClass().getSimpleName());
		for(Field f : o.getClass().getDeclaredFields()) {
			if(Modifier.isPrivate(f.getModifiers())) {
				f.setAccessible(true);
			}
			Object newObj = getValue(f, o);
			if(newObj != null) {
				 if(List.class.isAssignableFrom(f.getType())){
					
					List<?> objects = (List<?>) newObj;
					if(objects != null) {
						Element elm = null;
						if(!objects.isEmpty()) {
							if(isNativeObjects(objects)) {
								elm = createNativeObject(objects,f.getName());
							}
							else {
									elm = createObjects(objects);
							}		
						}
						if(elm != null) {
							e.appendChild(elm);
						}							
					}		
				}
				else if(f.getType().isPrimitive() || isString(newObj)) {
					e.setAttribute(f.getName(), getValue(f,o).toString());
				}
				else{	
					Text txt = document.createTextNode(newObj.toString());
					Element elm = document.createElement(f.getName());
					elm.appendChild(txt);
					e.appendChild(elm);	
				}
			}			
		}
		return e;
		
	}

	public Element createNativeObject(List<?> objects, String name) {
		Element e = document.createElement(name);
			for(Object o : objects) {	
				Text txt = document.createTextNode(o.toString());
				Element elm = document.createElement(o.getClass().getSimpleName());
				elm.appendChild(txt);
				e.appendChild(elm);
			}	
		return e;
	}
	
	
	public Element createObjects(List<?> objects) {
		Element e = document.createElement("OwnedMember");
		
		
		for(Object o : objects) {	
					Element elm = createObject(o);
					if(elm != null)
						e.appendChild(elm);
						
			}
			return e;
	}
	
	private static boolean isNativeObjects(List<?> objects) {
		if(objects.get(0).getClass().getCanonicalName().contains("java.lang")) return true;
		return false;
	}
	private static boolean isString(Object o) {
		return o instanceof String;
	}
	
	private static Object getValue(Field f, Object o) {
		try {
			return f.get(o);
			
		} catch (Exception e) {	}
		return null;
	}
	
	public String attribute(Node node,String name) {
		NamedNodeMap atts =node.getAttributes();
		return atts.getNamedItem(name).getNodeValue();
	}
	

}
