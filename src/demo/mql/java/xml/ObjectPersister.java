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
import org.w3c.dom.Text;


public class ObjectPersister {
	
	private Document document;
	private Element root;
	private File file;
	
	public ObjectPersister() {
	}

	public ObjectPersister(File file) {
		this.file = file;
		init();
	}
	
	
	private void init() {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder =  factory.newDocumentBuilder();
			document =builder.newDocument();
		} catch (Exception e) {}
	
	}	
//	private void addObjects(List<?> o) {
//		root.appendChild(createObjects(o,o.getClass().getSimpleName()));
//	}
	private void addObjects(List<?> o,String s) {
		root.appendChild(createObjects(o,s));
	}
	private void addObject(Object o) {
		root.appendChild(createObject(o));
	}

	public void setObject(Object o) {
			root = createObject(o);	
	}

	public void save() {
		createFile();
	}
	private void createFile() {
		try {
			document.appendChild(root);
			Result res = new StreamResult(file);
			DOMSource source = new DOMSource(document);
			TransformerFactory trsFormFactory = TransformerFactory.newInstance();
			Transformer transformer = trsFormFactory.newTransformer();
			transformer.transform(source, res);
		} catch (Exception e) {}
		
	}

	
	private Element createObject(Object o,String name) {
		Element e = document.createElement(name);
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
								elm = createObjects(objects,f.getName());
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
				else if(!newObj.getClass().isEnum()) {
					e.appendChild(createObject(newObj,f.getName()));
				}
				else {
						Text txt = document.createTextNode(newObj.toString());
						Element elm = document.createElement(f.getName());
						elm.appendChild(txt);
						e.appendChild(elm);		
				}
			
			}			
		}
		return e;
	}

	
	private Element createObject(Object o) {	
		return createObject(o,o.getClass().getSimpleName());
	}

	
	private Element createNativeObject(List<?> objects, String name) {
		Element e = document.createElement(name);
			for(Object o : objects) {	
				Text txt = document.createTextNode(o.toString());
				Element elm = document.createElement(o.getClass().getSimpleName());
				elm.appendChild(txt);
				e.appendChild(elm);
			}	
		return e;
	}
	
	
	private Element createObjects(List<?> objects,String name) {
		Element e = document.createElement(name);
		for(Object o : objects) {	
			Element elm = createObject(o);
			if(elm != null) {
				e.appendChild(elm);
			}			
		}
			return e;
	}
	
	private static boolean isNativeObjects(List<?> objects) {
		return isNative(objects.get(0));
	}
	
	private static boolean isNative(Object o) {
		if(o.getClass().getCanonicalName().contains("java.lang")) return true;
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
	
	

}
