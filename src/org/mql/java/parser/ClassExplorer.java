package org.mql.java.parser;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Vector;


@SuppressWarnings("rawtypes")
public class ClassExplorer{
	
	private List<String> classesNames;
	private String absolutPath;
	
	public ClassExplorer(String absolutPath) {
		this.absolutPath = absolutPath.replace('/', '\\');
		if(!this.absolutPath.endsWith("\\")) {
			this.absolutPath += "\\";
		}
		classesNames = new  Vector<>();	
	}


	public List<Class> getFoundedClasses() {
		List<Class> classLoaded = new Vector<Class>();
		try {
			File file = new File(absolutPath);
			getClassesNames(file);
			URLClassLoader urlClassLoader = URLClassLoader.newInstance(new URL[] {
				file.toURI().toURL()
			});
			for(String className : classesNames)
				classLoaded.add(urlClassLoader.loadClass(className));
			return classLoaded;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	
	private void getClassesNames(File directory) {
		for(File f : directory.listFiles()) {
			if(f.isFile() && f.getName().endsWith(".class")) {
				classesNames.add(f.getAbsolutePath().replace(absolutPath,"").replace('\\', '.').replace(".class", ""));
			}
			else if(f.isDirectory()) {
				getClassesNames(f);
			}
		}
	}
	

}
